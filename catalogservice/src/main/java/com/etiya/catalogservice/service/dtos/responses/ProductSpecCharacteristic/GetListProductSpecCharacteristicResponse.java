package com.etiya.catalogservice.service.dtos.responses.ProductSpecCharacteristic;

public class GetListProductSpecCharacteristicResponse {

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

    public GetListProductSpecCharacteristicResponse() {
    }

    public GetListProductSpecCharacteristicResponse(int id, Boolean isRequired, int prodSpecId, int charId) {
        this.id = id;
        this.isRequired = isRequired;
        this.prodSpecId = prodSpecId;
        this.charId = charId;
    }
}
