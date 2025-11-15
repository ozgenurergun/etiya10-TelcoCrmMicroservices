package com.etiya.catalogservice.service.concretes;

import com.etiya.catalogservice.domain.entities.ProductSpecCharacteristic;
import com.etiya.catalogservice.repository.ProductSpecCharacteristicRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductSpecCharacteristicLookupService {

    private final ProductSpecCharacteristicRepository productSpecCharacteristicRepository;

    public ProductSpecCharacteristicLookupService(ProductSpecCharacteristicRepository productSpecCharacteristicRepository) {
        this.productSpecCharacteristicRepository = productSpecCharacteristicRepository;
    }


    public List<ProductSpecCharacteristic> getByProdSpecId(int prodSpecId) {
        List<ProductSpecCharacteristic> all =  productSpecCharacteristicRepository.findAll();
        List<ProductSpecCharacteristic> responses = new ArrayList<>();
        for (ProductSpecCharacteristic productSpecCharacteristic : all) {
            if (productSpecCharacteristic.getProductSpecification().getId() == prodSpecId) {
                responses.add(productSpecCharacteristic);
            }
        }
        return responses;
    }
}
