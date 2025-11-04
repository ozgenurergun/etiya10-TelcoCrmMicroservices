package com.etiya.catalogservice.domain.entities;

import com.etiya.common.entities.BaseEntity;
import com.etiya.customerservice.domain.entities.City;
import jakarta.persistence.*;
import org.hibernate.annotations.SQLRestriction;

import java.util.List;

@Entity
@Table(name = "characteristics")
@SQLRestriction("is_active = 1")
public class Characteristic extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "description")
    private String description;

    @Column(name = "unit_of_measure")
    private String unitOfMeasure;

    @OneToMany(mappedBy = "characteristic")
    private List<CharValue> charValues;

    @OneToMany(mappedBy = "characteristic")
    private List<ProductSpecCharacteristic> productSpecCharacteristics;

    @ManyToOne
    @JoinColumn(name = "GNL_TP_ID")
    private GENELTYPE genelType;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUnitOfMeasure() {
        return unitOfMeasure;
    }

    public void setUnitOfMeasure(String unitOfMeasure) {
        this.unitOfMeasure = unitOfMeasure;
    }

    public List<CharValue> getCharValues() {
        return charValues;
    }

    public void setCharValues(List<CharValue> charValues) {
        this.charValues = charValues;
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

    public Characteristic() {
    }

    public Characteristic(int id, String description, String unitOfMeasure, List<CharValue> charValues, List<ProductSpecCharacteristic> productSpecCharacteristics) {
        this.id = id;
        this.description = description;
        this.unitOfMeasure = unitOfMeasure;
        this.charValues = charValues;
        this.productSpecCharacteristics = productSpecCharacteristics;
    }

    public Characteristic(int id, String description, String unitOfMeasure, List<CharValue> charValues, List<ProductSpecCharacteristic> productSpecCharacteristics, GENELTYPE genelType) {
        this.id = id;
        this.description = description;
        this.unitOfMeasure = unitOfMeasure;
        this.charValues = charValues;
        this.productSpecCharacteristics = productSpecCharacteristics;
        this.genelType = genelType;
    }
}

//Bir Özellik Tanımı (Characteristic), birden çok İzin Verilen Değer (Char_Value)'a sahip olabilir,
// ancak bir Değer, yalnızca tek bir Özelliğe aittir.