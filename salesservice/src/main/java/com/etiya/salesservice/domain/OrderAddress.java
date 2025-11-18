package com.etiya.salesservice.domain;

public class OrderAddress {
    private String street;
    private String houseNumber;
    private String description;
    private String city;
    private String district;

    public OrderAddress(String street, String houseNumber, String description, String city, String district) {
        this.street = street;
        this.houseNumber = houseNumber;
        this.description = description;
        this.city = city;
        this.district = district;
    }

    public OrderAddress() {}
    
    public String getStreet() { return street; }
    public void setStreet(String street) { this.street = street; }
    public String getHouseNumber() { return houseNumber; }
    public void setHouseNumber(String houseNumber) { this.houseNumber = houseNumber; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public String getCity() { return city; }
    public void setCity(String city) { this.city = city; }
    public String getDistrict() { return district; }
    public void setDistrict(String district) { this.district = district; }
}
