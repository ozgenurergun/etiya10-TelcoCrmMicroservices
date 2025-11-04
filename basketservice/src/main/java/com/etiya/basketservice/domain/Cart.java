package com.etiya.basketservice.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Cart implements Serializable {

    private int id;

    private int billingAccountId;

    private BigDecimal totalPrice;

    private List<CartItem> cartItems;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getBillingAccountId() {
        return billingAccountId;
    }

    public void setBillingAccountId(int billingAccountId) {
        this.billingAccountId = billingAccountId;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public List<CartItem> getCartItemList() {
        return cartItems;
    }

    public void setCartItemList(List<CartItem> cartItemList) {
        this.cartItems = cartItemList;
    }

    public Cart() {
    }

    public Cart(int id, int billingAccountId, BigDecimal totalPrice, List<CartItem> cartItems) {
        this.id = id;
        this.billingAccountId = billingAccountId;
        this.totalPrice = totalPrice;
        this.cartItems = cartItems;
    }
}
