package com.etiya.basketservice.repository;

import com.etiya.basketservice.domain.Cart;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Repository
public class CartRepository {

    public  static final String Key = "CART";

    private final RedisTemplate<String, Object> redisTemplate;
    private final HashOperations<String, String, Cart> cartHashOperations;

    public CartRepository(RedisTemplate<String, Object> redisTemplate){
        this.redisTemplate = redisTemplate;
        this.cartHashOperations = redisTemplate.opsForHash();
    }

    public void add(Cart cart){
        this.cartHashOperations.put(Key, cart.getId()+"_"+cart.getBillingAccountId(), cart);
    }

    public Cart getCartByBillingAccountId(int billingAccountId){
        return this.cartHashOperations.entries(Key).values().stream().filter(cart -> billingAccountId==cart.getBillingAccountId())
                .findFirst().orElse(null);
    }

    public Map<String, Cart> getAll(){
        return this.cartHashOperations.entries(Key);
    }
}
