package org.example.tables.services;

import org.example.tables.models.Driver;
import org.example.tables.models.Shipment;
import org.example.tables.rowmappers.DriverRowMapper;
import org.example.tables.rowmappers.ShipmentRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ShipmentService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public boolean save(Shipment shipment) {
        String sql = "insert into shipment " +
                "(shipment_id, " +
                "customer_id, " +
                "first_possible_start_time, " +
                "last_possible_start_time, " +
                "origin, " +
                "destination, " +
                "shipment_status) " +
                "values (?, ?, ?, ?, ?, ?, ?);";

        int update = jdbcTemplate.update(
                sql,
                shipment.getShipmentId(),
                shipment.getCustomerId(),
                shipment.getStartTime(),
                shipment.getEndTime(),
                shipment.getOrigin(),
                shipment.getDestination(),
                shipment.getShipmentStatus()
        );

        return update > 0;
    }

    public Shipment getById(int shipmentId) {

        String sql = "select * from shipment where shipment_id = ?";
        return jdbcTemplate.queryForObject(sql, new ShipmentRowMapper(), shipmentId);
    }

    public List<Shipment> getAll() {

        String sql = "select * from shipment";
        return  jdbcTemplate.query(sql, new ShipmentRowMapper());
    }

    public List<Shipment> getByShipmentStatus(String status) {

        String sql = "select * from shipment where shipment_status = ?";
        return  jdbcTemplate.query(sql, new ShipmentRowMapper(), status);
    }

    public boolean updateShipmentStatusByOldStatus(String oldStatus, String newStatus) {
        String sql = "update shipment set shipment_status = ? where shipment_status = ?";

        try {

            int affectedRows = jdbcTemplate.update(sql, newStatus, oldStatus);
            return (affectedRows > 0);

        } catch (Exception e) {

            e.printStackTrace();
        }

        return false;
    }

    public boolean deleteById(int shipmentId) {
        String sql = "delete from shipment where shipment_id = ?";

        try {

            int affectedRows = jdbcTemplate.update(sql, shipmentId);
            return (affectedRows > 0);

        } catch (Exception e) {

            e.printStackTrace();
        }

        return false;
    }

    public boolean deleteAll() {
        String sql = "delete from shipment;";

        try {

            int affectedRows = jdbcTemplate.update(sql);
            return (affectedRows > 0);

        } catch (Exception e) {

            e.printStackTrace();
        }

        return false;
    }
}
