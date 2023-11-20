package org.example.tables.rowmappers;

import org.example.tables.models.Vehicle;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class VehicleRowMapper implements RowMapper<Vehicle> {

    @Override
    public Vehicle mapRow(ResultSet rs, int rowNum) throws SQLException {
        Vehicle vehicle = new Vehicle();
        vehicle.setLicensePlate(rs.getString("license_plate"));
        vehicle.setMake(rs.getString("make"));
        vehicle.setModel(rs.getString("model"));
        vehicle.setProdYear(rs.getInt("production_year"));
        vehicle.setCost(rs.getDouble("cost"));
        vehicle.setMaxFuelInLiter(rs.getInt("max_fuel_liter"));
        vehicle.setLastRefuelling(rs.getString("last_refuelling"));
        vehicle.setLastRefuellingCost(rs.getInt("last_refuelling_cost"));
        vehicle.setCapacity(rs.getInt("capacity"));
        vehicle.setMaintenanceInterval(rs.getInt("maintenance_interval"));
        vehicle.setLastMaintenance(rs.getString("last_maintenance_date"));
        vehicle.setNextMaintenance(rs.getString("next_maintenance_date"));
        vehicle.setDoneDistance(rs.getDouble("done_distance_km"));
        vehicle.setAvgConsumption(rs.getDouble("avg_consumption"));
        vehicle.setDriverId(rs.getInt("driver_id"));
        vehicle.setShipmentId(rs.getInt("shipment_id"));
        vehicle.setVehicleStatus(rs.getString("vehicle_status"));

        return vehicle;
    }
}
