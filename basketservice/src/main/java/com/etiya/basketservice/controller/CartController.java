package com.etiya.basketservice.controller;

import com.etiya.basketservice.domain.Cart;
import com.etiya.basketservice.service.abstracts.CartService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/carts/")
public class CartController {

    private final CartService cartService;

    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void add(@RequestParam int billingAccountId,@RequestParam int productId,@RequestParam  int quantity,@RequestParam int productOfferId,
                    @RequestParam int catalogProductOfferId, @RequestParam int campaignProductId){
        cartService.add(billingAccountId, productId,  quantity, productOfferId, catalogProductOfferId, campaignProductId);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Map<String, Cart> getAll(){
        return cartService.getAll();
    }
}
