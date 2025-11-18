package com.etiya.salesservice.domain;

import com.etiya.common.entities.BaseEntity;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Document(collection = "orders")
public class Order extends BaseEntity {
    @Id
    private String id;
    private LocalDateTime orderDate;
    private String status;
    private BigDecimal totalAmount;
    private OrderCustomer customerSnapshot;
    private OrderAddress addressSnapshot;
    private OrderBillingAccount billingAccountSnapshot;

    private List<OrderItem> items;

    public Order() {
        this.orderDate = LocalDateTime.now();
        this.status = "COMPLETED";
    }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public OrderCustomer getCustomerSnapshot() { return customerSnapshot; }
    public void setCustomerSnapshot(OrderCustomer customerSnapshot) { this.customerSnapshot = customerSnapshot; }
    public OrderAddress getAddressSnapshot() { return addressSnapshot; }
    public void setAddressSnapshot(OrderAddress addressSnapshot) { this.addressSnapshot = addressSnapshot; }
    public OrderBillingAccount getBillingAccountSnapshot() { return billingAccountSnapshot; }
    public void setBillingAccountSnapshot(OrderBillingAccount billingAccountSnapshot) { this.billingAccountSnapshot = billingAccountSnapshot; }
    public List<OrderItem> getItems() { return items; }
    public void setItems(List<OrderItem> items) { this.items = items; }
    public BigDecimal getTotalAmount() { return totalAmount; }
    public void setTotalAmount(BigDecimal totalAmount) { this.totalAmount = totalAmount; }
}