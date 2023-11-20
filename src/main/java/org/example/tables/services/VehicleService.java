package org.example.tables.services;

import org.example.tables.models.Vehicle;
import org.example.tables.rowmappers.VehicleRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.List;

@Repository
public class VehicleService{

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public boolean save(Vehicle vehicle) {
        String sql = "INSERT INTO vehicles " +
                "(license_plate, " +
                "make, " +
                "model, " +
                "production_year, " +
                "cost, " +
                "max_fuel_liter, " +
                "last_refuelling, " +
                "last_refuelling_cost, " +
                "capacity, " +
                "maintenance_interval, " +
                "last_maintenance_date, " +
                "next_maintenance_date, " +
                "done_distance_km, " +
                "avg_consumption, " +
                "driver_id, " +
                "shipment_id, " +
                "vehicle_status) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";

        int update = jdbcTemplate.update(
                sql,
                vehicle.getLicensePlate(),
                vehicle.getMake(),
                vehicle.getModel(),
                vehicle.getProdYear(),
                vehicle.getCost(),
                vehicle.getMaxFuelInLiter(),
                vehicle.getLastRefuelling(),
                vehicle.getLastRefuellingCost(),
                vehicle.getCapacity(),
                vehicle.getMaintenanceInterval(),
                vehicle.getLastMaintenance(),
                vehicle.getNextMaintenance(),
                vehicle.getDoneDistance(),
                vehicle.getAvgConsumption(),
                vehicle.getDriverId(),
                vehicle.getShipmentId(),
                vehicle.getVehicleStatus()
        );

        return update > 0;
    }

    public Vehicle getById(String licensePlate) {

        String sql = "select * from vehicles where license_plate = ?";
        return jdbcTemplate.queryForObject(sql, new VehicleRowMapper(), licensePlate);
    }

    public List<Vehicle> getAll() {

        String sql = "select * from vehicles";
        return  jdbcTemplate.query(sql, new VehicleRowMapper());
    }

    public boolean deleteById(String licensePlate) {
        String sql = "delete from vehicles where license_plate = ?";

        try {

            int affectedRows = jdbcTemplate.update(sql, licensePlate);
            return (affectedRows > 0);

        } catch (Exception e) {

            e.printStackTrace();
        }

        return false;
    }

    public boolean deleteAll() {
        String sql = "delete from vehicles;";

        try {

            int affectedRows = jdbcTemplate.update(sql);
            return (affectedRows > 0);

        } catch (Exception e) {

            e.printStackTrace();
        }

        return false;
    }
}
