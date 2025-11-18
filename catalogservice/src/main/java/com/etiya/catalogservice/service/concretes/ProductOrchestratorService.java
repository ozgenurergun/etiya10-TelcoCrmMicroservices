package com.etiya.catalogservice.service.concretes;

import com.etiya.catalogservice.domain.entities.CharValue;
import com.etiya.catalogservice.domain.entities.Characteristic;
import com.etiya.catalogservice.domain.entities.Product;
import com.etiya.catalogservice.service.abstracts.CharValueService;
import com.etiya.catalogservice.service.abstracts.CharacteristicService;
import com.etiya.catalogservice.service.abstracts.ProductCharValueService;
import com.etiya.catalogservice.service.abstracts.ProductService;
import com.etiya.catalogservice.service.dtos.requests.ProductCharValue.CreateProductCharValueRequest;
import com.etiya.common.requests.CreateProdCharValueRequest;
import com.etiya.common.requests.CreateProductRequest;
import com.etiya.common.responses.ProductResponse;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ProductOrchestratorService {

    private final ProductCharValueService productCharValueService;
    private final CharValueService charValueService;
    private final ProductService productService;
    private final CharacteristicService  characteristicService;

    public ProductOrchestratorService(ProductCharValueService productCharValueService, CharValueService charValueService, ProductService productService, CharacteristicService characteristicService) {
        this.productCharValueService = productCharValueService;
        this.charValueService = charValueService;
        this.productService = productService;
        this.characteristicService = characteristicService;
    }

    @Transactional
    public ProductResponse createProduct(CreateProductRequest request) {
        Product product = productService.add(request);

        if (request.getProductCharValues() != null) {
            for (CreateProdCharValueRequest charValRequest : request.getProductCharValues()) {
                if (charValRequest.getCharValueId() != 0) {
                    CharValue charValue = charValueService.findById(charValRequest.getCharValueId());
                    CreateProductCharValueRequest createProdCharValueRequest = new CreateProductCharValueRequest();
                    createProdCharValueRequest.setCharValueId(charValue.getId());
                    createProdCharValueRequest.setProductId(product.getId());
                    productCharValueService.add(createProdCharValueRequest);
                }else {
                    CharValue charValue = new CharValue();
                    charValue.setValue(charValRequest.getValue());
                    Characteristic characteristic = characteristicService.findById(charValRequest.getCharacteristicId());
                    charValue.setCharacteristic(characteristic);

                    CharValue savedCharValue = charValueService.save(charValue);

                    CreateProductCharValueRequest createProdCharValueRequest = new CreateProductCharValueRequest();
                    createProdCharValueRequest.setCharValueId(savedCharValue.getId());
                    createProdCharValueRequest.setProductId(product.getId());
                    productCharValueService.add(createProdCharValueRequest);
                }
            }
        }
        ProductResponse productResponse = new ProductResponse();
        productResponse.setId(product.getId());
        productResponse.setName(product.getName());
        productResponse.setPrice(product.getPrice());
        return productResponse;
    }
}
