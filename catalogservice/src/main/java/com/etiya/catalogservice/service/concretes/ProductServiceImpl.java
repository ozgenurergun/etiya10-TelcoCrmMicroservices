package com.etiya.catalogservice.service.concretes;

import com.etiya.catalogservice.domain.entities.Catalog;
import com.etiya.catalogservice.domain.entities.Product;
import com.etiya.catalogservice.domain.entities.ProductOffer;
import com.etiya.catalogservice.repository.ProductRepository;
import com.etiya.catalogservice.service.abstracts.CatalogService;
import com.etiya.catalogservice.service.abstracts.ProductOfferService;
import com.etiya.catalogservice.service.abstracts.ProductService;
import com.etiya.catalogservice.service.abstracts.ProductSpecificationService;
import com.etiya.catalogservice.service.dtos.requests.Product.CreateProductRequest;
import com.etiya.catalogservice.service.dtos.requests.Product.UpdateProductRequest;
import com.etiya.catalogservice.service.dtos.responses.Product.CreatedProductResponse;
import com.etiya.catalogservice.service.dtos.responses.Product.GetListProductResponse;
import com.etiya.catalogservice.service.dtos.responses.Product.UpdatedProductResponse;
import com.etiya.catalogservice.service.mappings.ProductMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository; // Sadece kendi reposu
    // Diğer servisler
    private final CatalogService catalogService;
    private final ProductOfferService productOfferService;

    // Constructor Injection
    public ProductServiceImpl(ProductRepository productRepository,
                              CatalogService catalogService,
                              ProductSpecificationService productSpecificationService, ProductOfferService productOfferService) {
        this.productRepository = productRepository;
        this.catalogService = catalogService;
        this.productOfferService = productOfferService;
    }

    @Override
    public CreatedProductResponse add(CreateProductRequest request) {
        // 1. İlişkili nesneleri ID'lerinden servisleri kullanarak bul
        Catalog catalog = catalogService.findById(request.getCatalogId());
        ProductOffer productOffer = productOfferService.findById(request.getProductOfferId());
        // 2. Mapper ile temel alanları map'le
        Product product = ProductMapper.INSTANCE.getProductFromCreateRequest(request);

        // 3. İlişkili nesneleri elle set et
        product.setCatalog(catalog);
        product.setProductOffer(productOffer); // Entity'deki setter'ın adı 'setSpecification'

        // 4. Kaydet
        productRepository.save(product);

        // 5. Response DTO'ya map'le ve dön
        return ProductMapper.INSTANCE.getCreatedResponseFromProduct(product);
    }

    @Override
    public UpdatedProductResponse update(UpdateProductRequest request) {
        // 1. Güncellenecek ürünü bul
        Product productToUpdate = findById(request.getId()); // Kendi findById metodumuzu kullanalım

        // 2. İlişkili nesneleri bul
        Catalog catalog = catalogService.findById(request.getCatalogId());
        ProductOffer productOffer = productOfferService.findById(request.getProductOfferId());

        // 3. Mapper ile temel alanları güncelle
        ProductMapper.INSTANCE.updateProductFromUpdateRequest(request, productToUpdate);

        // 4. İlişkili nesneleri elle set et
        productToUpdate.setCatalog(catalog);
        productToUpdate.setProductOffer(productOffer);

        // 5. Kaydet
        productRepository.save(productToUpdate);

        return ProductMapper.INSTANCE.getUpdatedResponseFromProduct(productToUpdate);
    }

    @Override
    public List<GetListProductResponse> getListProductResponse() {
        // Not: Sadece aktif olanları getirmek daha iyi olabilir
        // List<Product> products = productRepository.findAllByIsActive(1);
        List<Product> products = productRepository.findAll();
        return ProductMapper.INSTANCE.getListResponseFromProductList(products);
    }

    @Override
    public void delete(int id) {
        Product product = findById(id); // Önce bul
        productRepository.delete(product);
    }

    @Override
    public void softDelete(int id) {
        Product product = findById(id);
        // BaseEntity'den gelen alanlar
        product.setDeletedDate(LocalDateTime.now());
        product.setIsActive(0);
        productRepository.save(product);
    }

    // --- Servis İçi Metot ---
    @Override
    public Product findById(int id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found with id: " + id));
    }

    @Override
    public ProductResponse getByIdForClient(int id) {
        Product product = findById(id); // Entity'yi bul

        // Common response'a map'le
        ProductResponse response = new ProductResponse();
        response.setId(product.getId());
        response.setName(product.getName());
        response.setPrice(product.getPrice());
        return response;
    }


}