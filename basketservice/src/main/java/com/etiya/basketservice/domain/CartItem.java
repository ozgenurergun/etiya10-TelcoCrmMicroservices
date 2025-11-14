package com.etiya.basketservice.domain;

import jakarta.persistence.CascadeType;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.UUID;

public class CartItem implements Serializable {

    private String id;

    private int productOfferId;

    private int campaignProductOfferId;

    private String productOfferName;

    private String campaignName;

    private BigDecimal price;

    private BigDecimal discountRate;

    private int quantity;

    private BigDecimal discountedPrice;

    private int productSpecificationId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getProductOfferId() {
        return productOfferId;
    }

    public void setProductOfferId(int productOfferId) {
        this.productOfferId = productOfferId;
    }

    public int getCampaignProductOfferId() {
        return campaignProductOfferId;
    }

    public void setCampaignProductOfferId(int campaignProductOfferId) {
        this.campaignProductOfferId = campaignProductOfferId;
    }

    public String getProductOfferName() {
        return productOfferName;
    }

    public void setProductOfferName(String productOfferName) {
        this.productOfferName = productOfferName;
    }

    public String getCampaignName() {
        return campaignName;
    }

    public void setCampaignName(String campaignName) {
        this.campaignName = campaignName;
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

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getDiscountedPrice() {
        return discountedPrice;
    }

    public void setDiscountedPrice(BigDecimal discountedPrice) {
        this.discountedPrice = discountedPrice;
    }

    public int getProductSpecificationId() {
        return productSpecificationId;
    }

    public void setProductSpecificationId(int productSpecificationId) {
        this.productSpecificationId = productSpecificationId;
    }

    public CartItem() {
    }

    public CartItem(String id, int productOfferId, int campaignProductOfferId, String productOfferName, String campaignName, BigDecimal price, BigDecimal discountRate, int quantity, BigDecimal discountedPrice, int productSpecificationId) {
        this.id = id;
        this.productOfferId = productOfferId;
        this.campaignProductOfferId = campaignProductOfferId;
        this.productOfferName = productOfferName;
        this.campaignName = campaignName;
        this.price = price;
        this.discountRate = discountRate;
        this.quantity = quantity;
        this.discountedPrice = discountedPrice;
        this.productSpecificationId = productSpecificationId;
    }
}
