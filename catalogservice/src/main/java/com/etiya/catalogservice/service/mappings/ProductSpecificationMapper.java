package com.etiya.catalogservice.service.mappings;

import com.etiya.catalogservice.domain.entities.CharValue;
import com.etiya.catalogservice.domain.entities.Characteristic;
import com.etiya.catalogservice.domain.entities.ProductSpecification;
import com.etiya.catalogservice.service.dtos.requests.CharValue.CreateCharValueRequest;
import com.etiya.catalogservice.service.dtos.requests.CharValue.UpdateCharValueRequest;
import com.etiya.catalogservice.service.dtos.requests.Characteristic.CreateCharacteristicRequest;
import com.etiya.catalogservice.service.dtos.requests.Characteristic.UpdateCharacteristicRequest;
import com.etiya.catalogservice.service.dtos.requests.ProductSpecification.CreateProductSpecificationRequest;
import com.etiya.catalogservice.service.dtos.requests.ProductSpecification.UpdateProductSpecificationRequest;
import com.etiya.catalogservice.service.dtos.responses.CharValue.CreatedCharValueResponse;
import com.etiya.catalogservice.service.dtos.responses.CharValue.GetListCharValueResponse;
import com.etiya.catalogservice.service.dtos.responses.CharValue.UpdatedCharValueResponse;
import com.etiya.catalogservice.service.dtos.responses.Characteristic.CreatedCharacteristicResponse;
import com.etiya.catalogservice.service.dtos.responses.Characteristic.GetListCharacteristicResponse;
import com.etiya.catalogservice.service.dtos.responses.Characteristic.UpdatedCharacteristicResponse;
import com.etiya.catalogservice.service.dtos.responses.ProductSpecification.CreatedProductSpecificationResponse;
import com.etiya.catalogservice.service.dtos.responses.ProductSpecification.GetListProductSpecificationResponse;
import com.etiya.catalogservice.service.dtos.responses.ProductSpecification.UpdatedProductSpecificationResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface ProductSpecificationMapper {

    ProductSpecificationMapper INSTANCE = Mappers.getMapper(ProductSpecificationMapper.class);

    //create
    @Mapping(target = "genelType.id", source = "genelTypeId") //“Request içindeki GENELTYPE_ID alanını al, Characteristic içindeki GENELTYPE nesnesinin id alanına koy.”
    @Mapping(target = "genelStatus.id", source = "genelStatusId")
    ProductSpecification productSpecificationFromCreateProductSpecificationRequest(CreateProductSpecificationRequest request);

    @Mapping(target = "genelTypeId", source = "genelType.id")
    @Mapping(target = "genelStatusId", source = "genelStatus.id")
    CreatedProductSpecificationResponse createdProductSpecificationResponseFromProductSpecification(ProductSpecification productSpecification);

    //update
    @Mapping(target = "genelType.id", source = "genelTypeId")
    @Mapping(target = "genelStatus.id", source = "genelStatusId")
    ProductSpecification productSpecificationFromUpdateProductSpecificationRequest(UpdateProductSpecificationRequest request);

    @Mapping(target = "genelType.id", ignore = true)
    @Mapping(target = "genelStatus.id", ignore = true)
    ProductSpecification productSpecificationFromUpdateProductSpecificationRequest(UpdateProductSpecificationRequest request, @MappingTarget ProductSpecification productSpecification);

    @Mapping(target = "genelTypeId", source = "genelType.id") //hedef responsa yapmak -- kaynak char.. entitysi
    @Mapping(target = "genelStatusId", source = "genelStatus.id")
    UpdatedProductSpecificationResponse updatedProductSpecificationResponseFromProductSpecification(ProductSpecification productSpecification);

    //getlist
    List<GetListProductSpecificationResponse> getListProductSpecificationResponseFromProductSpecification(List<ProductSpecification> productSpecifications);
}
