package com.etiya.catalogservice.service.mappings;

import com.etiya.catalogservice.domain.entities.CampaignProductOffer;
import com.etiya.catalogservice.service.dtos.requests.CampaignProductOffer.CreateCampaignProductOfferRequest;
import com.etiya.catalogservice.service.dtos.requests.CampaignProductOffer.UpdateCampaignProductOfferRequest;
import com.etiya.catalogservice.service.dtos.responses.CampaignProductOffer.CreatedCampaignProductOfferResponse;
import com.etiya.catalogservice.service.dtos.responses.CampaignProductOffer.GetListCampaignProductOfferResponse;
import com.etiya.catalogservice.service.dtos.responses.CampaignProductOffer.UpdatedCampaignProductOfferResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface CampaignProductMapper {
    CampaignProductMapper INSTANCE = Mappers.getMapper(CampaignProductMapper.class);


    @Mapping(source = "campaign.id", target = "campaignId")
    @Mapping(source = "productOffer.id", target = "productOfferId")
    CreatedCampaignProductOfferResponse getCreatedResponseFromCampaignProduct(CampaignProductOffer campaignProductOffer);

    @Mapping(source = "campaign.id", target = "campaignId")
    @Mapping(source = "productOffer.id", target = "productOfferId")
    UpdatedCampaignProductOfferResponse getUpdatedResponseFromCampaignProduct(CampaignProductOffer campaignProductOffer);

    @Mapping(source = "campaign.id", target = "campaignId")
    @Mapping(source = "productOffer.id", target = "productOfferId")
    GetListCampaignProductOfferResponse getListResponseFromCampaignProduct(CampaignProductOffer campaignProductOffer);

    List<GetListCampaignProductOfferResponse> getListResponseFromCampaignProductList(List<CampaignProductOffer> campaignProductOffers);


    @Mapping(target = "campaign", ignore = true) // campaign nesnesini elle setleyeceğiz
    @Mapping(target = "productOffer", ignore = true)  // product nesnesini elle setleyeceğiz
    CampaignProductOffer getCampaignProductFromCreateRequest(CreateCampaignProductOfferRequest request);


    @Mapping(target = "campaign", ignore = true)
    @Mapping(target = "productOffer", ignore = true)
    void updateCampaignProductFromUpdateRequest(UpdateCampaignProductOfferRequest request, @MappingTarget CampaignProductOffer campaignProductOffer);
}