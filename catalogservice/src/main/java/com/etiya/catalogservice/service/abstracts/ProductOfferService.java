package com.etiya.catalogservice.service.abstracts;

import com.etiya.catalogservice.domain.entities.ProductOffer;
import com.etiya.catalogservice.service.dtos.requests.ProductOffer.CreateProductOfferRequest;
import com.etiya.catalogservice.service.dtos.requests.ProductOffer.UpdateProductOfferRequest;
import com.etiya.catalogservice.service.dtos.responses.ProductOffer.CreatedProductOfferResponse;
import com.etiya.catalogservice.service.dtos.responses.ProductOffer.GetListProductOfferResponse;
import com.etiya.catalogservice.service.dtos.responses.ProductOffer.UpdatedProductOfferResponse;
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
}
