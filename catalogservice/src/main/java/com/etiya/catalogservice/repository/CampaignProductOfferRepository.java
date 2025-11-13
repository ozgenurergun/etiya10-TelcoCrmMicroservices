package com.etiya.catalogservice.repository;

import com.etiya.catalogservice.domain.entities.CampaignProductOffer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CampaignProductOfferRepository extends JpaRepository<CampaignProductOffer, Integer> {
}
