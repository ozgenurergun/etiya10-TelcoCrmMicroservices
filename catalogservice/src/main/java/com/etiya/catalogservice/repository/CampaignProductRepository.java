package com.etiya.catalogservice.repository;

import com.etiya.catalogservice.domain.entities.CampaignProduct;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CampaignProductRepository extends JpaRepository<CampaignProduct, Integer> {
}
