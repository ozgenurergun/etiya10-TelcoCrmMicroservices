package com.etiya.catalogservice.service.dtos.responses.GENELTYPE;

import jakarta.persistence.Column;

public class CreatedGenelTypeResponse {

    private int id;

    private String name;

    private String entName;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEntName() {
        return entName;
    }

    public void setEntName(String entName) {
        this.entName = entName;
    }

    public CreatedGenelTypeResponse() {
    }

    public CreatedGenelTypeResponse(int id, String name, String entName) {
        this.id = id;
        this.name = name;
        this.entName = entName;
    }
}
