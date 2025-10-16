package com.etiya.searchservice.service;

import com.etiya.searchservice.domain.Address;
import com.etiya.searchservice.domain.ContactMedium;
import com.etiya.searchservice.domain.CustomerSearch;
import com.etiya.searchservice.repository.CustomerSearchRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CustomerSearchServiceImpl implements CustomerSearchService {
    private final CustomerSearchRepository customerSearchRepository;

    public CustomerSearchServiceImpl(CustomerSearchRepository customerSearchRepository) {
        this.customerSearchRepository = customerSearchRepository;
    }

    @Override
    public void add(CustomerSearch customerSearch) {
        customerSearchRepository.save(customerSearch);
        //customerSearchRepository.save() çağrısı ile Elasticsearch’e veriyi yazar.
    }

    @Override
    public void addAddress(Address address, String customerId) {
        Optional<CustomerSearch> customerOpt = customerSearchRepository.findById(customerId);
        if(customerOpt.isPresent()){
            CustomerSearch customer = customerOpt.get();
            customer.getAddresses().add(address);
            customerSearchRepository.save(customer);
        }
    }

    @Override
    public void updateAddress(Address address, String customerId) {
        // Find the customer by ID
        Optional<CustomerSearch> customerOpt = customerSearchRepository.findById(customerId.toString());

        if (customerOpt.isPresent()) {
            CustomerSearch customer = customerOpt.get();
            List<Address> addresses = customer.getAddresses();
            boolean existsAddress = addresses.stream().anyMatch(a -> a.getAddressId() == address.getAddressId());
            if (existsAddress) {
                List<Address> updateList = addresses.stream().map(a-> {
                    if(a.getAddressId() == address.getAddressId()){
                        a.setStreet(address.getStreet());
                        a.setHouseNumber(address.getHouseNumber());
                        a.setDescription(address.getDescription());
                        a.setDefault(address.isDefault());
                        a.setDistrictId(address.getDistrictId());
                    }
                    return a;
                }).collect(Collectors.toList());
                customer.setAddresses(updateList);
            }

            // Save the updated customer
            customerSearchRepository.save(customer);
        } else {
            throw new RuntimeException("Customer not found");
        }
    }

    @Override
    public void deleteAddress(int addressId, String customerId) {
        Optional<CustomerSearch> customerOpt = customerSearchRepository.findById(customerId);

        if (customerOpt.isPresent()) {
            CustomerSearch customer = customerOpt.get();

            // Find the existing address by its ID or some unique identifier (e.g., houseNumber, street)
            boolean addressRemoved = customer.getAddresses().removeIf(addr -> addr.getAddressId() == addressId);

            if (addressRemoved) {
                // Save the updated customer after address removal
                customerSearchRepository.save(customer);
            } else {
                throw new RuntimeException("Address not found for deletion");
            }
        } else {
            throw new RuntimeException("Customer not found");
        }
    }

    @Override
    public void addContactMedium(ContactMedium contactMedium, String customerId) {
        Optional<CustomerSearch> customerOpt = customerSearchRepository.findById(customerId.toString());
        if(customerOpt.isPresent()) {
            CustomerSearch customer = customerOpt.get();
            customer.getContactMediums().add(contactMedium);
            customerSearchRepository.save(customer);
        }
    }

    @Override
    public void updateContactMedium(ContactMedium contactMedium, String customerId) {
        Optional<CustomerSearch> customerOpt = customerSearchRepository.findById(customerId.toString());

        if (customerOpt.isPresent()) {
            CustomerSearch customer = customerOpt.get();
            List<ContactMedium> contactMediums = customer.getContactMediums();
            boolean existsContactMedium = contactMediums.stream().anyMatch(a -> a.getId() == contactMedium.getId());
            if (existsContactMedium) {
                List<ContactMedium> updateList = contactMediums.stream().map(a-> {
                    if(a.getId() == contactMedium.getId()){
                                a.setType(contactMedium.getType());
                                a.setValue(contactMedium.getValue());
                                a.setPrimary(contactMedium.isPrimary());
                    }
                    return a;
                }).collect(Collectors.toList());
                customer.setContactMediums(updateList);
            }

            // Save the updated customer
            customerSearchRepository.save(customer);
        } else {
            throw new RuntimeException("Customer not found");
        }
    }

    @Override
    public void deleteContactMedium(int id, String customerId) {
        Optional<CustomerSearch> customerOpt = customerSearchRepository.findById(customerId);

        if (customerOpt.isPresent()) {
            CustomerSearch customer = customerOpt.get();

            // Find the existing address by its ID or some unique identifier (e.g., houseNumber, street)
            boolean contactMediumRemoved = customer.getContactMediums().removeIf(cm -> cm.getId() == id);

            if (contactMediumRemoved) {
                // Save the updated customer after address removal
                customerSearchRepository.save(customer);
            } else {
                throw new RuntimeException("Contact Medium not found for deletion");
            }
        } else {
            throw new RuntimeException("Customer not found");
        }
    }
}
