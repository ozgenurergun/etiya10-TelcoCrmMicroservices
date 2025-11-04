package com.etiya.catalogservice.service.concretes;

import com.etiya.catalogservice.domain.entities.GENELSTATUS;
import com.etiya.catalogservice.domain.entities.GENELTYPE;
import com.etiya.catalogservice.domain.entities.ProductSpecification;
import com.etiya.catalogservice.repository.ProductSpecificationRepository;
import com.etiya.catalogservice.service.abstracts.GenelStatusService;
import com.etiya.catalogservice.service.abstracts.GenelTypeService;
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
    private final GenelTypeService  genelTypeService;
    private final GenelStatusService genelStatusService;

    public ProductSpecificationServiceImpl(ProductSpecificationRepository productSpecificationRepository, GenelTypeService genelTypeService, GenelStatusService genelStatusService) {
        this.productSpecificationRepository = productSpecificationRepository;
        this.genelTypeService = genelTypeService;
        this.genelStatusService = genelStatusService;
    }

    @Override
    public CreatedProductSpecificationResponse add(CreateProductSpecificationRequest request) {
        ProductSpecification productSpecification = ProductSpecificationMapper.INSTANCE.productSpecificationFromCreateProductSpecificationRequest(request);

        GENELSTATUS genelStatus = genelStatusService.findById(request.getGenelStatusId());
        GENELTYPE genelType = genelTypeService.findById(request.getGenelTypeId());

        productSpecification.setGenelStatus(genelStatus);
        productSpecification.setGenelType(genelType);

        ProductSpecification createdProductSpecification = productSpecificationRepository.save(productSpecification);
        CreatedProductSpecificationResponse response = ProductSpecificationMapper.INSTANCE.createdProductSpecificationResponseFromProductSpecification(createdProductSpecification);
        return response;
    }

    @Override
    public UpdatedProductSpecificationResponse update(UpdateProductSpecificationRequest request) {
        ProductSpecification oldProductSpecification = productSpecificationRepository.findById(request.getId()).orElseThrow(() -> new RuntimeException("Product Specification not found"));

        GENELSTATUS genelStatus = genelStatusService.findById(request.getGenelStatusId());
        GENELTYPE genelType = genelTypeService.findById(request.getGenelTypeId());

        ProductSpecification productSpecification = ProductSpecificationMapper.INSTANCE.productSpecificationFromUpdateProductSpecificationRequest(request, oldProductSpecification);

        productSpecification.setGenelStatus(genelStatus);
        productSpecification.setGenelType(genelType);

        productSpecificationRepository.save(productSpecification);

        UpdatedProductSpecificationResponse response = ProductSpecificationMapper.INSTANCE.updatedProductSpecificationResponseFromProductSpecification(productSpecification);
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

    @Override
    public ProductSpecification findById(int id) {
        return productSpecificationRepository.findById(id).orElseThrow(() -> new RuntimeException("Product Specification not found"));
    }
}
