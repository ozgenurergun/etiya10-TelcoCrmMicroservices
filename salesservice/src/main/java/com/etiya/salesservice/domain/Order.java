package com.etiya.salesservice.domain;

import com.etiya.common.entities.BaseEntity;

import java.math.BigDecimal;

public class Order extends BaseEntity {
    private int id;
    private int customerId;
    private int billingAccountId;
    private int addressId;
    private BigDecimal totalAmount;
    private boolean isComplete;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public int getBillingAccountId() {
        return billingAccountId;
    }

    public void setBillingAccountId(int billingAccountId) {
        this.billingAccountId = billingAccountId;
    }

    public int getAddressId() {
        return addressId;
    }

    public void setAddressId(int addressId) {
        this.addressId = addressId;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    public boolean isComplete() {
        return isComplete;
    }

    public void setComplete(boolean complete) {
        isComplete = complete;
    }

    public Order(int id, int customerId, int billingAccountId, int addressId, BigDecimal totalAmount, boolean isComplete) {
        this.id = id;
        this.customerId = customerId;
        this.billingAccountId = billingAccountId;
        this.addressId = addressId;
        this.totalAmount = totalAmount;
        this.isComplete = false;
    }

    public Order() {
        this.isComplete = false;
    }
}
