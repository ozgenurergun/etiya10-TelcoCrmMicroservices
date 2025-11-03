package com.etiya.catalogservice.service.dtos.responses.ProductCharValue;

public class UpdatedProductCharValueResponse {

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

    public UpdatedProductCharValueResponse() {
    }

    public UpdatedProductCharValueResponse(int id, int charValueId, int productId) {
        this.id = id;
        this.charValueId = charValueId;
        this.productId = productId;
    }
}
