package com.etiya.catalogservice.domain.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "product_char_values")
public class ProductCharValue {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "char_value_id")
    private CharValue charValue; // Hangi değere ait

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private Product product; // Hangi ürüne ait

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public CharValue getCharValue() {
        return charValue;
    }

    public void setCharValue(CharValue charValue) {
        this.charValue = charValue;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public ProductCharValue() {
    }

    public ProductCharValue(int id, CharValue charValue, Product product) {
        this.id = id;
        this.charValue = charValue;
        this.product = product;
    }
}
