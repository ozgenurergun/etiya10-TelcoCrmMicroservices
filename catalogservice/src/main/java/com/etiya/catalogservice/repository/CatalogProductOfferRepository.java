package com.etiya.catalogservice.repository;

import com.etiya.catalogservice.domain.entities.CatalogProductOffer;
import com.etiya.catalogservice.domain.entities.ProductOffer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CatalogProductOfferRepository extends JpaRepository<CatalogProductOffer, Integer> {
    @Query("SELECT cpo FROM CatalogProductOffer cpo " +
            "WHERE cpo.catalog.id = :catalogId " +
            "AND (:offerId IS NULL OR cpo.productOffer.id = :offerId) " +
            "AND (:offerName IS NULL OR :offerName = '' OR LOWER(cpo.productOffer.name) LIKE LOWER(CONCAT('%', :offerName, '%')))")
    List<CatalogProductOffer> searchByCatalogAndFilters(
            @Param("catalogId") int catalogId,
            @Param("offerId") Integer offerId,
            @Param("offerName") String offerName);
}

