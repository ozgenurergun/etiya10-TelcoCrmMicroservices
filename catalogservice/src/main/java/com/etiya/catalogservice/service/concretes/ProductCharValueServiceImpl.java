package com.etiya.catalogservice.service.concretes;

import com.etiya.catalogservice.domain.entities.ProductCharValue;
import com.etiya.catalogservice.repository.ProductCharValueRepository;
import com.etiya.catalogservice.service.abstracts.ProductCharValueService;
import com.etiya.catalogservice.service.dtos.requests.ProductCharValue.CreateProductCharValueRequest;
import com.etiya.catalogservice.service.dtos.requests.ProductCharValue.UpdateProductCharValueRequest;
import com.etiya.catalogservice.service.dtos.responses.ProductCharValue.CreatedProductCharValueResponse;
import com.etiya.catalogservice.service.dtos.responses.ProductCharValue.GetListProductCharValueResponse;
import com.etiya.catalogservice.service.dtos.responses.ProductCharValue.UpdatedProductCharValueResponse;
import com.etiya.catalogservice.service.mappings.ProductCharValueMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class ProductCharValueServiceImpl implements ProductCharValueService {

    private final ProductCharValueRepository productCharValueRepository;

    public ProductCharValueServiceImpl(ProductCharValueRepository productCharValueRepository) {
        this.productCharValueRepository = productCharValueRepository;
    }

    @Override
    public CreatedProductCharValueResponse add(CreateProductCharValueRequest request) {
        ProductCharValue productCharValue = ProductCharValueMapper.INSTANCE.productCharValueFromCreateProductCharValueRequest(request);
        ProductCharValue createdProductCharValue = productCharValueRepository.save(productCharValue);
        CreatedProductCharValueResponse response = ProductCharValueMapper.INSTANCE.createdProductCharValueResponseFromProductCharValue(createdProductCharValue);
        return response;
    }

    @Override
    public UpdatedProductCharValueResponse update(UpdateProductCharValueRequest request) {
        ProductCharValue oldProductCharValue = productCharValueRepository.findById(request.getId()).orElseThrow(()-> new RuntimeException("ProductCharValue not found." ));

        ProductCharValue productCharValue = ProductCharValueMapper.INSTANCE.productCharValueFromUpdateProductCharValueRequest(request, oldProductCharValue);
        ProductCharValue updatedProductCharValue = productCharValueRepository.save(productCharValue);
        UpdatedProductCharValueResponse response = ProductCharValueMapper.INSTANCE.updatedProductCharValueResponseFromProductCharValue(updatedProductCharValue);
        return response;
    }

    @Override
    public List<GetListProductCharValueResponse> getAll() {
        List<ProductCharValue> productCharValues = productCharValueRepository.findAll();
        List<GetListProductCharValueResponse> responses = ProductCharValueMapper.INSTANCE.getListProductCharValueResponseFromProductCharValue(productCharValues);
        return responses;
    }

    @Override
    public void deleteById(int id) {
        ProductCharValue productCharValue = productCharValueRepository.findById(id).orElseThrow(()-> new RuntimeException("ProductCharValue not found." ));

        productCharValue.setDeletedDate(LocalDateTime.now());
        productCharValue.setIsActive(0);
        productCharValueRepository.save(productCharValue);
    }
}
