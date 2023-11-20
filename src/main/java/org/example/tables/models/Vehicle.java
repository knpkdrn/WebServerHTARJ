package org.example.tables.models;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

public class Vehicle {

    private String licensePlate;
    private String make;
    private String model;
    private int prodYear;
    private Double cost;
    private int maxFuelInLiter;
    private String lastRefuelling;
    private Integer lastRefuellingCost;
    private int capacity;
    private int maintenanceInterval;
    private String lastMaintenance;
    private String nextMaintenance;
    private Double doneDistance;
    private Double avgConsumption;
    private Integer driverId; // This can be null, so use the Integer class
    private Integer shipmentId; // This can be null, so use the Integer class
    private String vehicleStatus;


    public String getLicensePlate() {
        return licensePlate;
    }
    public String getMake() {
        return make;
    }
    public String getModel() {
        return model;
    }
    public int getProdYear() {
        return prodYear;
    }
    public Double getCost() {
        return cost;
    }
    public int getMaxFuelInLiter() {
        return maxFuelInLiter;
    }
    public String getLastRefuelling() {
        return lastRefuelling;
    }
    public Integer getLastRefuellingCost() {
        return lastRefuellingCost;
    }
    public int getCapacity() {
        return capacity;
    }
    public int getMaintenanceInterval() {
        return maintenanceInterval;
    }
    public String getLastMaintenance() {
        return lastMaintenance;
    }
    public String getNextMaintenance() {
        return nextMaintenance;
    }
    public Double getDoneDistance() {
        return doneDistance;
    }
    public Double getAvgConsumption() {
        return avgConsumption;
    }
    public Integer getDriverId() {
        return driverId;
    }
    public Integer getShipmentId() {
        return shipmentId;
    }
    public String getVehicleStatus() {
        return vehicleStatus;
    }
    public void setLicensePlate(String lPlate) {
        licensePlate = lPlate;
    }
    public void setMake(String m) {
        make = m;
    }
    public void setModel(String m) {
        model = m;
    }
    public void setProdYear(int year) {
        prodYear = year;
    }
    public void setCost(Double c) {
        cost = c;
    }
    public void setMaxFuelInLiter(int i) {
        maxFuelInLiter = i;
    }
    public void setLastRefuelling(String s) {
        lastRefuelling = s;
    }
    public void setLastRefuellingCost(Integer i) {
        lastRefuellingCost = i;
    }
    public void setCapacity(int i) {
        capacity = i;
    }
    public void setMaintenanceInterval(int i) {
        maintenanceInterval = i;
    }
    public void setLastMaintenance(String s) {
        lastMaintenance = s;
    }
    public void setNextMaintenance(String s) {
        nextMaintenance = s;
    }
    public void setDoneDistance(Double distance) {
        doneDistance = distance;
    }
    public void setAvgConsumption(Double avg) {
        avgConsumption = avg;
    }
    public void setDriverId(Integer id) {
        driverId = id;
    }
    public void setShipmentId(Integer id) {
        shipmentId = id;
    }
    public void setVehicleStatus(String s) {
        vehicleStatus = s;
    }

    public String toString() {
        return new String(
                "license_plate=" + licensePlate + "; " +
                        " make=" + make + ";" +
                        " model=" + model + "; " +
                        " production_year=" + prodYear + "; " +
                        " cost=" + cost + "; " +
                        " max_fuel_liter=" + maxFuelInLiter + "; " +
                        " last_refuelling=" + lastRefuelling + "; " +
                        " last_refuelling_cost=" + lastRefuellingCost + "; " +
                        " capacity=" + capacity + "; " +
                        " maintenance_interval=" + maintenanceInterval + "; " +
                        " last_maintenance_date=" + lastMaintenance + "; " +
                        " next_maintenance_date=" + nextMaintenance + "; " +
                        " done_distance_km=" + doneDistance + "; " +
                        " avg_consumption=" + avgConsumption + "; " +
                        " driver_id=" + driverId + "; " +
                        " shipment_id=" + shipmentId + "; " +
                        " vehicle_status=" + vehicleStatus + "; ");
    }

}
