package com.etiya.customerservice.service.concretes;

import com.etiya.common.events.address.CreateAddressEvent;
import com.etiya.common.events.address.DeleteAddressEvent;
import com.etiya.common.events.address.UpdateAddressEvent;
import com.etiya.common.responses.AddressResponse;
import com.etiya.customerservice.domain.entities.Address;
import com.etiya.customerservice.domain.entities.Customer;
import com.etiya.customerservice.domain.entities.District;
import com.etiya.customerservice.repository.AddressRepository;
import com.etiya.customerservice.service.abstracts.AddressService;
import com.etiya.customerservice.service.abstracts.DistrictService;
import com.etiya.customerservice.service.mappings.AddressMapper;
import com.etiya.customerservice.service.requests.address.CreateAddressRequest;
import com.etiya.customerservice.service.requests.address.UpdateAddressRequest;
import com.etiya.customerservice.service.responses.address.CreatedAddressResponse;
import com.etiya.customerservice.service.responses.address.GetAddressResponse;
import com.etiya.customerservice.service.responses.address.GetListAddressResponse;
import com.etiya.customerservice.service.responses.address.UpdatedAddressResponse;
import com.etiya.customerservice.service.responses.district.GetDistrictResponse;
import com.etiya.customerservice.service.rules.AddressBusinessRules;
import com.etiya.customerservice.transport.kafka.producer.address.CreateAddressProducer;
import com.etiya.customerservice.transport.kafka.producer.address.DeleteAddressProducer;
import com.etiya.customerservice.transport.kafka.producer.address.UpdateAddressProducer;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class AddressServiceImpl implements AddressService {

    private final AddressRepository addressRepository;
    private final AddressBusinessRules addressBusinessRules;
    private final CreateAddressProducer createAddressProducer;
    private final UpdateAddressProducer updateAddressProducer;
    private final DeleteAddressProducer deleteAddressProducer;
    private final DistrictService districtService;


    public AddressServiceImpl(AddressRepository addressRepository, AddressBusinessRules addressBusinessRules, CreateAddressProducer createAddressProducer, UpdateAddressProducer updateAddressProducer, DeleteAddressProducer deleteAddressProducer, DistrictService districtService, DistrictService districtService1) {
        this.addressRepository = addressRepository;
        this.addressBusinessRules = addressBusinessRules;
        this.createAddressProducer = createAddressProducer;
        this.updateAddressProducer = updateAddressProducer;
        this.deleteAddressProducer = deleteAddressProducer;
        this.districtService = districtService1;
    }

    @Override
    @Transactional
    public CreatedAddressResponse add(CreateAddressRequest request) {
        Address address = AddressMapper.INSTANCE.addressFromCreateAddressRequest(request);



        District district = districtService.getByIdService(request.getDistrictId());
        address.setDistrict(district);

        addressBusinessRules.checkIsPrimaryOnlyOne(address);
        Address createdAddress = addressRepository.save(address);

        CreateAddressEvent event = new CreateAddressEvent(
                createdAddress.getId(),
                createdAddress.getStreet(),
                createdAddress.getHouseNumber(),
                createdAddress.getDescription(),
                createdAddress.isDefault(),
                createdAddress.getDistrict().getId(),
                createdAddress.getDistrict().getCity().getName(),
                createdAddress.getCustomer().getId().toString()
        );
        createAddressProducer.produceAddressCreated(event);
        CreatedAddressResponse response = AddressMapper.INSTANCE.createdAddressResponseFromAddress(createdAddress);
        return response;
    }

    @Override
    public List<GetListAddressResponse> getList() {
        List<Address> addressList = addressRepository.findAll();
        List<GetListAddressResponse> response = AddressMapper.INSTANCE.getListAddressResponsesFromAddressList(addressList);
        return response;
    }

    @Override
    public void delete(int id) {
        addressBusinessRules.checkIfBillingAccountExists(id);
        Address address = addressRepository.getOne(id);
        DeleteAddressEvent event = new DeleteAddressEvent(id,address.getCustomer().getId().toString());
        addressRepository.deleteById(id);
        deleteAddressProducer.produceAddressDeleted(event);
    }

    @Override
    public void softDelete(int id) {
        Address address = addressRepository.findById(id).orElseThrow(() -> new RuntimeException("Address not found"));
        DeleteAddressEvent event = new DeleteAddressEvent(id,address.getCustomer().getId().toString());
        deleteAddressProducer.produceAddressDeleted(event);
        address.setDeletedDate(LocalDateTime.now());
        address.setIsActive(0);
        addressRepository.save(address);
    }

    @Override
    public UpdatedAddressResponse update(UpdateAddressRequest request) {
        Address oldAddress = addressRepository.findById(request.getId())
                .orElseThrow(() -> new RuntimeException("Address not found"));

        // İstekten gelen default durumu. Eğer istekte gelmediyse (DTO'da null ise),
        // MapStruct ignore edeceği için eski değer (true/false) korunur.
        boolean requestWantsPrimary = request.getDefault();

        // MapStruct ile kısmi güncelleme yapılıyor.
        Address addressToUpdate = AddressMapper.INSTANCE.addressFromUpdateAddressRequest(request, oldAddress);

        // KONTROL 1: İstek BU adresi primary yapmak istiyor MU?
        // KONTROL 2: Bu adres zaten primary miydi?
        // NOT: isDefault alanı requestten gelip addressToUpdate'e yazıldığı için, sadece request.isDefault() kontrolü yeterli olabilir.

        if (addressToUpdate.isDefault()) { // Güncelleme sonrası bu adres primary ise (İstek böyle gelmiştir)

            UUID customerId = addressToUpdate.getCustomer().getId();

            // 1. Mevcut birincil adresi bul (varsa) ve 'false' yap
            addressRepository.findByCustomerIdAndIsDefaultTrue(customerId)
                    .ifPresent(oldDefaultAddress -> {

                        // !!! KRİTİK DÜZELTME: UUID'leri .equals() ile karşılaştırıyoruz !!!
                        if (oldDefaultAddress.getId() != addressToUpdate.getId()) {

                            oldDefaultAddress.setDefault(false);
                            addressRepository.save(oldDefaultAddress);

                            // ESKİ PRIMARY DEĞİŞTİ: Kafka Eventi fırlat
                            UpdateAddressEvent oldEvent = createUpdateAddressEvent(oldDefaultAddress);
                            updateAddressProducer.produceAddressUpdated(oldEvent);
                        }
                    });

            // addressToUpdate zaten isDefault=true olarak ayarlanmıştır (MapStruct veya manuel olarak).
        }

        // 2. Güncel adresi veritabanına kaydet
        Address updatedAddress = addressRepository.save(addressToUpdate);

        // 3. Güncellenen adres için Kafka Eventi fırlat
        UpdateAddressEvent event = createUpdateAddressEvent(updatedAddress);
        updateAddressProducer.produceAddressUpdated(event);

        UpdatedAddressResponse response = AddressMapper.INSTANCE.updatedAddressResponseFromAddress(updatedAddress);
        return response;
    }

    @Override
    public GetAddressResponse getById(int id) {
        Address address = addressRepository.findById(id).orElseThrow(() -> new RuntimeException("Address not found"));
        GetAddressResponse response = AddressMapper.INSTANCE.getAddressResponseFromAddress(address);
        return response;
    }

    @Override
    public AddressResponse getAddressById(int id) {
        Address address = addressRepository.findById(id).orElseThrow(() -> new RuntimeException("Address not found"));
        AddressResponse response = new AddressResponse();
        response.setId(address.getId());
        return response;
    }

    @Override
    public List<GetListAddressResponse> getByCustomerId(String customerId) {
        UUID uuid = UUID.fromString(customerId);

        List<Address> addressList = addressRepository.findByCustomerId(uuid);
        List<GetListAddressResponse> response = AddressMapper.INSTANCE.getListAddressResponsesFromAddressList(addressList);
        return response;
    }

    @Override
    @Transactional
    public void setPrimaryAddress(int newPrimaryAddressId) {
        // 1. Yeni adresi bul ve müşteriyi belirle
        Address newDefaultAddress = addressRepository.findById(newPrimaryAddressId)
                .orElseThrow(() -> new RuntimeException("Address not found"));

        UUID customerId = newDefaultAddress.getCustomer().getId();

        // 2. Mevcut birincil adresi bul (varsa) ve 'false' yap
        // Not: Bu adım, müşterinin önceki birincil adresini false yapmak için kritiktir.
        addressRepository.findByCustomerIdAndIsDefaultTrue(customerId)
                .ifPresent(oldDefaultAddress -> {
                    // Eğer eski adres, yeni seçilen adres değilse (ayrı bir adres ise)
                    if (!(oldDefaultAddress.getId()==(newDefaultAddress.getId()))) {
                        oldDefaultAddress.setDefault(false);
                        addressRepository.save(oldDefaultAddress);

                        // ESKİ ADRES DEĞİŞTİ: Elasticsearch'e haber ver
                        UpdateAddressEvent oldEvent = createUpdateAddressEvent(oldDefaultAddress);
                        updateAddressProducer.produceAddressUpdated(oldEvent);
                    }
                });

        // 3. Yeni seçilen adresi 'true' yap
        // (Zaten yukarıda bulduk, tekrar sorgulamaya gerek yok.)
        if (!newDefaultAddress.isDefault()) { // Zaten true değilse güncelle
            newDefaultAddress.setDefault(true);
            addressRepository.save(newDefaultAddress);

            // YENİ ADRES DEĞİŞTİ: Elasticsearch'e haber ver
            UpdateAddressEvent newEvent = createUpdateAddressEvent(newDefaultAddress);
            updateAddressProducer.produceAddressUpdated(newEvent);
        }
    }

    @Override
    public Address findById(int id) {
        return addressRepository.findById(id).orElseThrow(() -> new RuntimeException("Address not found"));
    }

    // Yardımcı Metot: UpdateAddressEvent oluşturmak için
    private UpdateAddressEvent createUpdateAddressEvent(Address address) {
        return new UpdateAddressEvent(
                address.getId(),
                address.getStreet(),
                address.getHouseNumber(),
                address.getDescription(),
                address.isDefault(), // Güncel değeri (true/false) kullan
                address.getDistrict().getId(),
                address.getCustomer().getId().toString()
        );
    }
}