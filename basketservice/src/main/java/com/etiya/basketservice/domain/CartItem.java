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

    private int productId;

    private int productOfferId;

    private int catalogProductOfferId;

    private int campaignProductId;

    private String productName;

    private String productOfferName;

    private String campaignProductName;

    private BigDecimal price;

    private BigDecimal discountRate;

    private int quantity;

    private BigDecimal discountedPrice;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

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

    public int getCatalogProductOfferId() {
        return catalogProductOfferId;
    }

    public void setCatalogProductOfferId(int catalogProductOfferId) {
        this.catalogProductOfferId = catalogProductOfferId;
    }

    public int getCampaignProductId() {
        return campaignProductId;
    }

    public void setCampaignProductId(int campaignProductId) {
        this.campaignProductId = campaignProductId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductOfferName() {
        return productOfferName;
    }

    public void setProductOfferName(String productOfferName) {
        this.productOfferName = productOfferName;
    }

    public String getCampaignProductName() {
        return campaignProductName;
    }

    public void setCampaignProductName(String campaignProductName) {
        this.campaignProductName = campaignProductName;
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

    public CartItem() {
        this.id= UUID.randomUUID().toString();
    }

    public CartItem(String id, int productId, int productOfferId, int catalogProductOfferId, int campaignProductId, BigDecimal discountRate, int quantity) {
        this.id = id;
        this.productId = productId;
        this.productOfferId = productOfferId;
        this.catalogProductOfferId = catalogProductOfferId;
        this.campaignProductId = campaignProductId;
        this.discountRate = discountRate;
        this.quantity = quantity;
    }
}
