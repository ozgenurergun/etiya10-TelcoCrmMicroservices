package com.etiya.catalogservice.service.abstracts;

import com.etiya.catalogservice.service.dtos.requests.ProductCharValue.CreateProductCharValueRequest;
import com.etiya.catalogservice.service.dtos.requests.ProductCharValue.UpdateProductCharValueRequest;
import com.etiya.catalogservice.service.dtos.requests.ProductSpecification.CreateProductSpecificationRequest;
import com.etiya.catalogservice.service.dtos.requests.ProductSpecification.UpdateProductSpecificationRequest;
import com.etiya.catalogservice.service.dtos.responses.CharValue.GetListCharValueResponse;
import com.etiya.catalogservice.service.dtos.responses.ProductCharValue.CreatedProductCharValueResponse;
import com.etiya.catalogservice.service.dtos.responses.ProductCharValue.GetListProductCharValueResponse;
import com.etiya.catalogservice.service.dtos.responses.ProductCharValue.UpdatedProductCharValueResponse;
import com.etiya.catalogservice.service.dtos.responses.ProductSpecification.CreatedProductSpecificationResponse;
import com.etiya.catalogservice.service.dtos.responses.ProductSpecification.GetListProductSpecificationResponse;
import com.etiya.catalogservice.service.dtos.responses.ProductSpecification.UpdatedProductSpecificationResponse;

import java.util.List;

public interface ProductCharValueService {

    CreatedProductCharValueResponse add(CreateProductCharValueRequest request);

    UpdatedProductCharValueResponse update(UpdateProductCharValueRequest request);

    List<GetListProductCharValueResponse> getAll();

    void deleteById(int id);
}
