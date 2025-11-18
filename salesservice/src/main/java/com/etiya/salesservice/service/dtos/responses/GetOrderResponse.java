package com.etiya.salesservice.service.dtos.responses;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public class GetOrderResponse {
    private String id;
    private LocalDateTime orderDate;
    private String status;
    private BigDecimal totalAmount;

    // Snapshot verileri
    private OrderBillingAccountResponse billingAccount;
    private OrderAddressResponse address;

    // Ürün listesi
    private List<OrderItemResponse> items;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public LocalDateTime getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDateTime orderDate) {
        this.orderDate = orderDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    public OrderBillingAccountResponse getBillingAccount() {
        return billingAccount;
    }

    public void setBillingAccount(OrderBillingAccountResponse billingAccount) {
        this.billingAccount = billingAccount;
    }

    public OrderAddressResponse getAddress() {
        return address;
    }

    public void setAddress(OrderAddressResponse address) {
        this.address = address;
    }

    public List<OrderItemResponse> getItems() {
        return items;
    }

    public void setItems(List<OrderItemResponse> items) {
        this.items = items;
    }
}
