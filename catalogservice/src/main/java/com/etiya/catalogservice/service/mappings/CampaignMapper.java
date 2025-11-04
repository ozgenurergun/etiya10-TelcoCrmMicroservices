package com.etiya.catalogservice.service.mappings;

import com.etiya.catalogservice.domain.entities.Campaign;
import com.etiya.catalogservice.service.dtos.requests.Campaign.CreateCampaignRequest;
import com.etiya.catalogservice.service.dtos.requests.Campaign.UpdateCampaignRequest;
import com.etiya.catalogservice.service.dtos.responses.Campaign.CreatedCampaignResponse;
import com.etiya.catalogservice.service.dtos.responses.Campaign.GetListCampaignResponse;
import com.etiya.catalogservice.service.dtos.responses.Campaign.UpdatedCampaignResponse;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface CampaignMapper {

    CampaignMapper INSTANCE = Mappers.getMapper(CampaignMapper.class);

    Campaign getCampaignFromCreateCampaignRequest(CreateCampaignRequest request);
    CreatedCampaignResponse getCampaignResponseFromCampaign(Campaign campaign);

    Campaign getCampaignFromUpdateCampaignRequest(UpdateCampaignRequest request , @MappingTarget Campaign campaign);
    UpdatedCampaignResponse getUpdatedCampaignResponseFromCampaign(Campaign campaign);

    List<GetListCampaignResponse> getListCampaignResponseFromCampaign(List<Campaign> campaigns);
}
