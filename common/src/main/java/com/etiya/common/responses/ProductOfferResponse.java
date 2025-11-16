package com.etiya.common.responses;

import java.math.BigDecimal;
import java.util.List;

public class ProductOfferResponse {

    private int id;

    private String name;

    private BigDecimal price;

    private BigDecimal discountRate;

    private int productSpecificationId;

    private List<GetListCharacteristicWithoutCharValResponse> getListCharacteristicWithoutCharValResponseList;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public BigDecimal getDiscountRate() {
        return discountRate;
    }

    public void setDiscountRate(BigDecimal discountRate) {
        this.discountRate = discountRate;
    }

    public List<GetListCharacteristicWithoutCharValResponse> getGetListCharacteristicWithoutCharValResponseList() {
        return getListCharacteristicWithoutCharValResponseList;
    }

    public void setGetListCharacteristicWithoutCharValResponseList(List<GetListCharacteristicWithoutCharValResponse> getListCharacteristicWithoutCharValResponseList) {
        this.getListCharacteristicWithoutCharValResponseList = getListCharacteristicWithoutCharValResponseList;
    }
}
