package com.etiya.catalogservice.controller;

import com.etiya.catalogservice.service.abstracts.ProductCharValueService;
import com.etiya.catalogservice.service.dtos.requests.CharValue.UpdateCharValueRequest;
import com.etiya.catalogservice.service.dtos.requests.ProductCharValue.CreateProductCharValueRequest;
import com.etiya.catalogservice.service.dtos.requests.ProductCharValue.UpdateProductCharValueRequest;
import com.etiya.catalogservice.service.dtos.requests.ProductSpecification.CreateProductSpecificationRequest;
import com.etiya.catalogservice.service.dtos.requests.ProductSpecification.UpdateProductSpecificationRequest;
import com.etiya.catalogservice.service.dtos.responses.CharValue.GetListCharValueResponse;
import com.etiya.catalogservice.service.dtos.responses.ProductCharValue.CreatedProductCharValueResponse;
import com.etiya.catalogservice.service.dtos.responses.ProductCharValue.GetListProductCharValueResponse;
import com.etiya.catalogservice.service.dtos.responses.ProductCharValue.UpdatedProductCharValueResponse;
import com.etiya.catalogservice.service.dtos.responses.ProductSpecification.CreatedProductSpecificationResponse;
import com.etiya.catalogservice.service.dtos.responses.ProductSpecification.GetListProductSpecificationResponse;
import com.etiya.catalogservice.service.dtos.responses.ProductSpecification.UpdatedProductSpecificationResponse;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/product-char-values")
public class ProductCharValueController {

    private final ProductCharValueService productCharValueService;

    public ProductCharValueController(ProductCharValueService productCharValueService) {
        this.productCharValueService = productCharValueService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CreatedProductCharValueResponse add(@RequestBody CreateProductCharValueRequest request) {
        return productCharValueService.add(request);
    }

    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public UpdatedProductCharValueResponse update(@RequestBody UpdateProductCharValueRequest request) {
        return productCharValueService.update(request);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<GetListProductCharValueResponse> getAll() {
        return productCharValueService.getAll();
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@Valid @PathVariable int id) {
        productCharValueService.deleteById(id);
    }
}
