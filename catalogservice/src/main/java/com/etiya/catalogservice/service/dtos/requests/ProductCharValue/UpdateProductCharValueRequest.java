package com.etiya.catalogservice.service.dtos.requests.ProductCharValue;

import com.etiya.catalogservice.domain.entities.CharValue;
import com.etiya.catalogservice.domain.entities.Product;
import jakarta.persistence.*;

public class UpdateProductCharValueRequest {

    private int id;

    private int charValueId;

    private int productId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCharValueId() {
        return charValueId;
    }

    public void setCharValueId(int charValueId) {
        this.charValueId = charValueId;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public UpdateProductCharValueRequest() {
    }

    public UpdateProductCharValueRequest(int id, int charValueId, int productId) {
        this.id = id;
        this.charValueId = charValueId;
        this.productId = productId;
    }
}
