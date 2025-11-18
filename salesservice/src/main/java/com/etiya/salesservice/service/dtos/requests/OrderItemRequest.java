package com.etiya.salesservice.service.dtos.requests;

import java.util.List;

public class OrderItemRequest {
    private int productOfferId;
    private int quantity;
    private List<ProductCharacteristicRequest> characteristics;
    private int campaignOfferId;
    private String campaignOfferName;

    // Getter - Setter
    public int getProductOfferId() { return productOfferId; }
    public void setProductOfferId(int productOfferId) { this.productOfferId = productOfferId; }
    public int getQuantity() { return quantity; }
    public void setQuantity(int quantity) { this.quantity = quantity; }
    public List<ProductCharacteristicRequest> getCharacteristics() { return characteristics; }
    public void setCharacteristics(List<ProductCharacteristicRequest> characteristics) { this.characteristics = characteristics; }

    public int getCampaignOfferId() {
        return campaignOfferId;
    }

    public void setCampaignOfferId(int campaignOfferId) {
        this.campaignOfferId = campaignOfferId;
    }

    public String getCampaignOfferName() {
        return campaignOfferName;
    }

    public void setCampaignOfferName(String campaignOfferName) {
        this.campaignOfferName = campaignOfferName;
    }
}

