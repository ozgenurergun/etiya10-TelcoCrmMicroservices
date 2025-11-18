package com.etiya.salesservice.domain;

public class OrderCustomer {
    private String originalCustomerId;
    private String firstName;
    private String middleName;
    private String lastName;
    private String customerNumber;
    private String nationalId;

    public String getOriginalCustomerId() {
        return originalCustomerId;
    }

    public void setOriginalCustomerId(String originalCustomerId) {
        this.originalCustomerId = originalCustomerId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getCustomerNumber() {
        return customerNumber;
    }

    public void setCustomerNumber(String customerNumber) {
        this.customerNumber = customerNumber;
    }

    public String getNationalId() {
        return nationalId;
    }

    public void setNationalId(String nationalId) {
        this.nationalId = nationalId;
    }

    public OrderCustomer() {
    }

    public OrderCustomer(String originalCustomerId, String firstName, String middleName, String lastName, String customerNumber, String nationalId) {
        this.originalCustomerId = originalCustomerId;
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.customerNumber = customerNumber;
        this.nationalId = nationalId;
    }
}
