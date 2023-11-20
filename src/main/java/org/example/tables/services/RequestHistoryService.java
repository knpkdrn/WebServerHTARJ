package org.example.tables.services;

import org.example.tables.models.Driver;
import org.example.tables.models.RequestHistory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class RequestHistoryService {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void save(RequestHistory rH) {
        String sql = "insert into request_history " +
                "(request_type, " +
                "request_time, " +
                "result, " +
                "request_key ) " +
                "values (?, ?, ?, ?);";

        int update = jdbcTemplate.update(
                sql,
                rH.getRequestType(),
                rH.getRequestTime(),
                rH.getResult(),
                rH.getRequestKey()
        );
    }
}
