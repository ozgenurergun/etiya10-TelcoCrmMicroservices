package com.etiya.customerservice.service.abstracts;

import com.etiya.customerservice.service.requests.individualCustomer.CreateIndividualCustomerRequest;
import com.etiya.customerservice.service.requests.individualCustomer.UpdateIndividualCustomerRequest;
import com.etiya.customerservice.service.responses.individualCustomers.CreatedIndividualCustomerResponse;
import com.etiya.customerservice.service.responses.individualCustomers.GetIndividualCustomerResponse;
import com.etiya.customerservice.service.responses.individualCustomers.GetListIndividualCustomerResponse;
import com.etiya.customerservice.service.responses.individualCustomers.UpdatedIndividualCustomerResponse;

import java.util.List;

public interface IndividualCustomerService {
    //void add(IndividualCustomer individualCustomer);
    CreatedIndividualCustomerResponse add(CreateIndividualCustomerRequest createIndividualCustomerRequest);
    List<GetListIndividualCustomerResponse> getList();
    //List<GetListIndividualCustomerResponse> findAllWithAddresses();

    GetIndividualCustomerResponse getByLastName(String lastName);
    List<GetListIndividualCustomerResponse> getByFirstNameStartingWith(String prefix);
    List<GetListIndividualCustomerResponse> getByCustomerNumberPattern(String pattern);

    GetIndividualCustomerResponse getByCustomerId(String customerId);

    //upd
    UpdatedIndividualCustomerResponse update(UpdateIndividualCustomerRequest updateIndividualCustomerRequest);

    void delete(String customerId);

}
