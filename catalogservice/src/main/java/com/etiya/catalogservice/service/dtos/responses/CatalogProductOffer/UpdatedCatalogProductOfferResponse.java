package com.etiya.catalogservice.service.dtos.responses.CatalogProductOffer;

public class UpdatedCatalogProductOfferResponse {
    private int id;
    private int catalogId;
    private int productOfferId;

    public int getProductOfferId() {
        return productOfferId;
    }

    public void setProductOfferId(int productOfferId) {
        this.productOfferId = productOfferId;
    }

    public int getCatalogId() {
        return catalogId;
    }

    public void setCatalogId(int catalogId) {
        this.catalogId = catalogId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}

