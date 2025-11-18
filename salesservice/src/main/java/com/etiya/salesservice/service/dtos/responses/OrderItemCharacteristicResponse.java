package com.etiya.salesservice.service.dtos.responses;

public class OrderItemCharacteristicResponse {
    private int characteristicId;
    private String characteristicName;
    private String value;

    public int getCharacteristicId() {
        return characteristicId;
    }

    public void setCharacteristicId(int characteristicId) {
        this.characteristicId = characteristicId;
    }

    public String getCharacteristicName() {
        return characteristicName;
    }

    public void setCharacteristicName(String characteristicName) {
        this.characteristicName = characteristicName;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}