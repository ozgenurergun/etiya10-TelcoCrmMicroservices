package com.etiya.catalogservice.controller;

import com.etiya.catalogservice.service.abstracts.CatalogService;
import com.etiya.catalogservice.service.dtos.requests.Catalog.CreateCatalogRequest;
import com.etiya.catalogservice.service.dtos.requests.Catalog.UpdateCatalogRequest;
import com.etiya.catalogservice.service.dtos.responses.Catalog.CreatedCatalogResponse;
import com.etiya.catalogservice.service.dtos.responses.Catalog.GetListCatalogResponse;
import com.etiya.catalogservice.service.dtos.responses.Catalog.UpdatedCatalogResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/catalogs")
public class CatalogController {

    private final CatalogService catalogService;

    public CatalogController(CatalogService catalogService) {
        this.catalogService = catalogService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CreatedCatalogResponse add(@RequestBody CreateCatalogRequest request) {
        return catalogService.add(request);
    }

    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public UpdatedCatalogResponse update(@RequestBody UpdateCatalogRequest request) {
        return catalogService.update(request);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<GetListCatalogResponse> getListCatalogResponses() {
        return catalogService.getListCatalogResponse();
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable int id) {
        catalogService.delete(id);
    }

    @DeleteMapping("/{id}/soft")
    @ResponseStatus(HttpStatus.OK)
    public void softDelete(@PathVariable int id) {
        catalogService.softDelete(id);
    }
}
