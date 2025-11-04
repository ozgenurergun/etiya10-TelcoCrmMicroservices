package com.etiya.catalogservice.service.mappings;

import com.etiya.catalogservice.domain.entities.ProductSpecCharacteristic;
import com.etiya.catalogservice.service.dtos.requests.ProductSpecCharacteristic.CreateProductSpecCharacteristicRequest;
import com.etiya.catalogservice.service.dtos.requests.ProductSpecCharacteristic.UpdateProductSpecCharacteristicRequest;
import com.etiya.catalogservice.service.dtos.responses.ProductSpecCharacteristic.CreatedProductSpecCharacteristicResponse;
import com.etiya.catalogservice.service.dtos.responses.ProductSpecCharacteristic.GetListProductSpecCharacteristicResponse;
import com.etiya.catalogservice.service.dtos.responses.ProductSpecCharacteristic.UpdatedProductSpecCharacteristicResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProductSpecCharacteristicMapper {

    ProductSpecCharacteristicMapper INSTANCE = Mappers.getMapper(ProductSpecCharacteristicMapper.class);

    //create
    @Mapping(target = "productSpecification.id", source = "prodSpecId") //“Request içindeki GENELTYPE_ID alanını al, Characteristic içindeki GENELTYPE nesnesinin id alanına koy.”
    @Mapping(target = "characteristic.id", source = "charId")
    ProductSpecCharacteristic productSpecCharacteristicFromCreateProductSpecCharacteristicRequest(CreateProductSpecCharacteristicRequest request);

    @Mapping(target = "prodSpecId", source = "productSpecification.id")
    @Mapping(target = "charId", source = "characteristic.id")
    CreatedProductSpecCharacteristicResponse createdProductSpecCharacteristicResponseFromProductSpecCharacteristic(ProductSpecCharacteristic productSpecCharacteristic);

    //update
    @Mapping(target = "productSpecification.id", source = "prodSpecId")
    @Mapping(target = "characteristic.id", source = "charId")
    ProductSpecCharacteristic productSpecCharacteristicFromUpdateProductSpecCharacteristicRequest(UpdateProductSpecCharacteristicRequest request);

    @Mapping(target = "productSpecification.id", ignore = true)
    @Mapping(target = "characteristic.id", ignore = true)
    ProductSpecCharacteristic productSpecCharacteristicFromUpdateProductSpecCharacteristicRequest(UpdateProductSpecCharacteristicRequest request, @MappingTarget ProductSpecCharacteristic productSpecCharacteristic);

    @Mapping(target = "prodSpecId", source = "productSpecification.id") //hedef responsa yapmak -- kaynak char.. entitysi
    @Mapping(target = "charId", source = "characteristic.id")
    UpdatedProductSpecCharacteristicResponse updatedProductSpecCharacteristicResponseFromProductSpecCharacteristic(ProductSpecCharacteristic productSpecCharacteristic);

    //getlist
    List<GetListProductSpecCharacteristicResponse> getListProductSpecCharacteristicResponseFromProductSpecCharacteristic(List<ProductSpecCharacteristic> productSpecCharacteristics);

}
