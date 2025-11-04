package com.etiya.catalogservice.service.mappings;

import com.etiya.catalogservice.domain.entities.Catalog;
import com.etiya.catalogservice.service.dtos.requests.Catalog.CreateCatalogRequest;
import com.etiya.catalogservice.service.dtos.requests.Catalog.UpdateCatalogRequest;
import com.etiya.catalogservice.service.dtos.responses.Catalog.CreatedCatalogResponse;
import com.etiya.catalogservice.service.dtos.responses.Catalog.GetListCatalogResponse;
import com.etiya.catalogservice.service.dtos.responses.Catalog.UpdatedCatalogResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface CatalogMapper {

    CatalogMapper INSTANCE = Mappers.getMapper(CatalogMapper.class);

    // --- Entity'den Response DTO'ya ---
    @Mapping(source = "parent.id", target = "parentId")
    CreatedCatalogResponse getCreatedResponseFromCatalog(Catalog catalog);

    @Mapping(source = "parent.id", target = "parentId")
    UpdatedCatalogResponse getUpdatedResponseFromCatalog(Catalog catalog);

    @Mapping(source = "parent.id", target = "parentId")
    GetListCatalogResponse getListResponseFromCatalog(Catalog catalog);

    List<GetListCatalogResponse> getListResponseFromCatalogList(List<Catalog> catalogs);


    // --- Request DTO'dan Entity'ye ---
    // 'parent' nesnesini ServiceImpl'de elle setleyeceğiz.
    // Tüm OneToMany listelerini ignore et.
    @Mapping(target = "parent", ignore = true)
    @Mapping(target = "children", ignore = true)
    @Mapping(target = "products", ignore = true)
    @Mapping(target = "catalogProductOffers", ignore = true)
    Catalog getCatalogFromCreateRequest(CreateCatalogRequest request);

    // Var olan bir 'catalog' nesnesini güncelle
    @Mapping(target = "parent", ignore = true)
    @Mapping(target = "children", ignore = true)
    @Mapping(target = "products", ignore = true)
    @Mapping(target = "catalogProductOffers", ignore = true)
    void updateCatalogFromUpdateRequest(UpdateCatalogRequest request, @MappingTarget Catalog catalog);
}
