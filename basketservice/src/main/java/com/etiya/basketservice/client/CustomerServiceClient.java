package com.etiya.basketservice.client;

import com.etiya.common.responses.BillingAccountResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "customerservice")
public interface CustomerServiceClient {

    @GetMapping("/api/billingAccounts/{id}")
    BillingAccountResponse getBillingAccountById(@PathVariable("id") int id);

}
