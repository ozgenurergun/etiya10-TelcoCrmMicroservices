package com.etiya.catalogservice.service.abstracts;

import com.etiya.catalogservice.service.dtos.requests.ProductSpecification.CreateProductSpecificationRequest;
import com.etiya.catalogservice.service.dtos.requests.ProductSpecification.UpdateProductSpecificationRequest;
import com.etiya.catalogservice.service.dtos.responses.ProductSpecification.CreatedProductSpecificationResponse;
import com.etiya.catalogservice.service.dtos.responses.ProductSpecification.GetListProductSpecificationResponse;
import com.etiya.catalogservice.service.dtos.responses.ProductSpecification.UpdatedProductSpecificationResponse;

import java.util.List;

public interface ProductSpecificationService {

    CreatedProductSpecificationResponse add(CreateProductSpecificationRequest request);

    UpdatedProductSpecificationResponse update(UpdateProductSpecificationRequest request);

    List<GetListProductSpecificationResponse> getAll();

    void deleteById(int id);
}
