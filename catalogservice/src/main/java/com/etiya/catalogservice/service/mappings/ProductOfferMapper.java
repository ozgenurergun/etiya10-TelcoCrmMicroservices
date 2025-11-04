package com.etiya.catalogservice.service.mappings;

import com.etiya.catalogservice.domain.entities.ProductOffer;
import com.etiya.catalogservice.service.dtos.requests.ProductOffer.CreateProductOfferRequest;
import com.etiya.catalogservice.service.dtos.requests.ProductOffer.UpdateProductOfferRequest;
import com.etiya.catalogservice.service.dtos.responses.ProductOffer.CreatedProductOfferResponse;
import com.etiya.catalogservice.service.dtos.responses.ProductOffer.GetListProductOfferResponse;
import com.etiya.catalogservice.service.dtos.responses.ProductOffer.UpdatedProductOfferResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface ProductOfferMapper {

    ProductOfferMapper INSTANCE = Mappers.getMapper(ProductOfferMapper.class);

    // --- Entity'den Response DTO'ya ---
    @Mapping(source = "product.id", target = "productId")
    CreatedProductOfferResponse getCreatedResponseFromProductOffer(ProductOffer productOffer);

    @Mapping(source = "product.id", target = "productId")
    UpdatedProductOfferResponse getUpdatedResponseFromProductOffer(ProductOffer productOffer);

    @Mapping(source = "product.id", target = "productId")
    GetListProductOfferResponse getListResponseFromProductOffer(ProductOffer productOffer);

    List<GetListProductOfferResponse> getListResponseFromProductOfferList(List<ProductOffer> productOffers);


    // --- Request DTO'dan Entity'ye ---
    // 'product' nesnesini ServiceImpl'de elle setleyeceğiz.
    // Tüm OneToMany listelerini ignore et.
    @Mapping(target = "product", ignore = true)
    @Mapping(target = "catalogProductOffers", ignore = true)
    @Mapping(target = "customerOffers", ignore = true)
    ProductOffer getProductOfferFromCreateRequest(CreateProductOfferRequest request);

    // Var olan bir nesneyi güncelle
    @Mapping(target = "product", ignore = true)
    @Mapping(target = "catalogProductOffers", ignore = true)
    @Mapping(target = "customerOffers", ignore = true)
    void updateProductOfferFromUpdateRequest(UpdateProductOfferRequest request, @MappingTarget ProductOffer productOffer);
}