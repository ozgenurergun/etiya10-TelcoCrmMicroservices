package com.etiya.common.responses;

import java.io.Serializable;

public class CharValueForCharResponse implements Serializable {
    private int id;
    private String value;

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

    public CharValueForCharResponse() {
    }

    public CharValueForCharResponse(int id, String value) {
        this.id = id;
        this.value = value;
    }
}
