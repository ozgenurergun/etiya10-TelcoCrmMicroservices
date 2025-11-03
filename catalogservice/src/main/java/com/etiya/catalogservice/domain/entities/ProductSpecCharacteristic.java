package com.etiya.catalogservice.domain.entities;

import com.etiya.common.entities.BaseEntity;
import jakarta.persistence.*;

@Entity
@Table(name = "product_spec_characteristics")
public class ProductSpecCharacteristic extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "is_required")
    private Boolean isRequired;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "spec_id", nullable = false)
    private ProductSpecification productSpecification;

    // FK char_id (İlişki B)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "char_id", nullable = false)
    private Characteristic characteristic;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Boolean getRequired() {
        return isRequired;
    }

    public void setRequired(Boolean required) {
        isRequired = required;
    }

    public ProductSpecification getSpecification() {
        return productSpecification;
    }

    public void setSpecification(ProductSpecification specification) {
        this.productSpecification = specification;
    }

    public Characteristic getCharacteristic() {
        return characteristic;
    }

    public void setCharacteristic(Characteristic characteristic) {
        this.characteristic = characteristic;
    }

    public ProductSpecCharacteristic() {
    }

    public ProductSpecCharacteristic(int id, Boolean isRequired, ProductSpecification specification, Characteristic characteristic) {
        this.id = id;
        this.isRequired = isRequired;
        this.productSpecification = specification;
        this.characteristic = characteristic;
    }
}
