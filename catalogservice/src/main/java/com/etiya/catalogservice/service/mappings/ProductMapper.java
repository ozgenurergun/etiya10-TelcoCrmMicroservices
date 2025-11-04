package com.etiya.catalogservice.service.mappings;

import com.etiya.catalogservice.domain.entities.Product;
import com.etiya.catalogservice.service.dtos.requests.Product.CreateProductRequest;
import com.etiya.catalogservice.service.dtos.requests.Product.UpdateProductRequest;
import com.etiya.catalogservice.service.dtos.responses.Product.CreatedProductResponse;
import com.etiya.catalogservice.service.dtos.responses.Product.GetListProductResponse;
import com.etiya.catalogservice.service.dtos.responses.Product.UpdatedProductResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface ProductMapper {
    ProductMapper INSTANCE = Mappers.getMapper(ProductMapper.class);

    @Mapping(source = "catalog.id", target = "catalogId")
    @Mapping(source = "productSpecification.id", target = "productSpecificationId")
    CreatedProductResponse getCreatedResponseFromProduct(Product product);

    @Mapping(source = "catalog.id", target = "catalogId")
    @Mapping(source = "productSpecification.id", target = "productSpecificationId")
    UpdatedProductResponse getUpdatedResponseFromProduct(Product product);

    @Mapping(source = "catalog.id", target = "catalogId")
    @Mapping(source = "productSpecification.id", target = "productSpecificationId")
    GetListProductResponse getListResponseFromProduct(Product product);

    List<GetListProductResponse> getListResponseFromProductList(List<Product> products);


    @Mapping(target = "catalog", ignore = true)
    @Mapping(target = "productSpecification", ignore = true)
    @Mapping(target = "productCharValues", ignore = true) // OneToMany listelerini ignore et
    @Mapping(target = "campaignProducts", ignore = true) // OneToMany listelerini ignore et
    @Mapping(target = "productOffers", ignore = true) // OneToMany listelerini ignore et
    Product getProductFromCreateRequest(CreateProductRequest request);

    @Mapping(target = "catalog", ignore = true)
    @Mapping(target = "productSpecification", ignore = true)
    @Mapping(target = "productCharValues", ignore = true)
    @Mapping(target = "campaignProducts", ignore = true)
    @Mapping(target = "productOffers", ignore = true)
    void updateProductFromUpdateRequest(UpdateProductRequest request, @MappingTarget Product product);
}