package com.etiya.catalogservice.service.dtos.requests.ProductCharValue;

public class CreateProductCharValueRequest {

    private int charValueId;

    private int productId;

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

    public CreateProductCharValueRequest() {
    }

    public CreateProductCharValueRequest(int charValueId, int productId) {
        this.charValueId = charValueId;
        this.productId = productId;
    }
}
