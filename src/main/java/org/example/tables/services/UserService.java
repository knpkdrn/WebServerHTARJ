package org.example.tables.services;

import org.example.tables.models.Driver;
import org.example.tables.models.User;
import org.example.tables.rowmappers.DriverRowMapper;
import org.example.tables.rowmappers.UserRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class UserService {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public boolean save(User user) {
        String sql = "insert into users " +
                "(email, " +
                "username, " +
                "password_, " +
                "is_admin," +
                "was_logged_in) " +
                "values (?, ?, ?, ?, ?);";

        int update = jdbcTemplate.update(
                sql,
                user.getEmail(),
                user.getUsername(),
                user.getPassword(),
                user.getIsAdmin(),
                false
        );

        return update > 0;
    }

    public User getById(String email) {
        String sql = "select * from users where email = ?";
        return jdbcTemplate.queryForObject(sql, new UserRowMapper(), email);
    }

    public boolean updatePassword(String email, String password) {
        String sql = "update users set password_ = ? , was_logged_in = true where email = ?";
        return (jdbcTemplate.update(sql, password, email) <= 1);
    }

    public User validateLogIn(String email, String password){
        String sql = "select * from users where email = ? and password_ = ?";
        return jdbcTemplate.queryForObject(sql, new UserRowMapper(), email, password);
    }
}
