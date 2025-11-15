package com.etiya.catalogservice.domain.entities;

import com.etiya.common.entities.BaseEntity;
import jakarta.persistence.*;
import org.hibernate.annotations.SQLRestriction;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "product_offers")
@SQLRestriction("is_active = 1")
public class ProductOffer extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "stock")
    private int stock;

    @Column(name = "name")
    private String name;

    @Column(name = "price")
    private BigDecimal price;

    @Column(name = "description")
    private String description;

    @Column(name = "start_date")
    private LocalDate startDate;

    @Column(name = "end_date")
    private LocalDate endDate;

    @Column(name = "discount_rate", precision = 3, scale = 2, nullable = false)
    private BigDecimal discountRate;

    @Column(name = "status")
    private Boolean status;


    @OneToMany(mappedBy = "productOffer")
    private List<CatalogProductOffer> catalogProductOffers;

    @OneToMany(mappedBy = "productOffer")
    private List<CampaignProductOffer> campaignProductOffers;

    @OneToMany(mappedBy = "productOffer")
    private List<Product> products;


    @OneToMany(mappedBy = "productOffer")
    private List<CustomerOffer> customerOffers;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "spec_id", nullable = false)
    private ProductSpecification productSpecification;

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }


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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public BigDecimal getDiscountRate() {
        return discountRate;
    }

    public void setDiscountRate(BigDecimal discountRate) {
        this.discountRate = discountRate;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }


    public List<CatalogProductOffer> getCatalogProductOffers() {
        return catalogProductOffers;
    }

    public void setCatalogProductOffers(List<CatalogProductOffer> catalogProductOffers) {
        this.catalogProductOffers = catalogProductOffers;
    }

    public List<CampaignProductOffer> getCampaignProductOffers() {
        return campaignProductOffers;
    }

    public void setCampaignProductOffers(List<CampaignProductOffer> campaignProductOffers) {
        this.campaignProductOffers = campaignProductOffers;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public List<CustomerOffer> getCustomerOffers() {
        return customerOffers;
    }

    public void setCustomerOffers(List<CustomerOffer> customerOffers) {
        this.customerOffers = customerOffers;
    }

    public ProductSpecification getProductSpecification() {
        return productSpecification;
    }

    public void setProductSpecification(ProductSpecification productSpecification) {
        this.productSpecification = productSpecification;
    }

    public ProductOffer() {
    }

    public ProductOffer(int id, int stock, String name, BigDecimal price, String description, LocalDate startDate, LocalDate endDate, BigDecimal discountRate, Boolean status, List<CatalogProductOffer> catalogProductOffers, List<CampaignProductOffer> campaignProductOffers, List<Product> products, List<CustomerOffer> customerOffers, ProductSpecification productSpecification) {
        this.id = id;
        this.stock = stock;
        this.name = name;
        this.price = price;
        this.description = description;
        this.startDate = startDate;
        this.endDate = endDate;
        this.discountRate = discountRate;
        this.status = status;
        this.catalogProductOffers = catalogProductOffers;
        this.campaignProductOffers = campaignProductOffers;
        this.products = products;
        this.customerOffers = customerOffers;
        this.productSpecification = productSpecification;
    }
}
