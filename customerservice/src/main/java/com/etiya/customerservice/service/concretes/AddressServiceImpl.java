package com.etiya.customerservice.service.concretes;

import com.etiya.common.events.address.CreateAddressEvent;
import com.etiya.common.events.address.DeleteAddressEvent;
import com.etiya.common.events.address.UpdateAddressEvent;
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

        Address createdAddress = addressRepository.save(address);
        addressBusinessRules.checkIsPrimaryOnlyOne(createdAddress);
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
        return response;    }

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
        address.setDeletedDate(LocalDateTime.now());
        addressRepository.save(address);
    }

    @Override
    public UpdatedAddressResponse update(UpdateAddressRequest request) {
        Address oldAddress = addressRepository.findById(request.getId()).orElseThrow(() -> new RuntimeException("Address not found"));
        Address address =  AddressMapper.INSTANCE.addressFromUpdateAddressRequest(request,oldAddress);
        Address updatedAddress = addressRepository.save(address);
        addressBusinessRules.checkIsPrimaryOnlyOne(updatedAddress);
        UpdateAddressEvent event = new UpdateAddressEvent(updatedAddress.getId(),
                updatedAddress.getStreet(),
                updatedAddress.getHouseNumber(),
                updatedAddress.getDescription(),
                updatedAddress.isDefault(),
                updatedAddress.getDistrict().getId(),
                updatedAddress.getCustomer().getId().toString());
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
    @Transactional
    public void setPrimaryAddress(int newPrimaryAddressId) {
        // 1. Önce, müşterinin mevcut 'default' adresini bulup 'false' yapın
        //    (Bir müşterinin 2 tane default adresi olamaz)
        //    Bu kısmı backend ekibiniz kendi Customer ilişkisine göre yazmalı.

        Address address = addressRepository.findById(newPrimaryAddressId).orElseThrow(() -> new RuntimeException("Address not found"));
        addressBusinessRules.checkIsPrimaryOnlyOne(address);

        // 2. Şimdi yeni seçilen adresi bulup 'true' yapın
        Address newDefault = addressRepository.findById(newPrimaryAddressId)
                .orElseThrow(() -> new RuntimeException("Address not found"));

        newDefault.setDefault(true);
        addressRepository.save(newDefault);

        UpdateAddressEvent event = new UpdateAddressEvent(newDefault.getId(),
                newDefault.getStreet(),
                newDefault.getHouseNumber(),
                newDefault.getDescription(),
                newDefault.isDefault(),
                newDefault.getDistrict().getId(),
                newDefault.getCustomer().getId().toString());
        updateAddressProducer.produceAddressUpdated(event);

        // Event fırlatma vs. burada da yapılabilir.
    }

}