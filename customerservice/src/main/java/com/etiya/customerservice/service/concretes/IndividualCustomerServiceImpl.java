package com.etiya.customerservice.service.concretes;

import com.etiya.common.events.customer.CreateCustomerEvent;
import com.etiya.common.events.customer.DeleteCustomerEvent;
import com.etiya.common.events.customer.UpdateCustomerEvent;
import com.etiya.customerservice.domain.entities.Address;
import com.etiya.customerservice.domain.entities.IndividualCustomer;
import com.etiya.customerservice.repository.IndividualCustomerRepository;
import com.etiya.customerservice.service.abstracts.AddressService;
import com.etiya.customerservice.service.abstracts.ContactMediumService;
import com.etiya.customerservice.service.abstracts.IndividualCustomerService;
import com.etiya.customerservice.service.mappings.IndividualCustomerMapper;
import com.etiya.customerservice.service.requests.address.CreateAddressInCustomerRequest;
import com.etiya.customerservice.service.requests.address.CreateAddressRequest;
import com.etiya.customerservice.service.requests.contactMedium.CreateContactInCustomerRequest;
import com.etiya.customerservice.service.requests.contactMedium.CreateContactMediumRequest;
import com.etiya.customerservice.service.requests.individualCustomer.CreateIndividualCustomerRequest;
import com.etiya.customerservice.service.requests.individualCustomer.UpdateIndividualCustomerRequest;
import com.etiya.customerservice.service.responses.address.CreatedAddressResponse;
import com.etiya.customerservice.service.responses.address.GetListAddressResponse;
import com.etiya.customerservice.service.responses.contactMedium.CreatedContactMediumResponse;
import com.etiya.customerservice.service.responses.contactMedium.GetListContactMediumResponse;
import com.etiya.customerservice.service.responses.individualCustomers.CreatedIndividualCustomerResponse;
import com.etiya.customerservice.service.responses.individualCustomers.GetIndividualCustomerResponse;
import com.etiya.customerservice.service.responses.individualCustomers.GetListIndividualCustomerResponse;
import com.etiya.customerservice.service.responses.individualCustomers.UpdatedIndividualCustomerResponse;
import com.etiya.customerservice.service.rules.IndividualCustomerBusinessRules;
import com.etiya.customerservice.transport.kafka.producer.customer.CreateCustomerProducer;
import com.etiya.customerservice.transport.kafka.producer.customer.DeleteCustomerProducer;
import com.etiya.customerservice.transport.kafka.producer.customer.UpdateCustomerProducer;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class IndividualCustomerServiceImpl implements IndividualCustomerService {

    private final IndividualCustomerRepository individualCustomerRepository;
    private final IndividualCustomerBusinessRules individualCustomerBusinessRules;
    private final CreateCustomerProducer createCustomerProducer;
    private final AddressService addressService; // YENİ
    private final ContactMediumService contactMediumService; // YENİ
    private final UpdateCustomerProducer updateCustomerProducer ;
    private final DeleteCustomerProducer deleteCustomerProducer;

    public IndividualCustomerServiceImpl(IndividualCustomerRepository individualCustomerRepository, IndividualCustomerBusinessRules individualCustomerBusinessRules, CreateCustomerProducer createCustomerProducer, AddressService addressService, ContactMediumService contactMediumService, UpdateCustomerProducer updateCustomerProducer, DeleteCustomerProducer deleteCustomerProducer) {
        this.individualCustomerRepository = individualCustomerRepository;
        this.individualCustomerBusinessRules = individualCustomerBusinessRules;
        this.createCustomerProducer = createCustomerProducer;
        this.addressService = addressService;
        this.contactMediumService = contactMediumService;
        this.updateCustomerProducer = updateCustomerProducer;
        this.deleteCustomerProducer = deleteCustomerProducer;
    }

    @Override
    @Transactional // YENİ: Tüm metodun tek bir veritabanı işleminde çalışmasını sağlar
    public CreatedIndividualCustomerResponse add(CreateIndividualCustomerRequest request) {
        individualCustomerBusinessRules.checkIfIndividualCustomerExistsByIdentityNumber(request.getNationalId());
        IndividualCustomer individualCustomer = IndividualCustomerMapper.INSTANCE.individualCustomerFromCreateIndividualCustomerRequest(request);
        IndividualCustomer createdIndividualCustomer = individualCustomerRepository.save(individualCustomer);
        UUID customerId = createdIndividualCustomer.getId();

        // Yanıtları toplamak için boş listeler oluştur
        List<CreatedAddressResponse> createdAddresses = new ArrayList<>();
        List<CreatedContactMediumResponse> createdContacts = new ArrayList<>();

        CreateCustomerEvent event =
                new CreateCustomerEvent(createdIndividualCustomer.getId().toString(),
                        createdIndividualCustomer.getCustomerNumber(),
                        createdIndividualCustomer.getFirstName(),
                        createdIndividualCustomer.getMiddleName(),
                        createdIndividualCustomer.getLastName(),
                        createdIndividualCustomer.getNationalId(),
                        createdIndividualCustomer.getDateOfBirth().toString(),
                        createdIndividualCustomer.getMotherName(),
                        createdIndividualCustomer.getFatherName(),
                        createdIndividualCustomer.getGender());
        createCustomerProducer.produceCustomerCreated(event);

        // 4. Adresleri işle (YENİ)
        if (request.getAddresses() != null && !request.getAddresses().isEmpty()) {
            for (CreateAddressInCustomerRequest addressRequest : request.getAddresses()) {
                // AddressService'in beklediği tam CreateAddressRequest'i oluştur
                CreateAddressRequest newAddressReq = new CreateAddressRequest();
                newAddressReq.setStreet(addressRequest.getStreet());
                newAddressReq.setHouseNumber(addressRequest.getHouseNumber());
                newAddressReq.setDescription(addressRequest.getDescription());
                newAddressReq.setDefault(addressRequest.isDefault());
                newAddressReq.setDistrictId(addressRequest.getDistrictId());
                newAddressReq.setCustomerId(customerId.toString()); // EN ÖNEMLİ KISIM

                // YANITI YAKALA
                CreatedAddressResponse newAddressResponse = addressService.add(newAddressReq);
                createdAddresses.add(newAddressResponse);
            }
        }

        // 5. Contact Medium'ları işle (YENİ)
        if (request.getContactMediums() != null && !request.getContactMediums().isEmpty()) {
            for (CreateContactInCustomerRequest contactRequest : request.getContactMediums()) {
                // ContactMediumService'in beklediği tam CreateContactMediumRequest'i oluştur
                CreateContactMediumRequest newContactReq = new CreateContactMediumRequest();
                newContactReq.setType(contactRequest.getType());
                newContactReq.setValue(contactRequest.getValue());
                newContactReq.setPrimary(contactRequest.isPrimary());
                newContactReq.setCustomerId(customerId); // EN ÖNEMLİ KISIM

                // YANITI YAKALA
                CreatedContactMediumResponse newContactResponse = contactMediumService.add(newContactReq);
                createdContacts.add(newContactResponse);
            }
        }

        CreatedIndividualCustomerResponse response =
                IndividualCustomerMapper.INSTANCE.createdIndividualCustomerResponseFromIndividualCustomer(createdIndividualCustomer);

        response.setAddresses(createdAddresses);
        response.setContactMediums(createdContacts);

        return response;
    }

    @Override
    public List<GetListIndividualCustomerResponse> getList() {
        List<IndividualCustomer> individualCustomers = individualCustomerRepository.findAll();
        List<GetListIndividualCustomerResponse> responses = IndividualCustomerMapper.INSTANCE.getListIndividualCustomerResponseFromIndividualCustomers(individualCustomers);
        return responses;
    }

    @Override
    public GetIndividualCustomerResponse getByLastName(String lastName) {
        IndividualCustomer individualCustomer =
                individualCustomerRepository.findByLastName(lastName).orElseThrow(() -> new RuntimeException("Individual Customer not found"));
        GetIndividualCustomerResponse response =
                IndividualCustomerMapper.INSTANCE.getIndividualCustomerResponseFromIndividualCustomers(individualCustomer);
        return response;
    }

    @Override
    public List<GetListIndividualCustomerResponse> getByFirstNameStartingWith(String prefix) {
        List<IndividualCustomer> individualCustomers =
                individualCustomerRepository.findByFirstNameStartingPrefix(prefix);
        List<GetListIndividualCustomerResponse> responses =
                IndividualCustomerMapper.INSTANCE.getListIndividualCustomerResponseFromIndividualCustomers(individualCustomers);
        return responses;
    }

    @Override
    public List<GetListIndividualCustomerResponse> getByCustomerNumberPattern(String pattern) {
        List<IndividualCustomer> individualCustomers =
                individualCustomerRepository.findByCustomerNumberPattern(pattern);
        List<GetListIndividualCustomerResponse> responses =
                IndividualCustomerMapper.INSTANCE.getListIndividualCustomerResponseFromIndividualCustomers(individualCustomers);
        return responses;
    }

    @Override
    public GetIndividualCustomerResponse getByCustomerId(String customerId) {

        UUID uuid = UUID.fromString(customerId);

        IndividualCustomer individualCustomer = individualCustomerRepository.findById(uuid).orElseThrow(() -> new RuntimeException("Individual Customer not found"));

        GetIndividualCustomerResponse response = IndividualCustomerMapper.INSTANCE.getIndividualCustomerResponseFromIndividualCustomers(individualCustomer);
        return response;
    }

    @Override
    public UpdatedIndividualCustomerResponse update(UpdateIndividualCustomerRequest updateIndividualCustomerRequest) {
        IndividualCustomer oldIndividualCustomer = individualCustomerRepository.findById(updateIndividualCustomerRequest.getId()).orElseThrow(() -> new RuntimeException("Individual Customer not found"));

        IndividualCustomer updatedCustomer = IndividualCustomerMapper.INSTANCE.individualCustomerFromGetIndividualCustomerRequest(updateIndividualCustomerRequest, oldIndividualCustomer);
        individualCustomerRepository.save(updatedCustomer);

        String dateOfBirth = updatedCustomer.getDateOfBirth();
        if (dateOfBirth != null && dateOfBirth.trim().isEmpty()) {
            dateOfBirth = null;
        }

        UpdateCustomerEvent event = new UpdateCustomerEvent(
                updatedCustomer.getId().toString(),
                updatedCustomer.getCustomerNumber(),
                updatedCustomer.getFirstName(),
                updatedCustomer.getMiddleName(),
                updatedCustomer.getLastName(),
                updatedCustomer.getNationalId(),
                updatedCustomer.getDateOfBirth().toString(),
                updatedCustomer.getMotherName(),
                updatedCustomer.getFatherName(),
                updatedCustomer.getGender()
        );
        updateCustomerProducer.produceCustomerUpdated(event);

        UpdatedIndividualCustomerResponse response = IndividualCustomerMapper.INSTANCE.updatedIndividualCustomerResponseFromIndividualCustomer(updatedCustomer);
        return response;
    }

    @Override
    public void delete(String customerId) {
        UUID uuid = UUID.fromString(customerId);
        IndividualCustomer individualCustomer = individualCustomerRepository.findById(uuid).orElseThrow(() -> new RuntimeException("Individual Customer not found"));

        individualCustomer.setDeletedDate(LocalDateTime.now());
        individualCustomer.setIsActive(0);
        individualCustomerRepository.save(individualCustomer);

        List<GetListAddressResponse> addresses = addressService.getByCustomerId(customerId);
        for (GetListAddressResponse address : addresses) {
            addressService.softDelete(address.getId());
        }

        List<GetListContactMediumResponse> contactMediums = contactMediumService.getByCustomerId(customerId);
        for (GetListContactMediumResponse contactMedium : contactMediums) {
            contactMediumService.softDelete(contactMedium.getId());
        }

        DeleteCustomerEvent event = new DeleteCustomerEvent(
                individualCustomer.getId().toString()
        );
        deleteCustomerProducer.produceCustomerDeleted(event);
    }
}
