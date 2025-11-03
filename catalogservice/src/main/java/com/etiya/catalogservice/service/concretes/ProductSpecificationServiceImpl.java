package com.etiya.catalogservice.service.concretes;

import com.etiya.catalogservice.domain.entities.ProductSpecification;
import com.etiya.catalogservice.repository.ProductSpecificationRepository;
import com.etiya.catalogservice.service.abstracts.ProductSpecificationService;
import com.etiya.catalogservice.service.dtos.requests.ProductSpecification.CreateProductSpecificationRequest;
import com.etiya.catalogservice.service.dtos.requests.ProductSpecification.UpdateProductSpecificationRequest;
import com.etiya.catalogservice.service.dtos.responses.ProductSpecification.CreatedProductSpecificationResponse;
import com.etiya.catalogservice.service.dtos.responses.ProductSpecification.GetListProductSpecificationResponse;
import com.etiya.catalogservice.service.dtos.responses.ProductSpecification.UpdatedProductSpecificationResponse;
import com.etiya.catalogservice.service.mappings.ProductSpecificationMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ProductSpecificationServiceImpl implements ProductSpecificationService {

    private final ProductSpecificationRepository productSpecificationRepository;

    public ProductSpecificationServiceImpl(ProductSpecificationRepository productSpecificationRepository) {
        this.productSpecificationRepository = productSpecificationRepository;
    }

    @Override
    public CreatedProductSpecificationResponse add(CreateProductSpecificationRequest request) {
        ProductSpecification productSpecification = ProductSpecificationMapper.INSTANCE.productSpecificationFromCreateProductSpecificationRequest(request);
        ProductSpecification createdProductSpecification = productSpecificationRepository.save(productSpecification);
        CreatedProductSpecificationResponse response = ProductSpecificationMapper.INSTANCE.createdProductSpecificationResponseFromProductSpecification(createdProductSpecification);
        return response;
    }

    @Override
    public UpdatedProductSpecificationResponse update(UpdateProductSpecificationRequest request) {
        ProductSpecification oldProductSpecification = productSpecificationRepository.findById(request.getId()).orElseThrow(() -> new RuntimeException("Product Specification not found"));

        ProductSpecification productSpecification = ProductSpecificationMapper.INSTANCE.productSpecificationFromUpdateProductSpecificationRequest(request, oldProductSpecification);
        ProductSpecification updateProductSpecification = productSpecificationRepository.save(productSpecification);

        UpdatedProductSpecificationResponse response = ProductSpecificationMapper.INSTANCE.updatedProductSpecificationResponseFromProductSpecification(updateProductSpecification);
        return response;
    }

    @Override
    public List<GetListProductSpecificationResponse> getAll() {
        List<ProductSpecification> productSpecifications = productSpecificationRepository.findAll();
        List<GetListProductSpecificationResponse> responses = ProductSpecificationMapper.INSTANCE.getListProductSpecificationResponseFromProductSpecification(productSpecifications);
        return responses;
    }


    @Override
    public void deleteById(int id) {
        ProductSpecification productSpecification = productSpecificationRepository.findById(id).orElseThrow(() -> new RuntimeException("Product Specification not found"));

        productSpecification.setDeletedDate(LocalDateTime.now());
        productSpecification.setIsActive(0);
        productSpecificationRepository.save(productSpecification);
    }
}
