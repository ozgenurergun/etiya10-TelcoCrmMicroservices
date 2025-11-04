package com.etiya.basketservice.client;

import com.etiya.common.responses.CampaignProductResponse;
import com.etiya.common.responses.CatalogOfferResponse;
import com.etiya.common.responses.ProductOfferResponse;
import com.etiya.common.responses.ProductResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "catalogservice")
public interface CatalogServiceClient {

    @GetMapping("/api/products/{id}")
    ProductResponse getProductById(@PathVariable("id") int id);

    @GetMapping("/api/product-offers/{id}")
    ProductOfferResponse getProductOfferById(@PathVariable("id") int id);

    @GetMapping("/api/catalog-product-offers/{id}")
    CatalogOfferResponse  getCatalogOfferById(@PathVariable("id") int id);

    @GetMapping("/api/campaign-products/{id}")
    CampaignProductResponse getCampaignProductById(@PathVariable("id") int id);
}
