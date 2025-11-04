package com.etiya.catalogservice.service.concretes;

import com.etiya.catalogservice.domain.entities.Campaign;
import com.etiya.catalogservice.repository.CampaignRepository;
import com.etiya.catalogservice.service.abstracts.CampaignService;
import com.etiya.catalogservice.service.dtos.requests.Campaign.CreateCampaignRequest;
import com.etiya.catalogservice.service.dtos.requests.Campaign.UpdateCampaignRequest;
import com.etiya.catalogservice.service.dtos.responses.Campaign.CreatedCampaignResponse;
import com.etiya.catalogservice.service.dtos.responses.Campaign.GetListCampaignResponse;
import com.etiya.catalogservice.service.dtos.responses.Campaign.UpdatedCampaignResponse;
import com.etiya.catalogservice.service.mappings.CampaignMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class CampaignServiceImpl implements CampaignService {

    private final CampaignRepository campaignRepository;

    public CampaignServiceImpl(CampaignRepository campaignRepository) {
        this.campaignRepository = campaignRepository;
    }

    @Override
    public CreatedCampaignResponse add(CreateCampaignRequest request) {
        Campaign campaign = CampaignMapper.INSTANCE.getCampaignFromCreateCampaignRequest(request);
        campaignRepository.save(campaign);
        CreatedCampaignResponse response = CampaignMapper.INSTANCE.getCampaignResponseFromCampaign(campaign);
        return response;
    }

    @Override
    public UpdatedCampaignResponse update(UpdateCampaignRequest request) {
        Campaign campaignOld = campaignRepository.findById(request.getId()).orElseThrow(() -> new RuntimeException("Campaign not found"));

        Campaign update = CampaignMapper.INSTANCE.getCampaignFromUpdateCampaignRequest(request, campaignOld);
        campaignRepository.save(update);
        UpdatedCampaignResponse response = CampaignMapper.INSTANCE.getUpdatedCampaignResponseFromCampaign(update);
        return response;
    }

    @Override
    public List<GetListCampaignResponse> getListCampaignResponse() {
        List<Campaign> campaigns = campaignRepository.findAll();
        List<GetListCampaignResponse> responses = CampaignMapper.INSTANCE.getListCampaignResponseFromCampaign(campaigns);
        return responses;
    }

    @Override
    public void delete(int id) {
        Campaign campaign = campaignRepository.findById(id).orElseThrow(() -> new RuntimeException("Campaign not found"));
        campaignRepository.delete(campaign);
    }

    @Override
    public void softDelete(int id) {
        Campaign campaign = campaignRepository.findById(id).orElseThrow(() -> new RuntimeException("Campaign not found"));
        campaign.setDeletedDate(LocalDateTime.now());
        campaign.setIsActive(0);
        campaignRepository.save(campaign);
    }

    @Override
    public Campaign findById(int id) {
        // ID ile bul, bulamazsan exception fırlat.
        return campaignRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Campaign not found with id: " + id));
    }
}

