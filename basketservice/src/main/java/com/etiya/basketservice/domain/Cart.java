package com.etiya.basketservice.domain;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Cart implements Serializable {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    private int billingAccountId;

    private int addressId;

    private BigDecimal totalPrice = BigDecimal.ZERO;

    private List<CartItem> cartItems =  new ArrayList<>();

    public Cart(){
        this.id= UUID.randomUUID().toString();
        this.cartItems = new ArrayList<>();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
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

    public int getAddressId() {
        return addressId;
    }

    public void setAddressId(int addressId) {
        this.addressId = addressId;
    }

    public void setCartItemList(List<CartItem> cartItemList) {
        this.cartItems = cartItemList;
    }

    public Cart(String id, int billingAccountId, int addressId, BigDecimal totalPrice, List<CartItem> cartItems) {
        this.id = id;
        this.billingAccountId = billingAccountId;
        this.addressId = addressId;
        this.totalPrice = totalPrice;
        this.cartItems = cartItems;
    }
}
