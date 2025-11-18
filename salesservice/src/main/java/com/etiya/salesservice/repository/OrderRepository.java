package com.etiya.salesservice.repository;

import com.etiya.salesservice.domain.Order;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface OrderRepository extends MongoRepository<Order,Integer> {

    // customerSnapshot objesinin içindeki originalCustomerId alanına göre bul
    List<Order> findByCustomerSnapshot_OriginalCustomerId(String customerId);
}
