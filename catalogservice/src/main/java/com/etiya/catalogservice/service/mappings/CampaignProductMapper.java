package com.etiya.catalogservice.service.mappings;

import com.etiya.catalogservice.domain.entities.CampaignProduct;
import com.etiya.catalogservice.service.dtos.requests.CampaignProduct.CreateCampaignProductRequest;
import com.etiya.catalogservice.service.dtos.requests.CampaignProduct.UpdateCampaignProductRequest;
import com.etiya.catalogservice.service.dtos.responses.CampaignProduct.CreatedCampaignProductResponse;
import com.etiya.catalogservice.service.dtos.responses.CampaignProduct.GetListCampaignProductResponse;
import com.etiya.catalogservice.service.dtos.responses.CampaignProduct.UpdatedCampaignProductResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface CampaignProductMapper {
    CampaignProductMapper INSTANCE = Mappers.getMapper(CampaignProductMapper.class);


    @Mapping(source = "campaign.id", target = "campaignId")
    @Mapping(source = "product.id", target = "productId")
    CreatedCampaignProductResponse getCreatedResponseFromCampaignProduct(CampaignProduct campaignProduct);

    @Mapping(source = "campaign.id", target = "campaignId")
    @Mapping(source = "product.id", target = "productId")
    UpdatedCampaignProductResponse getUpdatedResponseFromCampaignProduct(CampaignProduct campaignProduct);

    @Mapping(source = "campaign.id", target = "campaignId")
    @Mapping(source = "product.id", target = "productId")
    GetListCampaignProductResponse getListResponseFromCampaignProduct(CampaignProduct campaignProduct);

    List<GetListCampaignProductResponse> getListResponseFromCampaignProductList(List<CampaignProduct> campaignProducts);


    @Mapping(target = "campaign", ignore = true) // campaign nesnesini elle setleyeceğiz
    @Mapping(target = "product", ignore = true)  // product nesnesini elle setleyeceğiz
    CampaignProduct getCampaignProductFromCreateRequest(CreateCampaignProductRequest request);


    @Mapping(target = "campaign", ignore = true)
    @Mapping(target = "product", ignore = true)
    void updateCampaignProductFromUpdateRequest(UpdateCampaignProductRequest request, @MappingTarget CampaignProduct campaignProduct);
}