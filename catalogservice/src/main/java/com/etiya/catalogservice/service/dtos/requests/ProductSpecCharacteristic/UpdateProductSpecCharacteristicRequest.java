package com.etiya.catalogservice.service.dtos.requests.ProductSpecCharacteristic;

import com.etiya.catalogservice.domain.entities.Characteristic;
import com.etiya.catalogservice.domain.entities.ProductSpecification;
import jakarta.persistence.Column;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

public class UpdateProductSpecCharacteristicRequest {

    private int id;

    private Boolean isRequired;

    private int prodSpecId;

    private int charId;

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

    public int getProdSpecId() {
        return prodSpecId;
    }

    public void setProdSpecId(int prodSpecId) {
        this.prodSpecId = prodSpecId;
    }

    public int getCharId() {
        return charId;
    }

    public void setCharId(int charId) {
        this.charId = charId;
    }

    public UpdateProductSpecCharacteristicRequest() {
    }

    public UpdateProductSpecCharacteristicRequest(int id, Boolean isRequired, int prodSpecId, int charId) {
        this.id = id;
        this.isRequired = isRequired;
        this.prodSpecId = prodSpecId;
        this.charId = charId;
    }
}
