package com.etiya.catalogservice.repository;

import com.etiya.catalogservice.domain.entities.CampaignProductOffer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CampaignProductOfferRepository extends JpaRepository<CampaignProductOffer, Integer> {

    @Query("SELECT cpo FROM CampaignProductOffer cpo " +
            "WHERE cpo.campaign.id = :campaignId " +
            "AND (:offerId IS NULL OR cpo.productOffer.id = :offerId) " +
            "AND (:offerName IS NULL OR :offerName = '' OR LOWER(cpo.productOffer.name) LIKE LOWER(CONCAT('%', :offerName, '%')))")
    List<CampaignProductOffer> searchByCampaignAndFilters(
            @Param("campaignId") int campaignId,
            @Param("offerId") Integer offerId,
            @Param("offerName") String offerName);

}
