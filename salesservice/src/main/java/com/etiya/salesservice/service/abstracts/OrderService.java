package com.etiya.salesservice.service.abstracts;

import com.etiya.salesservice.service.dtos.requests.CreateOrderRequest;
import com.etiya.salesservice.service.dtos.responses.CreatedOrderResponse;
import com.etiya.salesservice.service.dtos.responses.GetOrderResponse;

import java.util.List;

public interface OrderService {

    CreatedOrderResponse createOrder(CreateOrderRequest request);

    List<GetOrderResponse> getAllByCustomerId(String customerId);
}
