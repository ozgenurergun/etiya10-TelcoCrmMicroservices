package com.etiya.catalogservice.service.dtos.requests.ProductOffer;

import java.math.BigDecimal;
import java.time.LocalDate;

public class CreateProductOfferRequest {
    private String name;
    private String description;
    private LocalDate startDate;
    private LocalDate endDate;
    private BigDecimal discountRate;
    private Boolean status;
    private int productId; // İlişkili ürünün ID'si

    // --- Getter ve Setter'lar ---
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public LocalDate getStartDate() { return startDate; }
    public void setStartDate(LocalDate startDate) { this.startDate = startDate; }
    public LocalDate getEndDate() { return endDate; }
    public void setEndDate(LocalDate endDate) { this.endDate = endDate; }
    public BigDecimal getDiscountRate() { return discountRate; }
    public void setDiscountRate(BigDecimal discountRate) { this.discountRate = discountRate; }
    public Boolean getStatus() { return status; }
    public void setStatus(Boolean status) { this.status = status; }
    public int getProductId() { return productId; }
    public void setProductId(int productId) { this.productId = productId; }
}