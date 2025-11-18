package com.etiya.catalogservice.service.abstracts;

import com.etiya.catalogservice.domain.entities.Product;
import com.etiya.catalogservice.service.dtos.requests.Product.UpdateProductRequest;
import com.etiya.catalogservice.service.dtos.responses.Product.GetListProductResponse;
import com.etiya.catalogservice.service.dtos.responses.Product.UpdatedProductResponse;
import com.etiya.common.requests.CreateProductRequest;
import com.etiya.common.responses.ProductResponse;

import java.util.List;

public interface ProductService {

    UpdatedProductResponse update(UpdateProductRequest request);
    List<GetListProductResponse> getListProductResponse();
    void delete(int id);
    void softDelete(int id);
    Product findById(int id);

    ProductResponse getByIdForClient(int id); // <-- YENİ METOT

    Product add(CreateProductRequest request);
   }