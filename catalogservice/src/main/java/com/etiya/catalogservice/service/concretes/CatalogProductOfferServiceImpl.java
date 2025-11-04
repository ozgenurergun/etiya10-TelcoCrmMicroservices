package com.etiya.catalogservice.service.concretes;

import com.etiya.catalogservice.domain.entities.Catalog;
import com.etiya.catalogservice.domain.entities.CatalogProductOffer;
import com.etiya.catalogservice.domain.entities.ProductOffer;
import com.etiya.catalogservice.repository.CatalogProductOfferRepository;
import com.etiya.catalogservice.service.abstracts.CatalogProductOfferService;
import com.etiya.catalogservice.service.abstracts.CatalogService;
import com.etiya.catalogservice.service.abstracts.ProductOfferService;
import com.etiya.catalogservice.service.dtos.requests.CatalogProductOffer.CreateCatalogProductOfferRequest;
import com.etiya.catalogservice.service.dtos.requests.CatalogProductOffer.UpdateCatalogProductOfferRequest;
import com.etiya.catalogservice.service.dtos.responses.CatalogProductOffer.CreatedCatalogProductOfferResponse;
import com.etiya.catalogservice.service.dtos.responses.CatalogProductOffer.GetListCatalogProductOfferResponse;
import com.etiya.catalogservice.service.dtos.responses.CatalogProductOffer.UpdatedCatalogProductOfferResponse;
import com.etiya.catalogservice.service.mappings.CatalogProductOfferMapper;
import com.etiya.common.responses.CatalogOfferResponse;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class CatalogProductOfferServiceImpl implements CatalogProductOfferService {

    private final CatalogProductOfferRepository catalogProductOfferRepository; // Kendi reposu

    // Diğer servisler
    private final CatalogService catalogService;
    private final ProductOfferService productOfferService; // Varsayım

    // Constructor Injection
    public CatalogProductOfferServiceImpl(CatalogProductOfferRepository catalogProductOfferRepository,
                                          CatalogService catalogService,
                                          ProductOfferService productOfferService) {
        this.catalogProductOfferRepository = catalogProductOfferRepository;
        this.catalogService = catalogService;
        this.productOfferService = productOfferService;
    }

    @Override
    public CreatedCatalogProductOfferResponse add(CreateCatalogProductOfferRequest request) {
        // 1. İlişkili nesneleri servislerinden bul
        Catalog catalog = catalogService.findById(request.getCatalogId());
        ProductOffer productOffer = productOfferService.findById(request.getProductOfferId());

        // 2. Mapper ile boş entity oluştur
        CatalogProductOffer catalogProductOffer = CatalogProductOfferMapper.INSTANCE.getCatalogProductOfferFromCreateRequest(request);

        // 3. Nesneleri elle set et
        catalogProductOffer.setCatalog(catalog);
        catalogProductOffer.setProductOffer(productOffer);

        // 4. Kaydet
        catalogProductOfferRepository.save(catalogProductOffer);

        // 5. Response DTO'ya map'le ve dön
        return CatalogProductOfferMapper.INSTANCE.getCreatedResponseFromCatalogProductOffer(catalogProductOffer);
    }

    @Override
    public UpdatedCatalogProductOfferResponse update(UpdateCatalogProductOfferRequest request) {
        // 1. Güncellenecek kaydı bul
        CatalogProductOffer offerToUpdate = findById(request.getId());

        // 2. Yeni ilişkili nesneleri bul
        Catalog catalog = catalogService.findById(request.getCatalogId());
        ProductOffer productOffer = productOfferService.findById(request.getProductOfferId());

        // 3. Mapper ile temel (ilişkisel olmayan) alanları güncelle
        CatalogProductOfferMapper.INSTANCE.updateCatalogProductOfferFromUpdateRequest(request, offerToUpdate);

        // 4. İlişkileri elle güncelle
        offerToUpdate.setCatalog(catalog);
        offerToUpdate.setProductOffer(productOffer);

        // 5. Kaydet
        catalogProductOfferRepository.save(offerToUpdate);

        return CatalogProductOfferMapper.INSTANCE.getUpdatedResponseFromCatalogProductOffer(offerToUpdate);
    }

    @Override
    public List<GetListCatalogProductOfferResponse> getListCatalogProductOfferResponse() {
        // List<CatalogProductOffer> offers = catalogProductOfferRepository.findAllByIsActive(1);
        List<CatalogProductOffer> offers = catalogProductOfferRepository.findAll();
        return CatalogProductOfferMapper.INSTANCE.getListResponseFromCatalogProductOfferList(offers);
    }

    @Override
    public void delete(int id) {
        CatalogProductOffer offer = findById(id);
        catalogProductOfferRepository.delete(offer);
    }

    @Override
    public void softDelete(int id) {
        CatalogProductOffer offer = findById(id);

        offer.setDeletedDate(LocalDateTime.now());
        offer.setIsActive(0);

        catalogProductOfferRepository.save(offer);
    }

    // --- Servis İçi Metot ---
    @Override
    public CatalogProductOffer findById(int id) {
        return catalogProductOfferRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("CatalogProductOffer not found with id: " + id));
    }

    @Override
    public CatalogOfferResponse getByIdForClient(int id) {
        CatalogProductOffer catalogOffer = findById(id);

        CatalogOfferResponse response = new CatalogOfferResponse();
        response.setId(catalogOffer.getId());
        return response;
    }
}
