package org.example.tables.rowmappers;

import org.example.tables.models.Driver;
import org.example.tables.models.Shipment;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ShipmentRowMapper implements RowMapper<Shipment> {

    @Override
    public Shipment mapRow(ResultSet rs, int rowNum) throws SQLException {
        Shipment shipment = new Shipment();

        shipment.setShipmentId(rs.getInt("shipment_id"));
        shipment.setCustomerId(rs.getInt("customer_id"));
        shipment.setStartTime(rs.getString("first_possible_start_time"));
        shipment.setEndTime(rs.getString("last_possible_end_time"));
        shipment.setOrigin(rs.getString("origin"));
        shipment.setDestination(rs.getString("destination"));
        shipment.setShipmentStatus(rs.getString("shipment_status"));

        return shipment;
    }

}
