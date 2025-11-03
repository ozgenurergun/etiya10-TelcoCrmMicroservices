package com.etiya.catalogservice.service.dtos.requests.Characteristic;

import com.etiya.catalogservice.domain.entities.GENELTYPE;

public class CreateCharacteristicRequest {

    private String description;

    private String unitOfMeasure;

    private int genelTypeId;

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

    public int getGenelTypeId() {
        return genelTypeId;
    }

    public void setGenelTypeId(int genelTypeId) {
        this.genelTypeId = genelTypeId;
    }

    public CreateCharacteristicRequest() {
    }

    public CreateCharacteristicRequest(int genelTypeId, String unitOfMeasure, String description) {
        this.genelTypeId = genelTypeId;
        this.unitOfMeasure = unitOfMeasure;
        this.description = description;
    }
}
