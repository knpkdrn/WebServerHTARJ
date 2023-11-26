package org.example.tables.services;

import org.example.tables.models.Driver;
import org.example.tables.models.User;
import org.example.tables.rowmappers.DriverRowMapper;
import org.example.tables.rowmappers.UserRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class UserService {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public boolean save(User user) {
        String sql = "insert into drivers " +
                "(email, " +
                "password_, " +
                "is_admin " +
                "values (?, ?, ?, ?);";

        int update = jdbcTemplate.update(
                sql,
                user.getEmail(),
                user.getPassword(),
                user.getIsAdmin()
        );

        return update > 0;
    }

    public User getByKey(String key) {
        String sql = "select * from users where api_key = ?";
        return jdbcTemplate.queryForObject(sql, new UserRowMapper(), key);
    }
}
