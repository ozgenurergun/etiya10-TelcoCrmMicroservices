package com.etiya.catalogservice.service.abstracts;

import com.etiya.catalogservice.service.dtos.requests.Product.CreateProductRequest;
import com.etiya.catalogservice.service.dtos.responses.Product.CreatedProductResponse;

public interface ProductService {

    CreatedProductResponse add(CreateProductRequest request);
}
