package com.etiya.catalogservice.service.concretes;

import com.etiya.catalogservice.domain.entities.CharValue;
import com.etiya.catalogservice.domain.entities.Product;
import com.etiya.catalogservice.domain.entities.ProductCharValue;
import com.etiya.catalogservice.repository.ProductCharValueRepository;
import com.etiya.catalogservice.service.abstracts.CharValueService;
import com.etiya.catalogservice.service.abstracts.ProductCharValueService;
import com.etiya.catalogservice.service.abstracts.ProductService;
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
    private final CharValueService charValueService;
    private final ProductService productService;

    public ProductCharValueServiceImpl(ProductCharValueRepository productCharValueRepository, CharValueService charValueService, ProductService productService) {
        this.productCharValueRepository = productCharValueRepository;
        this.charValueService = charValueService;
        this.productService = productService;
    }

    @Override
    public CreatedProductCharValueResponse add(CreateProductCharValueRequest request) {
        ProductCharValue productCharValue = ProductCharValueMapper.INSTANCE.productCharValueFromCreateProductCharValueRequest(request);

        CharValue charValue = charValueService.findById(request.getCharValueId());
        Product product = productService.findById(request.getProductId());

        productCharValue.setCharValue(charValue);
        productCharValue.setProduct(product);
        productCharValueRepository.save(productCharValue);
        CreatedProductCharValueResponse response = ProductCharValueMapper.INSTANCE.createdProductCharValueResponseFromProductCharValue(productCharValue);
        return response;
    }

    @Override
    public UpdatedProductCharValueResponse update(UpdateProductCharValueRequest request) {
        ProductCharValue oldProductCharValue = productCharValueRepository.findById(request.getId()).orElseThrow(()-> new RuntimeException("ProductCharValue not found." ));

        CharValue charValue = charValueService.findById(request.getCharValueId());
        Product product = productService.findById(request.getProductId());

        ProductCharValue productCharValue = ProductCharValueMapper.INSTANCE.productCharValueFromUpdateProductCharValueRequest(request, oldProductCharValue);

        productCharValue.setCharValue(charValue);
        productCharValue.setProduct(product);
        productCharValueRepository.save(productCharValue);
        UpdatedProductCharValueResponse response = ProductCharValueMapper.INSTANCE.updatedProductCharValueResponseFromProductCharValue(productCharValue);
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

    @Override
    public ProductCharValue findById(int id) {
        return productCharValueRepository.findById(id).orElseThrow(() -> new RuntimeException("CampaignProduct not found"));
    }
}
