package com.etiya.customerservice.service.requests.address;

import com.etiya.customerservice.service.messages.Messages;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

// Bu DTO, ana müşteri kaydı içinde gelecek olan adres listesi içindir
public class CreateAddressInCustomerRequest {
    @NotBlank(message = Messages.StreetRequired)
    private String street;

    @NotBlank(message = Messages.HouseNumberRequired)
    private String houseNumber;

    @Size(min = 10, message = Messages.DescriptionSize)
    @NotBlank(message = Messages.DescriptionRequired)
    private String description;

    private boolean isDefault; // 'default' keyword olduğu için 'isDefault' daha iyidir

    private int districtId;

    // Getters and Setters...

    public String getStreet() { return street; }
    public void setStreet(String street) { this.street = street; }
    public String getHouseNumber() { return houseNumber; }
    public void setHouseNumber(String houseNumber) { this.houseNumber = houseNumber; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public boolean isDefault() { return isDefault; }
    public void setDefault(boolean aDefault) { isDefault = aDefault; }
    public int getDistrictId() { return districtId; }
    public void setDistrictId(int districtId) { this.districtId = districtId; }
}
