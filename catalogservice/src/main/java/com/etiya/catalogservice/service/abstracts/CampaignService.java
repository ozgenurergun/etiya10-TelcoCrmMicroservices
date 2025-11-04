package com.etiya.catalogservice.service.abstracts;

import com.etiya.catalogservice.domain.entities.Campaign;
import com.etiya.catalogservice.service.dtos.requests.Campaign.CreateCampaignRequest;
import com.etiya.catalogservice.service.dtos.requests.Campaign.UpdateCampaignRequest;
import com.etiya.catalogservice.service.dtos.responses.Campaign.CreatedCampaignResponse;
import com.etiya.catalogservice.service.dtos.responses.Campaign.GetListCampaignResponse;
import com.etiya.catalogservice.service.dtos.responses.Campaign.UpdatedCampaignResponse;

import java.util.List;

public interface CampaignService {
    CreatedCampaignResponse add(CreateCampaignRequest request);
    UpdatedCampaignResponse update(UpdateCampaignRequest request);
    List<GetListCampaignResponse> getListCampaignResponse();
    void delete(int id);
    void softDelete(int id);

    Campaign findById(int id);
}