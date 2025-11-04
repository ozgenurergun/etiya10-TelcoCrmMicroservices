package com.etiya.catalogservice.service.dtos.responses.CampaignProduct;

public class CreatedCampaignProductResponse {
    private int id;
    private int campaignId;
    private int productId;

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public int getCampaignId() {
        return campaignId;
    }
    public void setCampaignId(int campaignId) {
        this.campaignId = campaignId;
    }
    public int getProductId() {
        return productId;
    }
    public void setProductId(int productId) {
        this.productId = productId;
    }
}
