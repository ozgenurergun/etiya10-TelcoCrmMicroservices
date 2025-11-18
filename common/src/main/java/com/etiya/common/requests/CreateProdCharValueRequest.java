package com.etiya.common.requests;

public class CreateProdCharValueRequest {
    private int characteristicId; // Hangi özellik? (Örn: Renk, Hız, Şifre)

    // DURUM 1: Seçmeli ise ID dolu gelir (Örn: 1003)
    private Integer charValueId;

    // DURUM 2: Manuel ise ID null, Value dolu gelir (Örn: "superonline123")
    private String value;

    // Getter & Setter
    public int getCharacteristicId() { return characteristicId; }
    public void setCharacteristicId(int characteristicId) { this.characteristicId = characteristicId; }
    public Integer getCharValueId() { return charValueId; }
    public void setCharValueId(Integer charValueId) { this.charValueId = charValueId; }
    public String getValue() { return value; }
    public void setValue(String value) { this.value = value; }
}