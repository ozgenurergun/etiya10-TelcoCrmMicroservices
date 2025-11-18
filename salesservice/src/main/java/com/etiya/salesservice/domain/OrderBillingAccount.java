package com.etiya.salesservice.domain;

public class OrderBillingAccount {
    private String accountNumber;
    private String accountName;
    private String type;   // Enum yerine String tutmak Mongo'da daha güvenlidir
    private String status; // Sipariş anındaki durumu

    // Boş Constructor
    public OrderBillingAccount() {
    }

    // Tam Constructor
    public OrderBillingAccount(String accountNumber, String accountName, String type, String status) {
        this.accountNumber = accountNumber;
        this.accountName = accountName;
        this.type = type;
        this.status = status;
    }

    public String getAccountNumber() { return accountNumber; }
    public void setAccountNumber(String accountNumber) { this.accountNumber = accountNumber; }
    public String getAccountName() { return accountName; }
    public void setAccountName(String accountName) { this.accountName = accountName; }
    public String getType() { return type; }
    public void setType(String type) { this.type = type; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}
