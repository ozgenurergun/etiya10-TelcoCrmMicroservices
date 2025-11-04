package com.etiya.catalogservice.service.dtos.requests.CatalogProductOffer;

public class UpdateCatalogProductOfferRequest {
    private int id; // Hangi kaydın güncelleneceği
    private int catalogId;
    private int productOfferId;

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public int getCatalogId() { return catalogId; }
    public void setCatalogId(int catalogId) { this.catalogId = catalogId; }
    public int getProductOfferId() { return productOfferId; }
    public void setProductOfferId(int productOfferId) { this.productOfferId = productOfferId; }
}