package com.etiya.salesservice.service.dtos.responses;

public class CustomerServiceAddressResponse {
    private int id;
    private String street;
    private String houseNumber;
    private String description;
    private String cityName;     // Customer Service'ten gelen JSON alan adıyla birebir aynı olmalı
    private String districtName;

    // Getter Setter...
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getStreet() { return street; }
    public void setStreet(String street) { this.street = street; }
    public String getHouseNumber() { return houseNumber; }
    public void setHouseNumber(String houseNumber) { this.houseNumber = houseNumber; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public String getCityName() { return cityName; }
    public void setCityName(String cityName) { this.cityName = cityName; }
    public String getDistrictName() { return districtName; }
    public void setDistrictName(String districtName) { this.districtName = districtName; }
}
