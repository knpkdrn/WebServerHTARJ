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

        user.setApiKey(rs.getString("api_key"));
        user.setEmail(rs.getString("email"));
        user.setPassword(rs.getString("password_"));
        user.setIsAdmin(rs.getBoolean("is_admin"));

        return user;
    }
}
