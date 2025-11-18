package com.etiya.salesservice.domain;

import java.math.BigDecimal;
import java.util.List;

public class OrderItem {

    private int productId;

    private int productOfferId;

    private String productName;

    private int campaignProductOfferId;

    private String campaignProductOfferName;

    private BigDecimal price;

    private BigDecimal discountRate;

    private BigDecimal discountedPrice;

    private List<OrderItemCharacteristic> characteristics;

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
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

    public int getProductOfferId() {
        return productOfferId;
    }

    public void setProductOfferId(int productOfferId) {
        this.productOfferId = productOfferId;
    }

    public String getCampaignProductOfferName() {
        return campaignProductOfferName;
    }

    public void setCampaignProductOfferName(String campaignProductOfferName) {
        this.campaignProductOfferName = campaignProductOfferName;
    }

    public List<OrderItemCharacteristic> getCharacteristics() {
        return characteristics;
    }

    public void setCharacteristics(List<OrderItemCharacteristic> characteristics) {
        this.characteristics = characteristics;
    }

    public int getCampaignProductOfferId() {
        return campaignProductOfferId;
    }

    public void setCampaignProductOfferId(int campaignProductOfferId) {
        this.campaignProductOfferId = campaignProductOfferId;
    }
}
