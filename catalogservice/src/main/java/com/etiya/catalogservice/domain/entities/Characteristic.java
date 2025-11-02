package com.etiya.catalogservice.domain.entities;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "characteristics")
public class Characteristic {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "description")
    private String description;

    @Column(name = "data_type")
    private String dataType;

    @Column(name = "unit_of_measure")
    private String unitOfMeasure;

    @OneToMany(mappedBy = "characteristic")
    private List<CharValue> charValues;

    @OneToMany(mappedBy = "characteristic")
    private List<ProductSpecCharacteristic> productSpecCharacteristics;

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

    public String getDataType() {
        return dataType;
    }

    public void setDataType(String dataType) {
        this.dataType = dataType;
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

    public Characteristic() {
    }

    public Characteristic(int id, String description, String dataType, String unitOfMeasure, List<CharValue> charValues, List<ProductSpecCharacteristic> productSpecCharacteristics) {
        this.id = id;
        this.description = description;
        this.dataType = dataType;
        this.unitOfMeasure = unitOfMeasure;
        this.charValues = charValues;
        this.productSpecCharacteristics = productSpecCharacteristics;
    }
}

//Bir Özellik Tanımı (Characteristic), birden çok İzin Verilen Değer (Char_Value)'a sahip olabilir,
// ancak bir Değer, yalnızca tek bir Özelliğe aittir.