package com.etiya.basketservice.service.concretes;

import com.etiya.basketservice.client.CatalogServiceClient;
import com.etiya.basketservice.client.CustomerServiceClient;
import com.etiya.basketservice.domain.Cart;
import com.etiya.basketservice.domain.CartItem;
import com.etiya.basketservice.repository.CartRepository;
import com.etiya.basketservice.service.abstracts.CartService;
import com.etiya.common.responses.AddressResponse;
import com.etiya.common.responses.BillingAccountResponse;
import com.etiya.common.responses.CampaignProductOfferResponse;
import com.etiya.common.responses.ProductOfferResponse;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Map;
import java.util.Optional;

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
    public void add(int billingAccountId, int quantity, int productOfferId, int campaignProductOfferId) {
        // 1. Gerekli servisleri çağır
        BillingAccountResponse billingAccount = customerServiceClient.getBillingAccountById(billingAccountId);
        Cart cart = cartRepository.getCartByBillingAccountId(billingAccount.getId());
        ProductOfferResponse productOffer = catalogServiceClient.getProductOfferById(productOfferId);        // 2. Kampanya ID'si 0'dan büyükse getir, değilse null ata
        CampaignProductOfferResponse campaignProduct = campaignProductOfferId > 0
                ? catalogServiceClient.getCampaignProductOfferById(campaignProductOfferId)
                : null;

        // 3. Sepet yoksa yeni sepet oluştur
        if (cart == null) {
            cart = new Cart();
            cart.setBillingAccountId(billingAccount.getId());
        }

        // 4. Sepet kalemini (CartItem) oluştur
        CartItem cartItem = new CartItem();
        cartItem.setProductOfferId(productOfferId);
        cartItem.setCampaignProductOfferId(campaignProductOfferId);
        if (campaignProduct != null) {
            cartItem.setCampaignName(campaignProduct.getName());
        }
        cartItem.setQuantity(quantity);
        cartItem.setProductOfferName(productOffer.getName());
        cartItem.setDiscountRate(productOffer.getDiscountRate());

        // ****** HATA DÜZELTMESİ (ÖNCE SET ET, SONRA KULLAN) ******

        // 5. FİYATI ve SPEC ID'Yİ TEKLİFTEN (productOffer) ALIP SET ET
        // (ProductOfferResponse'un bu iki alanı içerdiğinden emin ol)
        cartItem.setPrice(productOffer.getPrice());
        cartItem.setProdOfferCharacteristics(productOffer.getGetListCharacteristicWithoutCharValResponseList());
        cartItem.setProductSpecificationId(productOffer.getProductSpecId());

        // 6. İNDİRİMLİ FİYATI ŞİMDİ GÜVENLE HESAPLA
        // (artık cartItem.getPrice() null değil)
        cartItem.setDiscountedPrice(BigDecimal.ONE.subtract(cartItem.getDiscountRate())
                .multiply(cartItem.getPrice()));
        // ****** DÜZELTME BİTTİ ******

        // 7. Sepet toplamlarını güncelle ve Redis'e kaydet
        cart.setBillingAccountId(billingAccount.getId());
        BigDecimal itemTotalPrice = cartItem.getDiscountedPrice().multiply(BigDecimal.valueOf(cartItem.getQuantity()));
        cart.setTotalPrice(cart.getTotalPrice().add(itemTotalPrice));
        cart.getCartItemList().add(cartItem);
        cartRepository.add(cart);
    }

    @Override
    public Map<String, Cart> getAll() {
        return cartRepository.getAll();
    }

    @Override
    public Map<String, Cart> getByBillingAccountId(int billingAccountId) {
        return cartRepository.foundCartByBillingAccountId(billingAccountId);
    }

    @Override
    public void deleteCart(int billingAccountId) {
        var cart = cartRepository.getCartByBillingAccountId(billingAccountId);
        if (cart == null) {
            throw new RuntimeException("Cart not found");
        }
        String hashKey = cart.getId()+"_"+cart.getBillingAccountId();
        cartRepository.delete(hashKey);
    }

    @Override
    public void deleteItemFromCart(int billingAccountId, String cartItemId) {
        var cart = cartRepository.getCartByBillingAccountId(billingAccountId);
        if (cart == null) {
            throw new RuntimeException("Cart not found");
        }
        Optional<CartItem> itemToRemove = cart.getCartItemList().stream().filter(item -> item.getId().equals(cartItemId)).findFirst();
        if (itemToRemove.isEmpty()) {
            throw new RuntimeException("Cart item not found");
        }
        CartItem removeItem = itemToRemove.get();
        BigDecimal itemTotal = removeItem.getDiscountedPrice().multiply(BigDecimal.valueOf(removeItem.getQuantity()));
        cart.setTotalPrice(cart.getTotalPrice().subtract(itemTotal));
        cart.getCartItemList().remove(removeItem);
        cartRepository.add(cart);
    }

    @Override
    public void addAddress(int addressId, int billingAccountId) {
        BillingAccountResponse billingAccount = customerServiceClient.getBillingAccountById(billingAccountId);
        Cart cart = cartRepository.getCartByBillingAccountId(billingAccount.getId());
        AddressResponse address = customerServiceClient.getAddressById(addressId);
        cart.setAddressId(address.getId());
    }
}
