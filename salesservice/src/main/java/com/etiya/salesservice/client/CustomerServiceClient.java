package com.etiya.salesservice.client;

import com.etiya.common.responses.AddressResponse;
import com.etiya.common.responses.BillingAccountResponse;
import com.etiya.common.responses.IndividualCustomerResponse;
import com.etiya.salesservice.service.dtos.responses.CustomerServiceAddressResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "customerservice")
public interface CustomerServiceClient {

    @GetMapping("/api/billingAccounts/{id}")
    BillingAccountResponse getBillingAccountById(@PathVariable("id") int id);

    @GetMapping("/api/addresses/getAddressById/{id}")
    AddressResponse getAddressById(@PathVariable("id") int id);

    @GetMapping("/api/individual-customers/getIndividualCustomerById/{id}")
    IndividualCustomerResponse getIndividualCustomerById(@PathVariable String id);
}
