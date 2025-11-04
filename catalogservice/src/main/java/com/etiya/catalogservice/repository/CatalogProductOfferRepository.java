package com.etiya.catalogservice.repository;

import com.etiya.catalogservice.domain.entities.CatalogProductOffer;
import com.etiya.catalogservice.domain.entities.ProductOffer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CatalogProductOfferRepository extends JpaRepository<CatalogProductOffer, Integer> {
}
