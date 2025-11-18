package com.etiya.salesservice.client;


import com.etiya.common.requests.CreateProductRequest;
import com.etiya.common.responses.ProductOfferResponse;
import com.etiya.common.responses.ProductResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "catalogservice")
public interface CatalogServiceClient {

    @GetMapping("/api/products/{id}")
    @ResponseStatus(HttpStatus.OK)
    ProductResponse getById(@PathVariable int id);

    @GetMapping("/api/product-offers/{id}")
    ProductOfferResponse getProductOfferById(@PathVariable("id") int id);

    // Sales, Catalog'a "Ürün Yarat" emri veriyor.
    @PostMapping("/api/products/createProduct")
    ProductResponse createProduct(@RequestBody CreateProductRequest request);
}
