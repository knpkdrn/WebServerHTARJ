package org.example.tables.services;

import org.example.tables.models.Driver;
import org.example.tables.models.Vehicle;
import org.example.tables.rowmappers.DriverRowMapper;
import org.example.tables.rowmappers.VehicleRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class DriverService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public boolean save(Driver driver) {
        String sql = "insert into drivers " +
                "(driver_id, " +
                "first_name, " +
                "last_name, " +
                "license_number, " +
                "phone, " +
                "email, " +
                "assigned_vehicle) " +
                "values (?, ?, ?, ?, ?, ?, ?);";

        int update = jdbcTemplate.update(
                sql,
                driver.getDriverID(),
                driver.getFirstName(),
                driver.getLastName(),
                driver.getLicenseNumber(),
                driver.getPhoneNumber(),
                driver.getEmailAddress(),
                driver.getAssignedVehicleLicense()
        );

        return update > 0;
    }

    public Driver getById(int driverId) {

        String sql = "select * from drivers where driver_id = ?";
        return jdbcTemplate.queryForObject(sql, new DriverRowMapper(), driverId);
    }

    public List<Driver> getAll() {

        String sql = "select * from drivers";
        return  jdbcTemplate.query(sql, new DriverRowMapper());
    }

    public boolean deleteById(int driverId) {
        String sql = "delete from drivers where driver_id = ?";

        try {

            int affectedRows = jdbcTemplate.update(sql, driverId);
            return (affectedRows > 0);

        } catch (Exception e) {

            e.printStackTrace();
        }

        return false;
    }

    public boolean deleteAll() {
        String sql = "delete from drivers;";

        try {

            int affectedRows = jdbcTemplate.update(sql);
            return (affectedRows > 0);

        } catch (Exception e) {

            e.printStackTrace();
        }

        return false;
    }

}
