package com.etiya.catalogservice.service.mappings;

import com.etiya.catalogservice.domain.entities.CatalogProductOffer;
import com.etiya.catalogservice.service.dtos.requests.CatalogProductOffer.CreateCatalogProductOfferRequest;
import com.etiya.catalogservice.service.dtos.requests.CatalogProductOffer.UpdateCatalogProductOfferRequest;
import com.etiya.catalogservice.service.dtos.responses.CatalogProductOffer.CreatedCatalogProductOfferResponse;
import com.etiya.catalogservice.service.dtos.responses.CatalogProductOffer.GetListCatalogProductOfferResponse;
import com.etiya.catalogservice.service.dtos.responses.CatalogProductOffer.UpdatedCatalogProductOfferResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface CatalogProductOfferMapper {

    CatalogProductOfferMapper INSTANCE = Mappers.getMapper(CatalogProductOfferMapper.class);

    // --- Entity'den Response DTO'ya ---
    @Mapping(source = "catalog.id", target = "catalogId")
    @Mapping(source = "productOffer.id", target = "productOfferId")
    CreatedCatalogProductOfferResponse getCreatedResponseFromCatalogProductOffer(CatalogProductOffer catalogProductOffer);

    @Mapping(source = "catalog.id", target = "catalogId")
    @Mapping(source = "productOffer.id", target = "productOfferId")
    UpdatedCatalogProductOfferResponse getUpdatedResponseFromCatalogProductOffer(CatalogProductOffer catalogProductOffer);

    @Mapping(source = "catalog.id", target = "catalogId")
    @Mapping(source = "productOffer.id", target = "productOfferId")
    GetListCatalogProductOfferResponse getListResponseFromCatalogProductOffer(CatalogProductOffer catalogProductOffer);

    List<GetListCatalogProductOfferResponse> getListResponseFromCatalogProductOfferList(List<CatalogProductOffer> catalogProductOffers);


    // --- Request DTO'dan Entity'ye ---
    // İlişkili nesneleri (Catalog, ProductOffer) ServiceImpl'de elle setleyeceğiz.
    @Mapping(target = "catalog", ignore = true)
    @Mapping(target = "productOffer", ignore = true)
    CatalogProductOffer getCatalogProductOfferFromCreateRequest(CreateCatalogProductOfferRequest request);

    // Var olan bir nesneyi güncelle
    @Mapping(target = "catalog", ignore = true)
    @Mapping(target = "productOffer", ignore = true)
    void updateCatalogProductOfferFromUpdateRequest(UpdateCatalogProductOfferRequest request, @MappingTarget CatalogProductOffer catalogProductOffer);
}
