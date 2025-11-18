package com.etiya.catalogservice.controller;

import com.etiya.catalogservice.service.abstracts.ProductOfferService;
import com.etiya.catalogservice.service.dtos.requests.ProductOffer.CreateProductOfferRequest;
import com.etiya.catalogservice.service.dtos.requests.ProductOffer.UpdateProductOfferRequest;
import com.etiya.catalogservice.service.dtos.responses.ProductOffer.*;
import com.etiya.common.responses.ProductOfferResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/product-offers")
public class ProductOfferController {

    private final ProductOfferService productOfferService;

    public ProductOfferController(ProductOfferService productOfferService) {
        this.productOfferService = productOfferService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CreatedProductOfferResponse add(@RequestBody CreateProductOfferRequest request) {
        return productOfferService.add(request);
    }

    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public UpdatedProductOfferResponse update(@RequestBody UpdateProductOfferRequest request) {
        return productOfferService.update(request);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<GetListProductOfferResponse> getListProductOfferResponses() {
        return productOfferService.getListProductOfferResponse();
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable int id) {
        productOfferService.delete(id);
    }

    @DeleteMapping("/{id}/soft")
    @ResponseStatus(HttpStatus.OK)
    public void softDelete(@PathVariable int id) {
        productOfferService.softDelete(id);
    }

    // YENİ ENDPOINT'İ EKLE
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ProductOfferResponse getById(@PathVariable int id) {
        return productOfferService.getByIdForClient(id);
    }

    @GetMapping("/getByCatalogId/{catalogId}")
    @ResponseStatus(HttpStatus.OK)
    public List<GetProductOfferFromCatalogResponse> getByCatalogId(@PathVariable int catalogId) {
        return productOfferService.getOffersByCatalogId(catalogId);
    }

    @GetMapping("/getByCampaignId/{campaignId}")
    @ResponseStatus(HttpStatus.OK)
    public List<GetProductOfferFromCampaignResponse> getByCampaignId(@PathVariable int campaignId) {
        return productOfferService.getOffersByCampaignId(campaignId);
    }


}