package com.etiya.salesservice.controller;

import com.etiya.salesservice.service.abstracts.OrderService;
import com.etiya.salesservice.service.dtos.requests.CreateOrderRequest;
import com.etiya.salesservice.service.dtos.responses.CreatedOrderResponse;
import com.etiya.salesservice.service.dtos.responses.GetOrderResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CreatedOrderResponse create(@RequestBody CreateOrderRequest request){
        return orderService.createOrder(request);
    }

    @GetMapping("/customer/{customerId}")
    @ResponseStatus(HttpStatus.OK)
    public List<GetOrderResponse> getAllByCustomerId(@PathVariable String customerId){
        return orderService.getAllByCustomerId(customerId);
    }
}
