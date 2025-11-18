package com.etiya.salesservice.service.dtos.responses;

import com.etiya.salesservice.domain.OrderItem;

import java.util.List;

public class CreatedOrderResponse {

    private String orderId;

    private List<OrderItem> orderItems;

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public List<OrderItem> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<OrderItem> orderItems) {
        this.orderItems = orderItems;
    }

    public CreatedOrderResponse(String orderId, List<OrderItem> orderItems) {
        this.orderId = orderId;
        this.orderItems = orderItems;
    }
}
