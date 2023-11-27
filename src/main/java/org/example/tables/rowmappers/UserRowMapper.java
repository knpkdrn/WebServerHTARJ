package org.example.tables.rowmappers;

import org.example.tables.models.Customer;
import org.example.tables.models.Driver;
import org.example.tables.models.User;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserRowMapper implements RowMapper<User> {

    @Override
    public User mapRow(ResultSet rs, int rowNum) throws SQLException {
        User user = new User();

        user.setEmail(rs.getString("email"));
        user.setUsername(rs.getString("username"));
        user.setPassword(rs.getString("password_"));
        user.setIsAdmin(rs.getBoolean("is_admin"));
        user.setWasLoggedIn(rs.getBoolean("was_logged_in"));
        return user;
    }
}
