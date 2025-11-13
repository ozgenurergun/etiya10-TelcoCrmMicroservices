package com.etiya.catalogservice.domain.entities;

import com.etiya.common.entities.BaseEntity;
import jakarta.persistence.*;
import org.hibernate.annotations.SQLRestriction;

import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "products")
@SQLRestriction("is_active = 1")
public class Product extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "price")
    private BigDecimal price;

    @Column(name = "stock")
    private int stock;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "catalog_id", nullable = false)
    private Catalog catalog;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_offer_id", nullable = false)
    private ProductOffer productOffer;


    @OneToMany(mappedBy = "product")
    private List<ProductCharValue> productCharValues;



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

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public Catalog getCatalog() {
        return catalog;
    }

    public void setCatalog(Catalog catalog) {
        this.catalog = catalog;
    }

    public ProductOffer getProductOffer() {
        return productOffer;
    }

    public void setProductOffer(ProductOffer productOffer) {
        this.productOffer = productOffer;
    }

    public List<ProductCharValue> getProductCharValues() {
        return productCharValues;
    }

    public void setProductCharValues(List<ProductCharValue> productCharValues) {
        this.productCharValues = productCharValues;
    }

    public Product() {
    }

    public Product(int id, String name, BigDecimal price, int stock, Catalog catalog, ProductOffer productOffer, List<ProductCharValue> productCharValues) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.stock = stock;
        this.catalog = catalog;
        this.productOffer = productOffer;
        this.productCharValues = productCharValues;
    }
}
