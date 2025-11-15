package com.etiya.catalogservice.service.dtos.responses.Characteristic;

import com.etiya.catalogservice.domain.entities.CharValue;
import com.etiya.catalogservice.service.dtos.responses.CharValue.CharValueForCharResponse;

import java.util.List;

public class GetListCharacteristicWithCharValResponse {

    private int id;

    private String description;

    private String unitOfMeasure;

    private List<CharValueForCharResponse> charValues;

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

    public List<CharValueForCharResponse> getCharValues() {
        return charValues;
    }

    public void setCharValues(List<CharValueForCharResponse> charValues) {
        this.charValues = charValues;
    }

    public GetListCharacteristicWithCharValResponse() {
    }

    public GetListCharacteristicWithCharValResponse(int id, String description, String unitOfMeasure, List<CharValueForCharResponse> charValues) {
        this.id = id;
        this.description = description;
        this.unitOfMeasure = unitOfMeasure;
        this.charValues = charValues;
    }
}
