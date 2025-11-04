package com.etiya.catalogservice.service.abstracts;

import com.etiya.catalogservice.domain.entities.CatalogProductOffer;
import com.etiya.catalogservice.service.dtos.requests.CatalogProductOffer.CreateCatalogProductOfferRequest;
import com.etiya.catalogservice.service.dtos.requests.CatalogProductOffer.UpdateCatalogProductOfferRequest;
import com.etiya.catalogservice.service.dtos.responses.CatalogProductOffer.CreatedCatalogProductOfferResponse;
import com.etiya.catalogservice.service.dtos.responses.CatalogProductOffer.GetListCatalogProductOfferResponse;
import com.etiya.catalogservice.service.dtos.responses.CatalogProductOffer.UpdatedCatalogProductOfferResponse;
import com.etiya.common.responses.CatalogOfferResponse;

import java.util.List;

public interface CatalogProductOfferService {
    CreatedCatalogProductOfferResponse add(CreateCatalogProductOfferRequest request);
    UpdatedCatalogProductOfferResponse update(UpdateCatalogProductOfferRequest request);
    List<GetListCatalogProductOfferResponse> getListCatalogProductOfferResponse();
    void delete(int id);
    void softDelete(int id);

    // --- Servisler Arası İletişim Metodu ---
    // Çok ihtiyaç duyulmaz ama tutarlılık için ekleyelim.
    CatalogProductOffer findById(int id);

    CatalogOfferResponse getByIdForClient(int id); // <-- YENİ METOT
}
