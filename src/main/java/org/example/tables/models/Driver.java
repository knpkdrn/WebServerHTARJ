package org.example.tables.models;

public class Driver {
    private int driverId;
    private String firstName;
    private String lastName;
    private String licenseNumber;
    private String phoneNumber;
    private String emailAddress;
    private String assignedVehicleLicense;

    public int getDriverId() {
        return driverId;
    }
    public String getFirstName() {
        return firstName;
    }
    public String getLastName() {
        return lastName;
    }
    public String getLicenseNumber() {
        return licenseNumber;
    }
    public String getPhoneNumber() {
        return phoneNumber;
    }
    public String getEmailAddress() {
        return emailAddress;
    }
    public String getAssignedVehicleLicense() {
        return assignedVehicleLicense;
    }
    public void setDriverId(int driverID) {
        this.driverId = driverID;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public void setLicenseNumber(String licenseNumber) {
        this.licenseNumber = licenseNumber;
    }
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }
    public void setAssignedVehicleLicense(String assignedVehicleLicense) {
        this.assignedVehicleLicense = assignedVehicleLicense;
    }
}

