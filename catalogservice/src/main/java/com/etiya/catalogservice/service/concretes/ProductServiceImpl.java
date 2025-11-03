package com.etiya.catalogservice.service.concretes;

import com.etiya.catalogservice.domain.entities.Product;
import com.etiya.catalogservice.repository.ProductRepository;
import com.etiya.catalogservice.service.abstracts.ProductService;
import com.etiya.catalogservice.service.dtos.requests.Product.CreateProductRequest;
import com.etiya.catalogservice.service.dtos.responses.Product.CreatedProductResponse;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public CreatedProductResponse add(CreateProductRequest request) {
        Product mappingProduct = new Product(request.getName(),request.getPrice(),request.getStock());
        Product createdProduct = productRepository.save(mappingProduct);
        CreatedProductResponse response = new CreatedProductResponse();
        response.setId(createdProduct.getId());
        return response;
    }
}
