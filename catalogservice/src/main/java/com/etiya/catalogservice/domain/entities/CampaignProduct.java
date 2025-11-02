package com.etiya.catalogservice.domain.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "campaign_products")
public class CampaignProduct {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "campaign_id")
    private Campaign campaign;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private Product product;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Campaign getCampaign() {
        return campaign;
    }

    public void setCampaign(Campaign campaign) {
        this.campaign = campaign;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public CampaignProduct() {
    }

    public CampaignProduct(int id, Campaign campaign, Product product) {
        this.id = id;
        this.campaign = campaign;
        this.product = product;
    }
}
