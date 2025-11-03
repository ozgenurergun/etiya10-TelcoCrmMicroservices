package com.etiya.catalogservice.service.dtos.requests.GENELSTATUS;

public class CreateGenelStatusRequest {

    private String name;

    private String entName;

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

    public CreateGenelStatusRequest() {
    }

    public CreateGenelStatusRequest(String name, String entName) {
        this.name = name;
        this.entName = entName;
    }
}
