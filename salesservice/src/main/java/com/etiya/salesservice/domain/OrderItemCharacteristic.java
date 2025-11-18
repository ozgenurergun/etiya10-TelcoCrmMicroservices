package com.etiya.salesservice.domain;

public class OrderItemCharacteristic {
    private int characteristicId;
    private String characteristicName;
    private int charValueId; // Seçmeli değerse ID'si
    private String value;    // Manuel girişse (veya text değeri)

    public OrderItemCharacteristic() {}

    public OrderItemCharacteristic(int characteristicId, int charValueId, String value) {
        this.characteristicId = characteristicId;
        this.charValueId = charValueId;
        this.value = value;
    }

    // Getter - Setter
    public int getCharacteristicId() { return characteristicId; }
    public void setCharacteristicId(int characteristicId) { this.characteristicId = characteristicId; }
    public int getCharValueId() { return charValueId; }
    public void setCharValueId(int charValueId) { this.charValueId = charValueId; }
    public String getValue() { return value; }
    public void setValue(String value) { this.value = value; }

    public String getCharacteristicName() {
        return characteristicName;
    }

    public void setCharacteristicName(String characteristicName) {
        this.characteristicName = characteristicName;
    }
}