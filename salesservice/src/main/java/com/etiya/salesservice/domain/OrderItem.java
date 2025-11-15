package com.etiya.salesservice.domain;

import com.etiya.common.responses.ProductResponse;

import java.math.BigDecimal;

public class OrderItem {
    private int orderId;
    private int productId;
    private int productOfferId;
    private int campaignProductOfferId;
    private BigDecimal price;
    private BigDecimal discountRate;
    private BigDecimal discountedPrice;


}
