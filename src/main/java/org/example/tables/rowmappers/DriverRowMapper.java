package org.example.tables.rowmappers;

import org.example.tables.models.Driver;
import org.example.tables.models.Vehicle;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class DriverRowMapper implements RowMapper<Driver> {
    @Override
    public Driver mapRow(ResultSet rs, int rowNum) throws SQLException {
        Driver driver = new Driver();

        driver.setDriverID(rs.getInt("driver_id"));
        driver.setFirstName(rs.getString("first_name"));
        driver.setLastName(rs.getString("last_name"));
        driver.setLicenseNumber(rs.getString("license_number"));
        driver.setPhoneNumber(rs.getString("phone"));
        driver.setEmailAddress(rs.getString("email"));
        driver.setAssignedVehicleLicense(rs.getString("assigned_vehicle"));

        return driver;
    }
}
