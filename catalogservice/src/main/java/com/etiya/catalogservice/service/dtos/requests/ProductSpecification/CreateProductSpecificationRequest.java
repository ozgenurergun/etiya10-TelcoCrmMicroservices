package com.etiya.catalogservice.service.dtos.requests.ProductSpecification;

public class CreateProductSpecificationRequest {

    private String name;

    private String description;

    private int genelTypeId;

    private int genelStatusId;

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

    public int getGenelTypeId() {
        return genelTypeId;
    }

    public void setGenelTypeId(int genelTypeId) {
        this.genelTypeId = genelTypeId;
    }

    public int getGenelStatusId() {
        return genelStatusId;
    }

    public void setGenelStatusId(int genelStatusId) {
        this.genelStatusId = genelStatusId;
    }

    public CreateProductSpecificationRequest() {
    }

    public CreateProductSpecificationRequest(String name, String description, int genelTypeId, int genelStatusId) {
        this.name = name;
        this.description = description;
        this.genelTypeId = genelTypeId;
        this.genelStatusId = genelStatusId;
    }
}
