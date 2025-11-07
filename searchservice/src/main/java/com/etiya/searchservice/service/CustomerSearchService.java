package com.etiya.searchservice.service;

import com.etiya.searchservice.domain.Address;
import com.etiya.searchservice.domain.ContactMedium;
import com.etiya.searchservice.domain.CustomerSearch;

import java.util.List;
import java.util.Optional;

public interface CustomerSearchService {

    void add(CustomerSearch customerSearch);
    void addAddress(Address address);
    void updateAddress(Address address);
    void deleteAddress(Address address);
    void addContactMedium(ContactMedium contactMedium);
    void updateContactMedium(ContactMedium contactMedium);
    void deleteContactMedium(ContactMedium contactMedium);

    List<CustomerSearch> searchAllFields(String keyword);

    List<CustomerSearch> findAll();

    List<CustomerSearch> searchByFirstName(String firstName);

    List<CustomerSearch> findByNationalId(String nationalId);

    List<CustomerSearch> findByLastNameFuzzy(String lastName);

    List<CustomerSearch> searchByFirstNameAndLastName(String firstName, String lastName);

    CustomerSearch searchByCustomerId(String customerId);

    List<CustomerSearch> searchDynamic(
            String id,
            String customerNumber,
            String nationalId,
            String firstName,
            String lastName,
            String value
    );

    Optional<CustomerSearch> findById(String id);

    void save(CustomerSearch customerSearch);
}
