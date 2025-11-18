package com.etiya.salesservice.service.abstracts;

import com.etiya.salesservice.service.dtos.requests.CreateOrderRequest;

public interface OrderService {

    void createOrder(CreateOrderRequest request);
}
