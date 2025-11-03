package com.etiya.catalogservice.service.dtos.requests.CharValue;

import com.etiya.catalogservice.domain.entities.Characteristic;
import com.etiya.catalogservice.domain.entities.ProductCharValue;
import jakarta.persistence.*;

import java.util.List;

public class CreateCharValueRequest {

    private String value;

    private int characteristicId;

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

    public CreateCharValueRequest() {
    }

    public CreateCharValueRequest(String value, int characteristicId) {
        this.value = value;
        this.characteristicId = characteristicId;
    }
}
