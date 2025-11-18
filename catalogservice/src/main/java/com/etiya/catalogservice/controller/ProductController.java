package com.etiya.catalogservice.controller;

import com.etiya.catalogservice.service.abstracts.ProductService;
import com.etiya.catalogservice.service.concretes.ProductOrchestratorService;
import com.etiya.catalogservice.service.dtos.requests.Product.UpdateProductRequest;
import com.etiya.catalogservice.service.dtos.responses.Product.GetListProductResponse;
import com.etiya.catalogservice.service.dtos.responses.Product.UpdatedProductResponse;
import com.etiya.common.requests.CreateProductRequest;
import com.etiya.common.responses.ProductResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final ProductService productService;
    private final ProductOrchestratorService  productOrchestratorService;

    public ProductController(ProductService productService, ProductOrchestratorService productOrchestratorService) {
        this.productService = productService;
        this.productOrchestratorService = productOrchestratorService;
    }

    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public UpdatedProductResponse update(@RequestBody UpdateProductRequest request) {
        return productService.update(request);
    }

    @PostMapping("/createProduct")
    @ResponseStatus(HttpStatus.CREATED)
    public ProductResponse createProduct(@RequestBody CreateProductRequest request) {
        return productOrchestratorService.createProduct(request);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<GetListProductResponse> getListProductResponses() {
        return productService.getListProductResponse();
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable int id) {
        productService.delete(id);
    }

    @DeleteMapping("/{id}/soft")
    @ResponseStatus(HttpStatus.OK)
    public void softDelete(@PathVariable int id) {
        productService.softDelete(id);
    }

    // YENİ ENDPOINT'İ EKLE
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ProductResponse getById(@PathVariable int id) {
        return productService.getByIdForClient(id);
    }
}