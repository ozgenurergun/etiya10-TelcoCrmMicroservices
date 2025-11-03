package com.etiya.catalogservice.domain.entities;

import com.etiya.common.entities.BaseEntity;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "product_specifications")
public class ProductSpecification extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "lifecycle_status")
    private String lifecycleStatus;

    @Column(name = "product_type")
    private String productType;

    @OneToMany(mappedBy = "productSpecification", cascade = CascadeType.ALL)
    private List<Product> products;

    @OneToMany(mappedBy = "productSpecification")
    private List<ProductSpecCharacteristic> productSpecCharacteristics;

    @ManyToOne
    @JoinColumn(name = "GNL_TP")
    private GENELTYPE GENELTYPE;

    @ManyToOne
    @JoinColumn(name = "GNL_ST")
    private GENELSTATUS GENELSTATUS;

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

    public String getLifecycleStatus() {
        return lifecycleStatus;
    }

    public void setLifecycleStatus(String lifecycleStatus) {
        this.lifecycleStatus = lifecycleStatus;
    }

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public List<ProductSpecCharacteristic> getProductSpecCharacteristics() {
        return productSpecCharacteristics;
    }

    public void setProductSpecCharacteristics(List<ProductSpecCharacteristic> productSpecCharacteristics) {
        this.productSpecCharacteristics = productSpecCharacteristics;
    }

    public GENELTYPE getGENELTYPE() {
        return GENELTYPE;
    }

    public void setGENELTYPE(GENELTYPE GENELTYPE) {
        this.GENELTYPE = GENELTYPE;
    }

    public GENELSTATUS getGENELSTATUS() {
        return GENELSTATUS;
    }

    public void setGENELSTATUS(GENELSTATUS GENELSTATUS) {
        this.GENELSTATUS = GENELSTATUS;
    }

    public ProductSpecification() {
    }

    public ProductSpecification(int id, String name, String description, String lifecycleStatus, String productType, List<Product> products, List<ProductSpecCharacteristic> productSpecCharacteristics) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.lifecycleStatus = lifecycleStatus;
        this.productType = productType;
        this.products = products;
        this.productSpecCharacteristics = productSpecCharacteristics;
    }

    public ProductSpecification(int id, String name, String description, String lifecycleStatus, String productType, List<Product> products, List<ProductSpecCharacteristic> productSpecCharacteristics, GENELTYPE GENELTYPE, GENELSTATUS GENELSTATUS) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.lifecycleStatus = lifecycleStatus;
        this.productType = productType;
        this.products = products;
        this.productSpecCharacteristics = productSpecCharacteristics;
        this.GENELTYPE = GENELTYPE;
        this.GENELSTATUS = GENELSTATUS;
    }
}
