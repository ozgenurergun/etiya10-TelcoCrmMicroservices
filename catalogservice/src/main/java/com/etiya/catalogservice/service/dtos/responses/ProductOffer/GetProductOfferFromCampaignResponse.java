package com.etiya.catalogservice.service.dtos.responses.ProductOffer;

import java.math.BigDecimal;

public class GetProductOfferFromCampaignResponse {
    private int id;
    private int productId;
    private int campaignProductId;
    private String name;
    private BigDecimal discountRate;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getCampaignProductId() {
        return campaignProductId;
    }

    public void setCampaignProductId(int campaignProductId) {
        this.campaignProductId = campaignProductId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getDiscountRate() {
        return discountRate;
    }

    public void setDiscountRate(BigDecimal discountRate) {
        this.discountRate = discountRate;
    }
}
