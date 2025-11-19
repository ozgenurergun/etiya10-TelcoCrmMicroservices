package com.etiya.catalogservice.service.concretes;

import com.etiya.catalogservice.domain.entities.Campaign;
import com.etiya.catalogservice.domain.entities.CampaignProductOffer;
import com.etiya.catalogservice.domain.entities.ProductOffer;
import com.etiya.catalogservice.repository.CampaignProductOfferRepository;
import com.etiya.catalogservice.service.abstracts.CampaignProductOfferService;
import com.etiya.catalogservice.service.abstracts.CampaignService;
import com.etiya.catalogservice.service.dtos.requests.CampaignProductOffer.CreateCampaignProductOfferRequest;
import com.etiya.catalogservice.service.dtos.requests.CampaignProductOffer.UpdateCampaignProductOfferRequest;
import com.etiya.catalogservice.service.dtos.responses.CampaignProductOffer.CreatedCampaignProductOfferResponse;
import com.etiya.catalogservice.service.dtos.responses.CampaignProductOffer.GetListCampaignProductOfferResponse;
import com.etiya.catalogservice.service.dtos.responses.CampaignProductOffer.UpdatedCampaignProductOfferResponse;
import com.etiya.catalogservice.service.mappings.CampaignProductMapper;
import com.etiya.common.responses.CampaignProductOfferResponse;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class CampaignProductOfferServiceImpl implements CampaignProductOfferService {
    // SADECE KENDİ REPO'SU
    private final CampaignProductOfferRepository campaignProductOfferRepository;

    // DİĞER SERVİSLER
    private final CampaignService campaignService;
    private final ProductOfferLookupService productOfferLookupService;

    public CampaignProductOfferServiceImpl(CampaignProductOfferRepository campaignProductOfferRepository, CampaignService campaignService, ProductOfferLookupService productOfferLookupService) {
        this.campaignProductOfferRepository = campaignProductOfferRepository;
        this.campaignService = campaignService;
        this.productOfferLookupService = productOfferLookupService;
    }


    @Override
    public CreatedCampaignProductOfferResponse add(CreateCampaignProductOfferRequest request) {
        // 1. İlgili nesneleri REPO'dan DEĞİL, SERVİS'ten al
        Campaign campaign = campaignService.findById(request.getCampaignId());
        ProductOffer productOffer = productOfferLookupService.findById(request.getProductOfferId());

        // 2. Mapper ile boş bir entity oluştur
        CampaignProductOffer campaignProductOffer = CampaignProductMapper.INSTANCE.getCampaignProductFromCreateRequest(request);

        // 3. İlişkileri (nesneleri) set et
        campaignProductOffer.setCampaign(campaign);
        campaignProductOffer.setProductOffer(productOffer);

        // 4. Kaydet
        campaignProductOfferRepository.save(campaignProductOffer);

        // 5. Kaydedilen nesneyi DTO'ya map'le ve dön
        return CampaignProductMapper.INSTANCE.getCreatedResponseFromCampaignProduct(campaignProductOffer);
    }

    @Override
    public UpdatedCampaignProductOfferResponse update(UpdateCampaignProductOfferRequest request) {
        // 1. Güncellenecek kaydı kendi reposundan bul
        CampaignProductOffer campaignProductOfferToUpdate = campaignProductOfferRepository.findById(request.getId())
                .orElseThrow(() -> new RuntimeException("CampaignProduct not found"));

        // 2. Yeni ilişkili nesneleri SERVİS'lerden al
        Campaign campaign = campaignService.findById(request.getCampaignId());
        ProductOffer productOffer = productOfferLookupService.findById(request.getProductOfferId());

        // 3. Mapper ile var olan nesneyi güncelle (ilişkiler hariç)
        CampaignProductMapper.INSTANCE.updateCampaignProductFromUpdateRequest(request, campaignProductOfferToUpdate);

        // 4. İlişkileri elle güncelle
        campaignProductOfferToUpdate.setCampaign(campaign);
        campaignProductOfferToUpdate.setProductOffer(productOffer);

        // 5. Kaydet
        campaignProductOfferRepository.save(campaignProductOfferToUpdate);

        return CampaignProductMapper.INSTANCE.getUpdatedResponseFromCampaignProduct(campaignProductOfferToUpdate);
    }
    @Override
    public List<GetListCampaignProductOfferResponse> getListCampaignProductResponse() {
        List<CampaignProductOffer> campaignProductOffers = campaignProductOfferRepository.findAll();
        // (Not: soft delete'lileri getirmemek için findAllByDeletedDateIsNull() gibi bir metot kullanabilirsin)
        return CampaignProductMapper.INSTANCE.getListResponseFromCampaignProductList(campaignProductOffers);
    }

    @Override
    public void delete(int id) {
        CampaignProductOffer campaignProductOffer = campaignProductOfferRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("CampaignProduct not found"));
        campaignProductOfferRepository.delete(campaignProductOffer);
    }

    @Override
    public void softDelete(int id) {
        CampaignProductOffer campaignProductOffer = campaignProductOfferRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("CampaignProduct not found"));

        campaignProductOffer.setDeletedDate(LocalDateTime.now());
        campaignProductOffer.setIsActive(0);

        campaignProductOfferRepository.save(campaignProductOffer);
    }

    @Override
    public CampaignProductOffer findById(int id) {
        return campaignProductOfferRepository.findById(id).orElseThrow(() -> new RuntimeException("CampaignProduct not found"));
    }

    @Override
    public CampaignProductOfferResponse getByIdForClient(int id) {
        CampaignProductOffer campaignProductOffer = findById(id);

        // Product'ı LAZY loading'e takılmadan ProductService üzerinden al
        ProductOffer productOffer = productOfferLookupService.findById(campaignProductOffer.getProductOffer().getId());

        CampaignProductOfferResponse response = new CampaignProductOfferResponse();
        response.setId(campaignProductOffer.getId());
        response.setName(productOffer.getName()); // Product adını set et
        return response;
    }

    @Override
    public List<CampaignProductOffer> getListCampaignProduct(int campaignId, Integer offerId, String offerName) {
        return campaignProductOfferRepository.searchByCampaignAndFilters(campaignId, offerId, offerName);
    }




}
