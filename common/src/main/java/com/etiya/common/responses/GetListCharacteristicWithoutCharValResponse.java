package com.etiya.common.responses;

import java.util.List;

public class GetListCharacteristicWithoutCharValResponse {

    private int id;

    private String description;

    private String unitOfMeasure;

    private boolean isRequired;

    private CharValueForCharResponse charValue;

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

    public boolean isRequired() {
        return isRequired;
    }

    public void setRequired(boolean required) {
        isRequired = required;
    }

    public CharValueForCharResponse getCharValue() {
        return charValue;
    }

    public void setCharValue(CharValueForCharResponse charValue) {
        this.charValue = charValue;
    }
}
