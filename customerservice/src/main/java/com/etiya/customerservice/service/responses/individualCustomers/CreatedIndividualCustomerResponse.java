package com.etiya.customerservice.service.responses.individualCustomers;

import com.etiya.customerservice.service.responses.address.CreatedAddressResponse;
import com.etiya.customerservice.service.responses.contactMedium.CreatedContactMediumResponse;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;


public class CreatedIndividualCustomerResponse {
    private UUID id;
    private String firstName;

    private String lastName;

    private String middleName;

    private String nationalId;

    private String dateOfBirth;

    private String motherName;

    private String fatherName;

    private String gender;

    // YENİ ALANLAR
    private List<CreatedAddressResponse> addresses;
    private List<CreatedContactMediumResponse> contactMediums;

    public List<CreatedAddressResponse> getAddresses() {
        return addresses;
    }

    public void setAddresses(List<CreatedAddressResponse> addresses) {
        this.addresses = addresses;
    }

    public List<CreatedContactMediumResponse> getContactMediums() {
        return contactMediums;
    }

    public void setContactMediums(List<CreatedContactMediumResponse> contactMediums) {
        this.contactMediums = contactMediums;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getNationalId() {
        return nationalId;
    }

    public void setNationalId(String nationalId) {
        this.nationalId = nationalId;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getMotherName() {
        return motherName;
    }

    public void setMotherName(String motherName) {
        this.motherName = motherName;
    }

    public String getFatherName() {
        return fatherName;
    }

    public void setFatherName(String fatherName) {
        this.fatherName = fatherName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }
}