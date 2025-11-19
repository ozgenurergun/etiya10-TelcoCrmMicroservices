package com.etiya.searchservice.repository;

import com.etiya.searchservice.domain.CustomerSearch;

import java.util.List;

public interface CustomCustomerSearchRepository {
    List<CustomerSearch> searchDynamic(
            String id,
            String customerNumber,
            String nationalId,
            String firstName,
            String lastName,
            String value,
            String orderId
    );
}