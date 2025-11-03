package com.etiya.catalogservice.service.dtos.requests.Product;

import java.math.BigDecimal;

public class CreateProductRequest {

    private String name;

    private BigDecimal price;

    private int stock;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }
}
