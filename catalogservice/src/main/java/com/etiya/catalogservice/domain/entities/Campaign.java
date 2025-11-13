package com.etiya.catalogservice.domain.entities;

import com.etiya.common.entities.BaseEntity;
import jakarta.persistence.*;
import org.hibernate.annotations.SQLRestriction;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "campaigns")
@SQLRestriction("is_active = 1")
public class Campaign extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "campaign_code")
    private String campaignCode;

    // numeric(3, 2): Toplam 3 hane, virgülden sonra 2 hane (Örn: 9.99)
    @Column(name = "discount_rate", precision = 3, scale = 2, nullable = false)
    private BigDecimal discountRate;

    @Column(name = "start_date", nullable = false)
    private LocalDate startDate; // date ve Not Null

    @Column(name = "end_date")
    private LocalDate endDate; // date (Nullable)

    @OneToMany(mappedBy = "campaign")
    private List<CampaignProductOffer> campaignProductOffers;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCampaignCode() {
        return campaignCode;
    }

    public void setCampaignCode(String campaignCode) {
        this.campaignCode = campaignCode;
    }

    public BigDecimal getDiscountRate() {
        return discountRate;
    }

    public void setDiscountRate(BigDecimal discountRate) {
        this.discountRate = discountRate;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public List<CampaignProductOffer> getCampaignProducts() {
        return campaignProductOffers;
    }

    public void setCampaignProducts(List<CampaignProductOffer> campaignProductOffers) {
        this.campaignProductOffers = campaignProductOffers;
    }

    public Campaign() {
    }

    public Campaign(int id, String name, String campaignCode, BigDecimal discountRate, LocalDate startDate, LocalDate endDate, List<CampaignProductOffer> campaignProductOffers) {
        this.id = id;
        this.name = name;
        this.campaignCode = campaignCode;
        this.discountRate = discountRate;
        this.startDate = startDate;
        this.endDate = endDate;
        this.campaignProductOffers = campaignProductOffers;
    }
}
