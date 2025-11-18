package com.etiya.salesservice.service.dtos.requests;

public class ProductCharacteristicRequest {
    private int characteristicId;
    private int charValueId; // Eğer seçmeliyse ID gelir
    private String value;    // Eğer manuel input ise (text) value gelir

    public int getCharacteristicId() { return characteristicId; }
    public void setCharacteristicId(int characteristicId) { this.characteristicId = characteristicId; }
    public int getCharValueId() { return charValueId; }
    public void setCharValueId(int charValueId) { this.charValueId = charValueId; }
    public String getValue() { return value; }
    public void setValue(String value) { this.value = value; }
}