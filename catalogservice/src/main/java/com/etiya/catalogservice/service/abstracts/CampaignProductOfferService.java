package com.etiya.catalogservice.service.abstracts;

import com.etiya.catalogservice.domain.entities.CampaignProductOffer;
import com.etiya.catalogservice.service.dtos.requests.CampaignProductOffer.CreateCampaignProductOfferRequest;
import com.etiya.catalogservice.service.dtos.requests.CampaignProductOffer.UpdateCampaignProductOfferRequest;
import com.etiya.catalogservice.service.dtos.responses.CampaignProductOffer.CreatedCampaignProductOfferResponse;
import com.etiya.catalogservice.service.dtos.responses.CampaignProductOffer.GetListCampaignProductOfferResponse;
import com.etiya.catalogservice.service.dtos.responses.CampaignProductOffer.UpdatedCampaignProductOfferResponse;
import com.etiya.common.responses.CampaignProductOfferResponse;

import java.util.List;

public interface CampaignProductOfferService {
    CreatedCampaignProductOfferResponse add(CreateCampaignProductOfferRequest request);
    UpdatedCampaignProductOfferResponse update(UpdateCampaignProductOfferRequest request);
    List<GetListCampaignProductOfferResponse> getListCampaignProductResponse();
    void delete(int id);
    void softDelete(int id);

    CampaignProductOffer findById(int id);

    CampaignProductOfferResponse getByIdForClient(int id); // <-- YENİ METOT


    List<CampaignProductOffer> getListCampaignProduct();
}

