package com.etiya.basketservice.service.concretes;

import com.etiya.basketservice.client.CatalogServiceClient;
import com.etiya.basketservice.client.CustomerServiceClient;
import com.etiya.basketservice.domain.Cart;
import com.etiya.basketservice.domain.CartItem;
import com.etiya.basketservice.repository.CartRepository;
import com.etiya.basketservice.service.abstracts.CartService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Map;

@Service
public class CartServiceImpl implements CartService {

    private final CartRepository cartRepository;
    private final CatalogServiceClient catalogServiceClient;
    private final CustomerServiceClient customerServiceClient;

    public CartServiceImpl(CartRepository cartRepository, CatalogServiceClient catalogServiceClient, CustomerServiceClient customerServiceClient) {
        this.cartRepository = cartRepository;
        this.catalogServiceClient = catalogServiceClient;
        this.customerServiceClient = customerServiceClient;
    }

    @Override
    public void add(int billingAccountId, int productId, int quantity, int productOfferId, int catalogProductOfferId, int campaignProductId) {
        var billingAccount = customerServiceClient.getBillingAccountById(billingAccountId);
        var product = catalogServiceClient.getProductById(productId);
        var cart = cartRepository.getCartByBillingAccountId(billingAccount.getId());
        var productOffer = catalogServiceClient.getProductOfferById(productOfferId);
        var campaignProduct = catalogServiceClient.getCampaignProductById(campaignProductId);

        if (cart == null) {
            cart = new Cart();
            cart.setBillingAccountId(billingAccount.getId());
        }

        CartItem cartItem = new CartItem();
        cartItem.setProductId(product.getId());
        cartItem.setProductOfferId(productOfferId);
        cartItem.setCatalogProductOfferId(catalogProductOfferId);
        cartItem.setCampaignProductId(campaignProductId);
        cartItem.setQuantity(quantity);
        cartItem.setProductName(product.getName());
        cartItem.setProductOfferName(productOffer.getName());
        cartItem.setCampaignProductName(campaignProduct.getName());
        cartItem.setPrice(product.getPrice());
        cartItem.setDiscountRate(productOffer.getDiscountRate());
        cartItem.setDiscountedPrice(BigDecimal.ONE.subtract(cartItem.getDiscountRate()) // (1 - discountRate)
                .multiply(cartItem.getPrice()));          // * price);


        cart.setBillingAccountId(billingAccount.getId());
        cart.setTotalPrice(cart.getTotalPrice().add(cartItem.getDiscountedPrice()
                                .multiply(BigDecimal.valueOf(cartItem.getQuantity())))
        );
        cart.getCartItemList().add(cartItem);
        cartRepository.add(cart);
    }

    @Override
    public Map<String, Cart> getAll() {
        return cartRepository.getAll();
    }
}
