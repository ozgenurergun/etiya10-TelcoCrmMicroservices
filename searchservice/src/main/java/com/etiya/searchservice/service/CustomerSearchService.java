package com.etiya.searchservice.service;

import com.etiya.searchservice.domain.Address;
import com.etiya.searchservice.domain.ContactMedium;
import com.etiya.searchservice.domain.CustomerSearch;

import java.util.List;

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
}
