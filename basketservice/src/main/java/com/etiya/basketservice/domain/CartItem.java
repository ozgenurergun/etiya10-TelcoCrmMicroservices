package com.etiya.basketservice.domain;

import com.etiya.common.responses.GetListCharacteristicWithoutCharValResponse;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
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

    private List<GetListCharacteristicWithoutCharValResponse> prodOfferCharacteristics;

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

    public List<GetListCharacteristicWithoutCharValResponse> getProdOfferCharacteristics() {
        return prodOfferCharacteristics;
    }

    public void setProdOfferCharacteristics(List<GetListCharacteristicWithoutCharValResponse> prodOfferCharacteristics) {
        this.prodOfferCharacteristics = prodOfferCharacteristics;
    }

    public int getProductSpecificationId() {
        return productSpecificationId;
    }

    public void setProductSpecificationId(int productSpecificationId) {
        this.productSpecificationId = productSpecificationId;
    }

    public CartItem() {
        this.id= UUID.randomUUID().toString();
    }

    public CartItem(String id, int productOfferId, int campaignProductOfferId, String productOfferName, String campaignName, BigDecimal price, BigDecimal discountRate, int quantity, BigDecimal discountedPrice, int productSpecificationId, List<GetListCharacteristicWithoutCharValResponse> prodOfferCharacteristics) {
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
        this.prodOfferCharacteristics = prodOfferCharacteristics;
    }
}
