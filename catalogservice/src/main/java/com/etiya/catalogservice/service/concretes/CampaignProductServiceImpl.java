package com.etiya.catalogservice.service.concretes;

import com.etiya.catalogservice.domain.entities.Campaign;
import com.etiya.catalogservice.domain.entities.CampaignProduct;
import com.etiya.catalogservice.domain.entities.Product;
import com.etiya.catalogservice.repository.CampaignProductRepository;
import com.etiya.catalogservice.service.abstracts.CampaignProductService;
import com.etiya.catalogservice.service.abstracts.CampaignService;
import com.etiya.catalogservice.service.abstracts.ProductService;
import com.etiya.catalogservice.service.dtos.requests.CampaignProduct.CreateCampaignProductRequest;
import com.etiya.catalogservice.service.dtos.requests.CampaignProduct.UpdateCampaignProductRequest;
import com.etiya.catalogservice.service.dtos.responses.CampaignProduct.CreatedCampaignProductResponse;
import com.etiya.catalogservice.service.dtos.responses.CampaignProduct.GetListCampaignProductResponse;
import com.etiya.catalogservice.service.dtos.responses.CampaignProduct.UpdatedCampaignProductResponse;
import com.etiya.catalogservice.service.mappings.CampaignProductMapper;
import com.etiya.common.responses.CampaignProductResponse;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class CampaignProductServiceImpl implements CampaignProductService {
    // SADECE KENDİ REPO'SU
    private final CampaignProductRepository campaignProductRepository;

    // DİĞER SERVİSLER
    private final CampaignService campaignService;
    private final ProductService productService;

    // Constructor'ı düzeltiyoruz
    public CampaignProductServiceImpl(CampaignProductRepository campaignProductRepository,
                                      CampaignService campaignService,
                                      ProductService productService) {
        this.campaignProductRepository = campaignProductRepository;
        this.campaignService = campaignService;
        this.productService = productService;
    }

    @Override
    public CreatedCampaignProductResponse add(CreateCampaignProductRequest request) {
        // 1. İlgili nesneleri REPO'dan DEĞİL, SERVİS'ten al
        Campaign campaign = campaignService.findById(request.getCampaignId());
        Product product = productService.findById(request.getProductId());

        // 2. Mapper ile boş bir entity oluştur
        CampaignProduct campaignProduct = CampaignProductMapper.INSTANCE.getCampaignProductFromCreateRequest(request);

        // 3. İlişkileri (nesneleri) set et
        campaignProduct.setCampaign(campaign);
        campaignProduct.setProduct(product);

        // 4. Kaydet
        campaignProductRepository.save(campaignProduct);

        // 5. Kaydedilen nesneyi DTO'ya map'le ve dön
        return CampaignProductMapper.INSTANCE.getCreatedResponseFromCampaignProduct(campaignProduct);
    }

    @Override
    public UpdatedCampaignProductResponse update(UpdateCampaignProductRequest request) {
        // 1. Güncellenecek kaydı kendi reposundan bul
        CampaignProduct campaignProductToUpdate = campaignProductRepository.findById(request.getId())
                .orElseThrow(() -> new RuntimeException("CampaignProduct not found"));

        // 2. Yeni ilişkili nesneleri SERVİS'lerden al
        Campaign campaign = campaignService.findById(request.getCampaignId());
        Product product = productService.findById(request.getProductId());

        // 3. Mapper ile var olan nesneyi güncelle (ilişkiler hariç)
        CampaignProductMapper.INSTANCE.updateCampaignProductFromUpdateRequest(request, campaignProductToUpdate);

        // 4. İlişkileri elle güncelle
        campaignProductToUpdate.setCampaign(campaign);
        campaignProductToUpdate.setProduct(product);

        // 5. Kaydet
        campaignProductRepository.save(campaignProductToUpdate);

        return CampaignProductMapper.INSTANCE.getUpdatedResponseFromCampaignProduct(campaignProductToUpdate);
    }
    @Override
    public List<GetListCampaignProductResponse> getListCampaignProductResponse() {
        List<CampaignProduct> campaignProducts = campaignProductRepository.findAll();
        // (Not: soft delete'lileri getirmemek için findAllByDeletedDateIsNull() gibi bir metot kullanabilirsin)
        return CampaignProductMapper.INSTANCE.getListResponseFromCampaignProductList(campaignProducts);
    }

    @Override
    public void delete(int id) {
        CampaignProduct campaignProduct = campaignProductRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("CampaignProduct not found"));
        campaignProductRepository.delete(campaignProduct);
    }

    @Override
    public void softDelete(int id) {
        CampaignProduct campaignProduct = campaignProductRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("CampaignProduct not found"));

        campaignProduct.setDeletedDate(LocalDateTime.now());
        campaignProduct.setIsActive(0);

        campaignProductRepository.save(campaignProduct);
    }

    @Override
    public CampaignProduct findById(int id) {
        return campaignProductRepository.findById(id).orElseThrow(() -> new RuntimeException("CampaignProduct not found"));
    }

    @Override
    public CampaignProductResponse getByIdForClient(int id) {
        CampaignProduct campaignProduct = findById(id);

        // Product'ı LAZY loading'e takılmadan ProductService üzerinden al
        Product product = productService.findById(campaignProduct.getProduct().getId());

        CampaignProductResponse response = new CampaignProductResponse();
        response.setId(campaignProduct.getId());
        response.setName(product.getName()); // Product adını set et
        return response;
    }
}
