package com.etiya.catalogservice.service.abstracts;

import com.etiya.catalogservice.service.dtos.requests.ProductSpecCharacteristic.CreateProductSpecCharacteristicRequest;
import com.etiya.catalogservice.service.dtos.requests.ProductSpecCharacteristic.UpdateProductSpecCharacteristicRequest;
import com.etiya.catalogservice.service.dtos.responses.ProductSpecCharacteristic.CreatedProductSpecCharacteristicResponse;
import com.etiya.catalogservice.service.dtos.responses.ProductSpecCharacteristic.GetListProductSpecCharacteristicResponse;
import com.etiya.catalogservice.service.dtos.responses.ProductSpecCharacteristic.UpdatedProductSpecCharacteristicResponse;

import java.util.List;

public interface ProductSpecCharacteristicService {

    CreatedProductSpecCharacteristicResponse add(CreateProductSpecCharacteristicRequest request);

    UpdatedProductSpecCharacteristicResponse update(UpdateProductSpecCharacteristicRequest request);

    List<GetListProductSpecCharacteristicResponse> getAll();

    void deleteById(int id);
}
