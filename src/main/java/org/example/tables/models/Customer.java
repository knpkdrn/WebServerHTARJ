package org.example.tables.models;

public class Customer {
    private int customerId;
    private String fullName;
    private String address;
    private String phoneNumber;
    private String emailAddress;
    private String password;
    private String apiKey;

    public int getCustomerId() {
        return customerId;
    }
    public String getFullName() {
        return fullName;
    }
    public String getAddress() {
        return address;
    }
    public String getPhoneNumber() {
        return phoneNumber;
    }
    public String getEmailAddress() {
        return emailAddress;
    }
    public String getPassword() {
        return password;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }
    public void setFullName(String fullName) {
        this.fullName = fullName;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }
    public void setPassword(String password) {
        this.password = password;
    }
}
