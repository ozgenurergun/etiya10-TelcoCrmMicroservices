package com.etiya.catalogservice.service.concretes;

import com.etiya.catalogservice.domain.entities.ProductSpecCharacteristic;
import com.etiya.catalogservice.domain.entities.ProductSpecification;
import com.etiya.catalogservice.repository.ProductSpecCharacteristicRepository;
import com.etiya.catalogservice.service.abstracts.ProductSpecCharacteristicService;
import com.etiya.catalogservice.service.dtos.requests.ProductSpecCharacteristic.CreateProductSpecCharacteristicRequest;
import com.etiya.catalogservice.service.dtos.requests.ProductSpecCharacteristic.UpdateProductSpecCharacteristicRequest;
import com.etiya.catalogservice.service.dtos.responses.ProductSpecCharacteristic.CreatedProductSpecCharacteristicResponse;
import com.etiya.catalogservice.service.dtos.responses.ProductSpecCharacteristic.GetListProductSpecCharacteristicResponse;
import com.etiya.catalogservice.service.dtos.responses.ProductSpecCharacteristic.UpdatedProductSpecCharacteristicResponse;
import com.etiya.catalogservice.service.dtos.responses.ProductSpecification.CreatedProductSpecificationResponse;
import com.etiya.catalogservice.service.dtos.responses.ProductSpecification.GetListProductSpecificationResponse;
import com.etiya.catalogservice.service.dtos.responses.ProductSpecification.UpdatedProductSpecificationResponse;
import com.etiya.catalogservice.service.mappings.ProductSpecCharacteristicMapper;
import com.etiya.catalogservice.service.mappings.ProductSpecificationMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ProductSpecCharacteristicServiceImpl implements ProductSpecCharacteristicService {

    private final ProductSpecCharacteristicRepository productSpecCharacteristicRepository;
    private final ProductSpecCharacteristicMapper productSpecCharacteristicMapper;

    public ProductSpecCharacteristicServiceImpl(ProductSpecCharacteristicRepository productSpecCharacteristicRepository, ProductSpecCharacteristicMapper productSpecCharacteristicMapper) {
        this.productSpecCharacteristicRepository = productSpecCharacteristicRepository;
        this.productSpecCharacteristicMapper = productSpecCharacteristicMapper;
    }


    @Override
    public CreatedProductSpecCharacteristicResponse add(CreateProductSpecCharacteristicRequest request) {
        ProductSpecCharacteristic productSpecCharacteristic = ProductSpecCharacteristicMapper.INSTANCE.productSpecCharacteristicFromCreateProductSpecCharacteristicRequest(request);
        ProductSpecCharacteristic createdProductSpecCharacteristic = productSpecCharacteristicRepository.save(productSpecCharacteristic);
        CreatedProductSpecCharacteristicResponse response = ProductSpecCharacteristicMapper.INSTANCE.createdProductSpecCharacteristicResponseFromProductSpecCharacteristic(createdProductSpecCharacteristic);
        return response;    }

    @Override
    public UpdatedProductSpecCharacteristicResponse update(UpdateProductSpecCharacteristicRequest request) {
        ProductSpecCharacteristic oldProductSpecCharacteristic = productSpecCharacteristicRepository.findById(request.getId()).orElseThrow(() -> new RuntimeException("Product Specification Char not found"));

        ProductSpecCharacteristic productSpecCharacteristic = ProductSpecCharacteristicMapper.INSTANCE.productSpecCharacteristicFromUpdateProductSpecCharacteristicRequest(request, oldProductSpecCharacteristic);
        ProductSpecCharacteristic updateProductSpecCharacteristic = productSpecCharacteristicRepository.save(productSpecCharacteristic);

        UpdatedProductSpecCharacteristicResponse response = ProductSpecCharacteristicMapper.INSTANCE.updatedProductSpecCharacteristicResponseFromProductSpecCharacteristic(updateProductSpecCharacteristic);
        return response;
    }

    @Override
    public List<GetListProductSpecCharacteristicResponse> getAll() {
        List<ProductSpecCharacteristic> productSpecCharacteristics = productSpecCharacteristicRepository.findAll();
        List<GetListProductSpecCharacteristicResponse> responses = productSpecCharacteristicMapper.INSTANCE.getListProductSpecCharacteristicResponseFromProductSpecCharacteristic(productSpecCharacteristics);
        return responses;
    }

    @Override
    public void deleteById(int id) {
        ProductSpecCharacteristic productSpecCharacteristic = productSpecCharacteristicRepository.findById(id).orElseThrow(() -> new RuntimeException("Product Specification Char not found"));

        productSpecCharacteristic.setDeletedDate(LocalDateTime.now());
        productSpecCharacteristic.setIsActive(0);
        productSpecCharacteristicRepository.save(productSpecCharacteristic);
    }
}
