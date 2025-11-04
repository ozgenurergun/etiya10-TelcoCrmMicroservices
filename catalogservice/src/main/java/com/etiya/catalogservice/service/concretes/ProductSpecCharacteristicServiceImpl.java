package com.etiya.catalogservice.service.concretes;

import com.etiya.catalogservice.domain.entities.Characteristic;
import com.etiya.catalogservice.domain.entities.ProductSpecCharacteristic;
import com.etiya.catalogservice.domain.entities.ProductSpecification;
import com.etiya.catalogservice.repository.ProductSpecCharacteristicRepository;
import com.etiya.catalogservice.service.abstracts.CharacteristicService;
import com.etiya.catalogservice.service.abstracts.ProductSpecCharacteristicService;
import com.etiya.catalogservice.service.abstracts.ProductSpecificationService;
import com.etiya.catalogservice.service.dtos.requests.ProductSpecCharacteristic.CreateProductSpecCharacteristicRequest;
import com.etiya.catalogservice.service.dtos.requests.ProductSpecCharacteristic.UpdateProductSpecCharacteristicRequest;
import com.etiya.catalogservice.service.dtos.responses.ProductSpecCharacteristic.CreatedProductSpecCharacteristicResponse;
import com.etiya.catalogservice.service.dtos.responses.ProductSpecCharacteristic.GetListProductSpecCharacteristicResponse;
import com.etiya.catalogservice.service.dtos.responses.ProductSpecCharacteristic.UpdatedProductSpecCharacteristicResponse;
import com.etiya.catalogservice.service.mappings.ProductSpecCharacteristicMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ProductSpecCharacteristicServiceImpl implements ProductSpecCharacteristicService {

    private final ProductSpecCharacteristicRepository productSpecCharacteristicRepository;
    private final ProductSpecCharacteristicMapper productSpecCharacteristicMapper;
    private final ProductSpecificationService productSpecificationService;
    private final CharacteristicService characteristicService;

    public ProductSpecCharacteristicServiceImpl(ProductSpecCharacteristicRepository productSpecCharacteristicRepository, ProductSpecCharacteristicMapper productSpecCharacteristicMapper, ProductSpecificationService productSpecificationService, CharacteristicService characteristicService) {
        this.productSpecCharacteristicRepository = productSpecCharacteristicRepository;
        this.productSpecCharacteristicMapper = productSpecCharacteristicMapper;
        this.productSpecificationService = productSpecificationService;
        this.characteristicService = characteristicService;
    }


    @Override
    public CreatedProductSpecCharacteristicResponse add(CreateProductSpecCharacteristicRequest request) {
        ProductSpecCharacteristic productSpecCharacteristic = ProductSpecCharacteristicMapper.INSTANCE.productSpecCharacteristicFromCreateProductSpecCharacteristicRequest(request);

        ProductSpecification productSpecification = productSpecificationService.findById(request.getProdSpecId());
        Characteristic characteristic = characteristicService.findById(request.getCharId());

        productSpecCharacteristic.setProductSpecification(productSpecification);
        productSpecCharacteristic.setCharacteristic(characteristic);

        productSpecCharacteristicRepository.save(productSpecCharacteristic);
        CreatedProductSpecCharacteristicResponse response = ProductSpecCharacteristicMapper.INSTANCE.createdProductSpecCharacteristicResponseFromProductSpecCharacteristic(productSpecCharacteristic);
        return response;    }

    @Override
    public UpdatedProductSpecCharacteristicResponse update(UpdateProductSpecCharacteristicRequest request) {
        ProductSpecCharacteristic oldProductSpecCharacteristic = productSpecCharacteristicRepository.findById(request.getId()).orElseThrow(() -> new RuntimeException("Product Specification Char not found"));

        ProductSpecification productSpecification = productSpecificationService.findById(request.getProdSpecId());
        Characteristic characteristic = characteristicService.findById(request.getCharId());

        ProductSpecCharacteristic productSpecCharacteristic = ProductSpecCharacteristicMapper.INSTANCE.productSpecCharacteristicFromUpdateProductSpecCharacteristicRequest(request, oldProductSpecCharacteristic);

        productSpecCharacteristic.setCharacteristic(characteristic);
        productSpecCharacteristic.setProductSpecification(productSpecification);

        productSpecCharacteristicRepository.save(productSpecCharacteristic);

        UpdatedProductSpecCharacteristicResponse response = ProductSpecCharacteristicMapper.INSTANCE.updatedProductSpecCharacteristicResponseFromProductSpecCharacteristic(productSpecCharacteristic);
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

    @Override
    public ProductSpecCharacteristic findById(int id) {
        return productSpecCharacteristicRepository.findById(id).orElseThrow(() -> new RuntimeException("CampaignProduct not found"));
    }

}
