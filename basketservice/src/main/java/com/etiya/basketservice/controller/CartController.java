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
    public void add(@RequestParam int billingAccountId, @RequestParam  int quantity,@RequestParam int productOfferId,
                    @RequestParam int campaignProductOfferId){
        cartService.add(billingAccountId, quantity, productOfferId, campaignProductOfferId);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Map<String, Cart> getAll(){
        return cartService.getAll();
    }

    @GetMapping("billingAccount/")
    @ResponseStatus(HttpStatus.OK)
    public Map<String, Cart> getByBillingAccountId(@RequestParam int billingAccountId){
        return cartService.getByBillingAccountId(billingAccountId);
    }

    @DeleteMapping("/delete/{billingAccountId}")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable int billingAccountId){
        cartService.deleteCart(billingAccountId);
    }

    @DeleteMapping("/{billingAccountId}/items/{cartItemId}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteItem(@PathVariable int billingAccountId, @PathVariable String cartItemId){
        cartService.deleteItemFromCart(billingAccountId, cartItemId);
    }

    @PostMapping("addAddress")
    @ResponseStatus(HttpStatus.CREATED)
    public void addAddress(@RequestParam int addressId, @RequestParam int billingAccountId){
        cartService.addAddress(addressId, billingAccountId);
    }
}
