package com.etiya.catalogservice.service.abstracts;

import com.etiya.catalogservice.domain.entities.Product;
import com.etiya.catalogservice.service.dtos.requests.Product.CreateProductRequest;
import com.etiya.catalogservice.service.dtos.requests.Product.UpdateProductRequest;
import com.etiya.catalogservice.service.dtos.responses.Product.CreatedProductResponse;
import com.etiya.catalogservice.service.dtos.responses.Product.GetListProductResponse;
import com.etiya.catalogservice.service.dtos.responses.Product.UpdatedProductResponse;

import java.util.List;

public interface ProductService {

    CreatedProductResponse add(CreateProductRequest request);
    UpdatedProductResponse update(UpdateProductRequest request);
    List<GetListProductResponse> getListProductResponse();
    void delete(int id);
    void softDelete(int id);
    Product findById(int id);
}