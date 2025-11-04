package com.etiya.catalogservice.service.dtos.requests.CatalogProductOffer;

public class CreateCatalogProductOfferRequest {
    private int catalogId;
    private int productOfferId;

    // --- Getter ve Setter'lar ---
    public int getCatalogId() { return catalogId; }
    public void setCatalogId(int catalogId) { this.catalogId = catalogId; }
    public int getProductOfferId() { return productOfferId; }
    public void setProductOfferId(int productOfferId) { this.productOfferId = productOfferId; }
}
