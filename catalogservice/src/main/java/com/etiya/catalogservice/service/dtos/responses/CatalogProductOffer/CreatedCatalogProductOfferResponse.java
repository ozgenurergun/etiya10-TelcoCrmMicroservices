package com.etiya.catalogservice.service.dtos.responses.CatalogProductOffer;

public class CreatedCatalogProductOfferResponse {
    private int id;
    private int catalogId;
    private int productOfferId;

    // --- Getter ve Setter'lar ---
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public int getCatalogId() { return catalogId; }
    public void setCatalogId(int catalogId) { this.catalogId = catalogId; }
    public int getProductOfferId() { return productOfferId; }
    public void setProductOfferId(int productOfferId) { this.productOfferId = productOfferId; }
}