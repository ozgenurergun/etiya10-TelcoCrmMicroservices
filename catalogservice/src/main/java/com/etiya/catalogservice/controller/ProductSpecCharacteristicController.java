package com.etiya.catalogservice.controller;

import com.etiya.catalogservice.repository.ProductSpecCharacteristicRepository;
import com.etiya.catalogservice.service.abstracts.ProductSpecCharacteristicService;
import com.etiya.catalogservice.service.dtos.requests.ProductSpecCharacteristic.CreateProductSpecCharacteristicRequest;
import com.etiya.catalogservice.service.dtos.requests.ProductSpecCharacteristic.UpdateProductSpecCharacteristicRequest;
import com.etiya.catalogservice.service.dtos.requests.ProductSpecification.CreateProductSpecificationRequest;
import com.etiya.catalogservice.service.dtos.requests.ProductSpecification.UpdateProductSpecificationRequest;
import com.etiya.catalogservice.service.dtos.responses.ProductSpecCharacteristic.CreatedProductSpecCharacteristicResponse;
import com.etiya.catalogservice.service.dtos.responses.ProductSpecCharacteristic.GetListProductSpecCharacteristicResponse;
import com.etiya.catalogservice.service.dtos.responses.ProductSpecCharacteristic.UpdatedProductSpecCharacteristicResponse;
import com.etiya.catalogservice.service.dtos.responses.ProductSpecification.CreatedProductSpecificationResponse;
import com.etiya.catalogservice.service.dtos.responses.ProductSpecification.GetListProductSpecificationResponse;
import com.etiya.catalogservice.service.dtos.responses.ProductSpecification.UpdatedProductSpecificationResponse;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/product-spec-characteristics")
public class ProductSpecCharacteristicController {

    private final ProductSpecCharacteristicService productSpecCharacteristicService;

    public ProductSpecCharacteristicController(ProductSpecCharacteristicService productSpecCharacteristicService) {
        this.productSpecCharacteristicService = productSpecCharacteristicService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CreatedProductSpecCharacteristicResponse add(@RequestBody CreateProductSpecCharacteristicRequest request) {
        return productSpecCharacteristicService.add(request);
    }

    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public UpdatedProductSpecCharacteristicResponse update(@RequestBody UpdateProductSpecCharacteristicRequest request) {
        return productSpecCharacteristicService.update(request);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<GetListProductSpecCharacteristicResponse> getAll() {
        return productSpecCharacteristicService.getAll();
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@Valid @PathVariable int id) {
        productSpecCharacteristicService.deleteById(id);
    }
}
