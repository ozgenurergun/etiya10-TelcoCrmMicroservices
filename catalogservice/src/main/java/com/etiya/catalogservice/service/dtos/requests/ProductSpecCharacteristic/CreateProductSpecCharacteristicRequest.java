package com.etiya.catalogservice.service.dtos.requests.ProductSpecCharacteristic;

public class CreateProductSpecCharacteristicRequest {

    private Boolean isRequired;

    private int prodSpecId;

    private int charId;

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

    public CreateProductSpecCharacteristicRequest() {
    }

    public CreateProductSpecCharacteristicRequest(Boolean isRequired, int prodSpecId, int charId) {
        this.isRequired = isRequired;
        this.prodSpecId = prodSpecId;
        this.charId = charId;
    }
}
