package com.etiya.basketservice.service.abstracts;

import com.etiya.basketservice.domain.Cart;
import com.etiya.common.responses.CharValueForCharResponse;
import com.etiya.common.responses.GetListCharacteristicWithoutCharValResponse;

import java.util.List;
import java.util.Map;

public interface CartService {

    void add(int billingAccountId, int quantity, int productOfferId, int campaignProductOfferId);

    Map<String, Cart> getAll();

    Map<String, Cart> getByBillingAccountId(int billingAccountId);

    void deleteCart(int billingAccountId);

    void deleteItemFromCart(int billingAccountId, String cartItemId);

    void addAddress(int addressId, int billingAccountId);

    void updateItemCharacteristics(int billingAccountId, String cartItemId, List<GetListCharacteristicWithoutCharValResponse> responses);

    void updateCartAddress(int addressId, int billingAccountId);
}
