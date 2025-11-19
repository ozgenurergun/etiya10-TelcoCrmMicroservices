package com.etiya.catalogservice.service.abstracts;

import com.etiya.catalogservice.domain.entities.ProductOffer;
import com.etiya.catalogservice.service.dtos.requests.ProductOffer.CreateProductOfferRequest;
import com.etiya.catalogservice.service.dtos.requests.ProductOffer.UpdateProductOfferRequest;
import com.etiya.catalogservice.service.dtos.responses.ProductOffer.*;
import com.etiya.common.responses.ProductOfferResponse;

import java.util.List;

public interface ProductOfferService {
    CreatedProductOfferResponse add(CreateProductOfferRequest request);
    UpdatedProductOfferResponse update(UpdateProductOfferRequest request);
    List<GetListProductOfferResponse> getListProductOfferResponse();
    void delete(int id);
    void softDelete(int id);

    // --- Servisler Arası İletişim Metodu ---
    // CatalogProductOfferService'in ProductOffer entity'sine
    // DTO olmadan, doğrudan erişebilmesi için.
    ProductOffer findById(int id);

    ProductOfferResponse getByIdForClient(int id); // <-- YENİ METOT

    List<GetProductOfferFromCatalogResponse> getOffersByCatalogId(int catalogId, Integer offerId, String offerName);
    //List<GetProductOfferFromCatalogResponse> getOffersByCatalogId(int catalogId);
    //List<GetProductOfferFromCampaignResponse> getOffersByCampaignId(int campaignId);
    List<GetProductOfferFromCampaignResponse> getOffersByCampaignId(int campaignId, Integer offerId, String offerName);
}
