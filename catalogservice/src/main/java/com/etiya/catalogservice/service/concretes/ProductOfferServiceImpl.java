package com.etiya.catalogservice.service.concretes;

import com.etiya.catalogservice.domain.entities.*;
import com.etiya.catalogservice.repository.ProductOfferRepository;
import com.etiya.catalogservice.service.abstracts.*;
import com.etiya.catalogservice.service.dtos.requests.ProductOffer.CreateProductOfferRequest;
import com.etiya.catalogservice.service.dtos.requests.ProductOffer.UpdateProductOfferRequest;
import com.etiya.catalogservice.service.dtos.responses.ProductOffer.*;
import com.etiya.catalogservice.service.mappings.ProductOfferMapper;
import com.etiya.common.responses.ProductOfferResponse;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class ProductOfferServiceImpl implements ProductOfferService {

    private final ProductOfferRepository productOfferRepository; // Kendi reposu

    // Diğer servis
    private final ProductSpecificationService productSpecificationService;
    private final CatalogProductOfferService  catalogProductOfferService;
    private final CampaignProductOfferService campaignProductOfferService;

    public ProductOfferServiceImpl(ProductOfferRepository productOfferRepository, ProductSpecificationService productSpecificationService, CatalogProductOfferService catalogProductOfferService, CampaignProductOfferService campaignProductOfferService) {
        this.productOfferRepository = productOfferRepository;
        this.productSpecificationService = productSpecificationService;
        this.catalogProductOfferService = catalogProductOfferService;
        this.campaignProductOfferService = campaignProductOfferService;
    }

    // Constructor Injection

    @Override
    public CreatedProductOfferResponse add(CreateProductOfferRequest request) {
        // 1. İlişkili Product nesnesini ProductService'ten bul
        ProductSpecification productSpecification = productSpecificationService.findById(request.getProductSpecificationId());


        // 2. Mapper ile temel alanları map'le
        ProductOffer productOffer = ProductOfferMapper.INSTANCE.getProductOfferFromCreateRequest(request);

        // 3. İlişkili nesneyi elle set et
        productOffer.setProductSpecification(productSpecification);

        // 4. Kaydet
        productOfferRepository.save(productOffer);

        // 5. Response DTO'ya map'le ve dön
        return ProductOfferMapper.INSTANCE.getCreatedResponseFromProductOffer(productOffer);
    }

    @Override
    public UpdatedProductOfferResponse update(UpdateProductOfferRequest request) {
        // 1. Güncellenecek teklifi bul
        ProductOffer offerToUpdate = findById(request.getId());

        // 2. İlişkili Product nesnesini bul
        ProductSpecification productSpecification = productSpecificationService.findById(request.getProductSpecificationId());

        // 3. Mapper ile temel alanları güncelle
        ProductOfferMapper.INSTANCE.updateProductOfferFromUpdateRequest(request, offerToUpdate);

        // 4. İlişkili nesneyi elle set et
        offerToUpdate.setProductSpecification(productSpecification);

        // 5. Kaydet
        productOfferRepository.save(offerToUpdate);

        return ProductOfferMapper.INSTANCE.getUpdatedResponseFromProductOffer(offerToUpdate);
    }

    @Override
    public List<GetListProductOfferResponse> getListProductOfferResponse() {
        // List<ProductOffer> offers = productOfferRepository.findAllByIsActive(1);
        List<ProductOffer> offers = productOfferRepository.findAll();
        return ProductOfferMapper.INSTANCE.getListResponseFromProductOfferList(offers);
    }

    @Override
    public void delete(int id) {
        ProductOffer offer = findById(id);
        productOfferRepository.delete(offer);
    }

    @Override
    public void softDelete(int id) {
        ProductOffer offer = findById(id);

        // BaseEntity'den gelen alanlar
        offer.setDeletedDate(LocalDateTime.now());
        offer.setIsActive(0); // 'status' alanı yerine BaseEntity'deki 'isActive'i kullanıyoruz

        productOfferRepository.save(offer);
    }

    // --- Servis İçi ve Servisler Arası Metot ---
    @Override
    public ProductOffer findById(int id) {
        return productOfferRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("ProductOffer not found with id: " + id));
    }

    @Override
    public ProductOfferResponse getByIdForClient(int id) {
        ProductOffer productOffer = findById(id);

        ProductOfferResponse response = new ProductOfferResponse();
        response.setId(productOffer.getId());
        response.setName(productOffer.getName());
        response.setDiscountRate(productOffer.getDiscountRate());
        return response;
    }



    @Override
    public List<GetProductOfferFromCatalogResponse> getOffersByCatalogId(int catalogId) {
        List<GetProductOfferFromCatalogResponse> responses = new ArrayList<>();
        List<CatalogProductOffer> allList =  catalogProductOfferService.getListCatalogProductOffer();
        for (CatalogProductOffer catalogProductOffer : allList) {
            if(catalogProductOffer.getCatalog().getId() == catalogId) {
                ProductOffer po = catalogProductOffer.getProductOffer();
                GetProductOfferFromCatalogResponse response = ProductOfferMapper.INSTANCE.getProductOfferFromCatalogResponseFromProductOffer(po);
                response.setCatalogProductOfferId(catalogProductOffer.getId());
                response.setProductSpecificationId(po.getProductSpecification().getId());
                responses.add(response);
            }
        }
        return responses;
    }

    @Override
    public List<GetProductOfferFromCampaignResponse> getOffersByCampaignId(int campaignId) {
        List<GetProductOfferFromCampaignResponse> responses = new ArrayList<>();
        List<CampaignProductOffer> allList = campaignProductOfferService.getListCampaignProduct();  //campaignProductRepository.findAll();
        for (CampaignProductOffer campaignProductOffer : allList) {
            if(campaignProductOffer.getCampaign().getId() == campaignId) {
                ProductOffer po = campaignProductOffer.getProductOffer();
                GetProductOfferFromCampaignResponse response = ProductOfferMapper.INSTANCE.getProductOfferFromCampaignResponseFromProductOffer(po);
                response.setCampaignProductOfferId(campaignProductOffer.getId());
                response.setProductSpecificationId(po.getProductSpecification().getId());
                responses.add(response);
            }
        }
        return responses;
    }
}
