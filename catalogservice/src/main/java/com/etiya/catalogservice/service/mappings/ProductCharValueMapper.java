package com.etiya.catalogservice.service.mappings;

import com.etiya.catalogservice.domain.entities.ProductCharValue;
import com.etiya.catalogservice.domain.entities.ProductSpecCharacteristic;
import com.etiya.catalogservice.service.dtos.requests.ProductCharValue.CreateProductCharValueRequest;
import com.etiya.catalogservice.service.dtos.requests.ProductCharValue.UpdateProductCharValueRequest;
import com.etiya.catalogservice.service.dtos.requests.ProductSpecCharacteristic.CreateProductSpecCharacteristicRequest;
import com.etiya.catalogservice.service.dtos.requests.ProductSpecCharacteristic.UpdateProductSpecCharacteristicRequest;
import com.etiya.catalogservice.service.dtos.responses.ProductCharValue.CreatedProductCharValueResponse;
import com.etiya.catalogservice.service.dtos.responses.ProductCharValue.GetListProductCharValueResponse;
import com.etiya.catalogservice.service.dtos.responses.ProductCharValue.UpdatedProductCharValueResponse;
import com.etiya.catalogservice.service.dtos.responses.ProductSpecCharacteristic.CreatedProductSpecCharacteristicResponse;
import com.etiya.catalogservice.service.dtos.responses.ProductSpecCharacteristic.GetListProductSpecCharacteristicResponse;
import com.etiya.catalogservice.service.dtos.responses.ProductSpecCharacteristic.UpdatedProductSpecCharacteristicResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface ProductCharValueMapper {

    ProductCharValueMapper INSTANCE = Mappers.getMapper(ProductCharValueMapper.class);

    //create
    @Mapping(target = "charValue.id", source = "charValueId") //“Request içindeki GENELTYPE_ID alanını al, Characteristic içindeki GENELTYPE nesnesinin id alanına koy.”
    @Mapping(target = "product.id", source = "productId")
    ProductCharValue productCharValueFromCreateProductCharValueRequest(CreateProductCharValueRequest request);

    @Mapping(target = "charValueId", source = "charValue.id")
    @Mapping(target = "productId", source = "product.id")
    CreatedProductCharValueResponse createdProductCharValueResponseFromProductCharValue(ProductCharValue productCharValue);

    //update
    @Mapping(target = "charValue.id", source = "charValueId")
    @Mapping(target = "product.id", source = "productId")
    ProductCharValue productCharValueFromUpdateProductCharValueRequest(UpdateProductCharValueRequest request);

    @Mapping(target = "charValue.id", ignore = true)
    @Mapping(target = "product.id", ignore = true)
    ProductCharValue productCharValueFromUpdateProductCharValueRequest(UpdateProductCharValueRequest request, @MappingTarget ProductCharValue productCharValue);

    @Mapping(target = "charValueId", source = "charValue.id") //hedef responsa yapmak -- kaynak char.. entitysi
    @Mapping(target = "productId", source = "product.id")
    UpdatedProductCharValueResponse updatedProductCharValueResponseFromProductCharValue(ProductCharValue productCharValue);

    //getlist
    List<GetListProductCharValueResponse> getListProductCharValueResponseFromProductCharValue(List<ProductCharValue> productCharValues);

}
