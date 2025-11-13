package com.etiya.catalogservice.service.concretes;

import com.etiya.catalogservice.domain.entities.ProductOffer;
import com.etiya.catalogservice.repository.ProductOfferRepository;
import org.springframework.stereotype.Service;

@Service
public class ProductOfferLookupService {
    private final ProductOfferRepository repo;
    public ProductOfferLookupService(ProductOfferRepository repo) { this.repo = repo; }

    public ProductOffer findById(int id) {
        return repo.findById(id)
                .orElseThrow(() -> new RuntimeException("ProductOffer not found"));
    }
}
