package com.etiya.catalogservice.service.dtos.responses.Characteristic;

public class UpdatedCharacteristicResponse {

    private int id;

    private String description;

    private String unitOfMeasure;

    private int genelTypeId;

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

    public int getGenelTypeId() {
        return genelTypeId;
    }

    public void setGenelTypeId(int genelTypeId) {
        this.genelTypeId = genelTypeId;
    }

    public UpdatedCharacteristicResponse() {
    }

    public UpdatedCharacteristicResponse(int id, String description, String unitOfMeasure, int GENELTYPE_ID) {
        this.id = id;
        this.description = description;
        this.unitOfMeasure = unitOfMeasure;
        this.genelTypeId = GENELTYPE_ID;
    }
}
