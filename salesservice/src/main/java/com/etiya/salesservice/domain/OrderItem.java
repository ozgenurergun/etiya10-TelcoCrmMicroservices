package com.etiya.salesservice.domain;

import com.etiya.common.responses.ProductResponse;

import java.math.BigDecimal;

public class OrderItem {

    private int productId;

    private int productOfferId;

    private String productOfferName;

    private int campaignProductOfferId;

    private BigDecimal price;

    private BigDecimal discountRate;

    private BigDecimal discountedPrice;


    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getProductOfferId() {
        return productOfferId;
    }

    public void setProductOfferId(int productOfferId) {
        this.productOfferId = productOfferId;
    }

    public String getProductOfferName() {
        return productOfferName;
    }

    public void setProductOfferName(String productOfferName) {
        this.productOfferName = productOfferName;
    }

    public int getCampaignProductOfferId() {
        return campaignProductOfferId;
    }

    public void setCampaignProductOfferId(int campaignProductOfferId) {
        this.campaignProductOfferId = campaignProductOfferId;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getDiscountRate() {
        return discountRate;
    }

    public void setDiscountRate(BigDecimal discountRate) {
        this.discountRate = discountRate;
    }

    public BigDecimal getDiscountedPrice() {
        return discountedPrice;
    }

    public void setDiscountedPrice(BigDecimal discountedPrice) {
        this.discountedPrice = discountedPrice;
    }
}
