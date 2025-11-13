package com.etiya.catalogservice.service.abstracts;

import com.etiya.catalogservice.domain.entities.CampaignProduct;
import com.etiya.catalogservice.service.dtos.requests.CampaignProduct.CreateCampaignProductRequest;
import com.etiya.catalogservice.service.dtos.requests.CampaignProduct.UpdateCampaignProductRequest;
import com.etiya.catalogservice.service.dtos.responses.CampaignProduct.CreatedCampaignProductResponse;
import com.etiya.catalogservice.service.dtos.responses.CampaignProduct.GetListCampaignProductResponse;
import com.etiya.catalogservice.service.dtos.responses.CampaignProduct.UpdatedCampaignProductResponse;
import com.etiya.catalogservice.service.dtos.responses.ProductOffer.GetProductOfferFromCampaignResponse;
import com.etiya.common.responses.CampaignProductResponse;

import java.util.List;

public interface CampaignProductService {
    CreatedCampaignProductResponse add(CreateCampaignProductRequest request);
    UpdatedCampaignProductResponse update(UpdateCampaignProductRequest request);
    List<GetListCampaignProductResponse> getListCampaignProductResponse();
    void delete(int id);
    void softDelete(int id);

    CampaignProduct findById(int id);

    CampaignProductResponse getByIdForClient(int id); // <-- YENİ METOT

    List<GetProductOfferFromCampaignResponse> getListProductOfferFromCampaignResponse(int campaignId);

}

