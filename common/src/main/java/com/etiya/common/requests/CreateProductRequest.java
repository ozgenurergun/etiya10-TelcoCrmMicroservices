package com.etiya.common.requests;

import java.math.BigDecimal;
import java.util.List;

public class CreateProductRequest {
    private String name;
    private BigDecimal price;
    private int stock;
    private int catalogId;
    private int productOfferId;

    // Bu alan YENİ. Karakteristikleri burada taşıyacağız.
    private List<CreateProdCharValueRequest> productCharValues;

    // Getter & Setter
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public BigDecimal getPrice() { return price; }
    public void setPrice(BigDecimal price) { this.price = price; }
    public int getStock() { return stock; }
    public void setStock(int stock) { this.stock = stock; }
    public int getCatalogId() { return catalogId; }
    public void setCatalogId(int catalogId) { this.catalogId = catalogId; }
    public int getProductOfferId() { return productOfferId; }
    public void setProductOfferId(int productOfferId) { this.productOfferId = productOfferId; }

    public List<CreateProdCharValueRequest> getProductCharValues() { return productCharValues; }
    public void setProductCharValues(List<CreateProdCharValueRequest> productCharValues) { this.productCharValues = productCharValues; }
}