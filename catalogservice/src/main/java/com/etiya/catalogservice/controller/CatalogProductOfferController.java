package com.etiya.catalogservice.controller;

import com.etiya.catalogservice.service.abstracts.CatalogProductOfferService;
import com.etiya.catalogservice.service.dtos.requests.CatalogProductOffer.CreateCatalogProductOfferRequest;
import com.etiya.catalogservice.service.dtos.requests.CatalogProductOffer.UpdateCatalogProductOfferRequest;
import com.etiya.catalogservice.service.dtos.responses.CatalogProductOffer.CreatedCatalogProductOfferResponse;
import com.etiya.catalogservice.service.dtos.responses.CatalogProductOffer.GetListCatalogProductOfferResponse;
import com.etiya.catalogservice.service.dtos.responses.CatalogProductOffer.UpdatedCatalogProductOfferResponse;
import com.etiya.common.responses.CatalogProductOfferResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/catalog-product-offers")
public class CatalogProductOfferController {

    private final CatalogProductOfferService catalogProductOfferService;

    public CatalogProductOfferController(CatalogProductOfferService catalogProductOfferService) {
        this.catalogProductOfferService = catalogProductOfferService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CreatedCatalogProductOfferResponse add(@RequestBody CreateCatalogProductOfferRequest request) {
        return catalogProductOfferService.add(request);
    }

    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public UpdatedCatalogProductOfferResponse update(@RequestBody UpdateCatalogProductOfferRequest request) {
        return catalogProductOfferService.update(request);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<GetListCatalogProductOfferResponse> getListCatalogProductOfferResponses() {
        return catalogProductOfferService.getListCatalogProductOfferResponse();
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable int id) {
        catalogProductOfferService.delete(id);
    }

    @DeleteMapping("/{id}/soft")
    @ResponseStatus(HttpStatus.OK)
    public void softDelete(@PathVariable int id) {
        catalogProductOfferService.softDelete(id);
    }

    // YENİ ENDPOINT'İ EKLE
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public CatalogProductOfferResponse getById(@PathVariable int id) {
        return catalogProductOfferService.getByIdForClient(id);
    }


}
