package com.etiya.salesservice.service.dtos.responses;

import java.math.BigDecimal;
import java.util.List;

public class OrderItemResponse {
    private int productId;
    private String productName;
    private BigDecimal price;
    private BigDecimal discountedPrice;
    private List<OrderItemCharacteristicResponse> characteristics;

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getDiscountedPrice() {
        return discountedPrice;
    }

    public void setDiscountedPrice(BigDecimal discountedPrice) {
        this.discountedPrice = discountedPrice;
    }

    public List<OrderItemCharacteristicResponse> getCharacteristics() {
        return characteristics;
    }

    public void setCharacteristics(List<OrderItemCharacteristicResponse> characteristics) {
        this.characteristics = characteristics;
    }
}
