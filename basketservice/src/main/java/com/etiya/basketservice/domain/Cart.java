package com.etiya.basketservice.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Cart implements Serializable {

    private String id;
    private String customerId; //billingAccountId olacak
    private double totalPrice;
    private List<CartItem> cartItems;

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

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public List<CartItem> getCartItemList() {
        return cartItems;
    }

    public void setCartItemList(List<CartItem> cartItemList) {
        this.cartItems = cartItemList;
    }
}
