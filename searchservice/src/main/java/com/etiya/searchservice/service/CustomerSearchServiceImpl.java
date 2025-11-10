package com.etiya.searchservice.service;

import com.etiya.searchservice.domain.Address;
import com.etiya.searchservice.domain.ContactMedium;
import com.etiya.searchservice.domain.CustomerSearch;
import com.etiya.searchservice.repository.CustomerSearchRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

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
    public void addAddress(Address address) {
        Optional<CustomerSearch> customerOpt = customerSearchRepository.findById(address.getCustomerId());
        if(customerOpt.isPresent()){
            CustomerSearch customer = customerOpt.get();
            customer.getAddresses().add(address);
            customerSearchRepository.save(customer);
        }
    }

    @Override
    public void updateAddress(Address address) {
// Find the customer by ID
        Optional<CustomerSearch> customerOpt = customerSearchRepository.findById(address.getCustomerId());

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
    public void updateCustomer(CustomerSearch customerSearch) {
        Optional<CustomerSearch> customerOpt = customerSearchRepository.findById(customerSearch.getId());

        if (customerOpt.isPresent()) {
            CustomerSearch customer = customerOpt.get();
            customer.setFirstName(customerSearch.getFirstName());
            customer.setMiddleName(customerSearch.getMiddleName());
            customer.setLastName(customerSearch.getLastName());
            customer.setFatherName(customerSearch.getFatherName());
            customer.setMotherName(customerSearch.getMotherName());
            customer.setDateOfBirth(customerSearch.getDateOfBirth());
            customer.setGender(customerSearch.getGender());

            customerSearchRepository.save(customer);
        }else  {
            throw new RuntimeException("Customer not found");
        }

    }

    @Override
    public void deleteAddress(Address address) {
        Optional<CustomerSearch> customerOpt = customerSearchRepository.findById(address.getCustomerId());

        if (customerOpt.isPresent()) {
            CustomerSearch customer = customerOpt.get();

            // Find the existing address by its ID or some unique identifier (e.g., houseNumber, street)
            boolean addressRemoved = customer.getAddresses().removeIf(addr -> addr.getAddressId() == address.getAddressId());

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
    public void deleteCustomer(CustomerSearch customerSearch) {
        Optional<CustomerSearch> customerOpt = customerSearchRepository.findById(customerSearch.getId());

        if (customerOpt.isPresent()) {
            customerSearchRepository.delete(customerOpt.get());
        }else  {
            throw new RuntimeException("Customer not found");
        }
    }

    @Override
    public void addContactMedium(ContactMedium contactMedium) {
        Optional<CustomerSearch> customerOpt = customerSearchRepository.findById(contactMedium.getCustomerId());
        if(customerOpt.isPresent()) {
            CustomerSearch customer = customerOpt.get();
            customer.getContactMediums().add(contactMedium);
            customerSearchRepository.save(customer);
        }
    }

    @Override
    public void updateContactMedium(ContactMedium contactMedium) {
        Optional<CustomerSearch> customerOpt = customerSearchRepository.findById(contactMedium.getCustomerId());

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
    public void deleteContactMedium(ContactMedium contactMedium) {
        Optional<CustomerSearch> customerOpt = customerSearchRepository.findById(contactMedium.getCustomerId().toString());
        if (customerOpt.isPresent()) {
            CustomerSearch customer = customerOpt.get();

            // Find the existing address by its ID or some unique identifier (e.g., houseNumber, street)
            boolean contactMediumRemoved = customer.getContactMediums().removeIf(cm -> cm.getId() == contactMedium.getId());

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

    @Override
    public List<CustomerSearch> searchAllFields(String keyword) {
        return customerSearchRepository.searchAllFields(keyword);
    }

    @Override
    public List<CustomerSearch> findAll() {
        return StreamSupport.stream(customerSearchRepository.findAll().spliterator(),false).collect(Collectors.toList());
    }

    @Override
    public List<CustomerSearch> searchByFirstName(String firstName) {
        return customerSearchRepository.searchByFirstName(firstName);
    }

    @Override
    public List<CustomerSearch> findByNationalId(String nationalId) {
        return customerSearchRepository.findByNationalId(nationalId);
    }

    @Override
    public List<CustomerSearch> findByLastNameFuzzy(String lastName) {
        return customerSearchRepository.findByLastNameFuzzy(lastName);
    }

    @Override
    public List<CustomerSearch> searchByFirstNameAndLastName(String firstName, String lastName) {
        return customerSearchRepository.searchByFirstNameAndLastName(firstName, lastName);
    }

    @Override
    public CustomerSearch searchByCustomerId(String customerId) {
        return customerSearchRepository.searchByCustomerId(customerId);
    }

    @Override
    public List<CustomerSearch> searchDynamic(String id, String customerNumber, String nationalId, String firstName, String lastName, String value) {
        return customerSearchRepository.searchDynamic(id,customerNumber,nationalId,firstName,lastName,value);
    }

    @Override
    public Optional<CustomerSearch> findById(String id) {
        return customerSearchRepository.findById(id);
    }

    @Override
    public void save(CustomerSearch customerSearch) {
        customerSearchRepository.save(customerSearch);
    }


}
