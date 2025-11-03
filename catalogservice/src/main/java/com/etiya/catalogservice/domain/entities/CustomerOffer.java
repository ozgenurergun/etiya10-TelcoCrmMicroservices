package com.etiya.catalogservice.domain.entities;

import com.etiya.common.entities.BaseEntity;
import com.etiya.customerservice.domain.entities.Customer;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "customer_offers")
public class CustomerOffer extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    // Customer Service'ten gelecek (UUID tipinde)
    //@Column(name = "customer_id", nullable = false)
    //private UUID customerId;

    @Column(name = "expiration_date")
    private LocalDate expirationDate;

    @Column(name = "status")
    private Boolean status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_offer_id")
    private ProductOffer productOffer;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDate getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(LocalDate expirationDate) {
        this.expirationDate = expirationDate;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public ProductOffer getProductOffer() {
        return productOffer;
    }

    public void setProductOffer(ProductOffer productOffer) {
        this.productOffer = productOffer;
    }

    public CustomerOffer() {
    }

    public CustomerOffer(int id, LocalDate expirationDate, Boolean status, ProductOffer productOffer) {
        this.id = id;
        this.expirationDate = expirationDate;
        this.status = status;
        this.productOffer = productOffer;
    }
}
