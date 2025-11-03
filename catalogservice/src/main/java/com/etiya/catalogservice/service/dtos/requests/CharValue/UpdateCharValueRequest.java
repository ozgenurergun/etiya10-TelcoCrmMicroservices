package com.etiya.catalogservice.service.dtos.requests.CharValue;

public class UpdateCharValueRequest {

    private int id;

    private String value;

    private int characteristicId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public int getCharacteristicId() {
        return characteristicId;
    }

    public void setCharacteristicId(int characteristicId) {
        this.characteristicId = characteristicId;
    }

    public UpdateCharValueRequest() {
    }

    public UpdateCharValueRequest(int id, String value, int characteristicId) {
        this.id = id;
        this.value = value;
        this.characteristicId = characteristicId;
    }
}
