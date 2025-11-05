package com.etiya.basketservice.service.abstracts;

import com.etiya.basketservice.domain.Cart;

import java.util.Map;

public interface CartService {

    void add(int billingAccountId, int productId,  int quantity, int productOfferId, int catalogProductOfferId, int campaignProductId);

    Map<String, Cart> getAll();

    Map<String, Cart> getByBillingAccountId(int billingAccountId);

    void deleteCart(int billingAccountId);

    void deleteItemFromCart(int billingAccountId, String cartItemId);
}
