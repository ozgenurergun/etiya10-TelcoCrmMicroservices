package com.etiya.catalogservice.service.dtos.responses.ProductOffer;

import com.etiya.common.responses.GetListCharacteristicWithoutCharValResponse;

import java.math.BigDecimal;
import java.util.List;

public class GetProductOfferFromCatalogResponse {
    private int id;

    private int catalogProductOfferId;

    private String name;

    private BigDecimal discountRate;

    private BigDecimal price;

    private int productSpecificationId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public int getProductSpecificationId() {
        return productSpecificationId;
    }

    public void setProductSpecificationId(int productSpecificationId) {
        this.productSpecificationId = productSpecificationId;
    }


    public int getCatalogProductOfferId() {
        return catalogProductOfferId;
    }

    public void setCatalogProductOfferId(int catalogProductOfferId) {
        this.catalogProductOfferId = catalogProductOfferId;
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
