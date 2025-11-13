package com.etiya.catalogservice.service.concretes;

import com.etiya.catalogservice.domain.entities.Product;
import com.etiya.catalogservice.domain.entities.ProductOffer;
import com.etiya.catalogservice.repository.ProductOfferRepository;
import com.etiya.catalogservice.service.abstracts.ProductOfferService;
import com.etiya.catalogservice.service.abstracts.ProductService;
import com.etiya.catalogservice.service.dtos.requests.ProductOffer.CreateProductOfferRequest;
import com.etiya.catalogservice.service.dtos.requests.ProductOffer.UpdateProductOfferRequest;
import com.etiya.catalogservice.service.dtos.responses.ProductOffer.CreatedProductOfferResponse;
import com.etiya.catalogservice.service.dtos.responses.ProductOffer.GetListProductOfferResponse;
import com.etiya.catalogservice.service.dtos.responses.ProductOffer.UpdatedProductOfferResponse;
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
    private final ProductService productService;

    // Constructor Injection
    public ProductOfferServiceImpl(ProductOfferRepository productOfferRepository,
                                   ProductService productService) {
        this.productOfferRepository = productOfferRepository;
        this.productService = productService;
    }

    @Override
    public CreatedProductOfferResponse add(CreateProductOfferRequest request) {
        // 1. İlişkili Product nesnesini ProductService'ten bul
        Product product = productService.findById(request.getProductId());

        // 2. Mapper ile temel alanları map'le
        ProductOffer productOffer = ProductOfferMapper.INSTANCE.getProductOfferFromCreateRequest(request);

        // 3. İlişkili nesneyi elle set et
        productOffer.setProduct(product);

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
        Product product = productService.findById(request.getProductId());

        // 3. Mapper ile temel alanları güncelle
        ProductOfferMapper.INSTANCE.updateProductOfferFromUpdateRequest(request, offerToUpdate);

        // 4. İlişkili nesneyi elle set et
        offerToUpdate.setProduct(product);

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
    public List<ProductOffer> getProductOffersByProductId(int productId) {
        List<ProductOffer> responses = new ArrayList<>();
        List<ProductOffer> getAll =  productOfferRepository.findAll();
        for (ProductOffer productOffer : getAll) {
            if (productOffer.getProduct().getId() == productId) {
                responses.add(productOffer);
            }
        }
        return responses;
    }
}
