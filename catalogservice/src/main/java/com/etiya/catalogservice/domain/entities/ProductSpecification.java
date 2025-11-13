package com.etiya.catalogservice.domain.entities;

import com.etiya.common.entities.BaseEntity;
import jakarta.persistence.*;
import org.hibernate.annotations.SQLRestriction;

import java.util.List;

@Entity
@Table(name = "product_specifications")
@SQLRestriction("is_active = 1")
public class ProductSpecification extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @OneToMany(mappedBy = "productSpecification", cascade = CascadeType.ALL)
    private List<ProductOffer> productOffers;

    @OneToMany(mappedBy = "productSpecification")
    private List<ProductSpecCharacteristic> productSpecCharacteristics;

    @ManyToOne
    @JoinColumn(name = "GNL_TP")
    private GENELTYPE genelType;

    @ManyToOne
    @JoinColumn(name = "GNL_ST")
    private GENELSTATUS genelStatus;

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

    public List<ProductOffer> getProductOffers() {
        return productOffers;
    }

    public void setProductOffers(List<ProductOffer> productOffers) {
        this.productOffers = productOffers;
    }

    public List<ProductSpecCharacteristic> getProductSpecCharacteristics() {
        return productSpecCharacteristics;
    }

    public void setProductSpecCharacteristics(List<ProductSpecCharacteristic> productSpecCharacteristics) {
        this.productSpecCharacteristics = productSpecCharacteristics;
    }

    public GENELTYPE getGenelType() {
        return genelType;
    }

    public void setGenelType(GENELTYPE genelType) {
        this.genelType = genelType;
    }

    public GENELSTATUS getGenelStatus() {
        return genelStatus;
    }

    public void setGenelStatus(GENELSTATUS genelStatus) {
        this.genelStatus = genelStatus;
    }

    public ProductSpecification() {
    }

    public ProductSpecification(int id, String name, String description, List<ProductOffer> productOffers, List<ProductSpecCharacteristic> productSpecCharacteristics, GENELTYPE genelType, GENELSTATUS genelStatus) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.productOffers = productOffers;
        this.productSpecCharacteristics = productSpecCharacteristics;
        this.genelType = genelType;
        this.genelStatus = genelStatus;
    }
}
