package org.example.tables.services;

import org.example.tables.models.Customer;
import org.example.tables.models.Driver;
import org.example.tables.rowmappers.CustomerRowMapper;
import org.example.tables.rowmappers.DriverRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CustomerService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public boolean save(Customer customer) {
        String sql = "insert into drivers " +
                "(customer_id, " +
                "full_name, " +
                "address, " +
                "phone_number, " +
                "email, " +
                "password_) " +
                "values (?, ?, ?, ?, ?, ?);";

        int update = jdbcTemplate.update(
                sql,
                customer.getCustomerId(),
                customer.getFullName(),
                customer.getAddress(),
                customer.getPhoneNumber(),
                customer.getEmailAddress()
        );

        return update > 0;
    }

    public Customer getById(int customerId) {

        String sql = "select * from customers where customer_id = ?";
        return jdbcTemplate.queryForObject(sql, new CustomerRowMapper(), customerId);
    }

    public List<Customer> getAll() {

        String sql = "select * from customers";
        return  jdbcTemplate.query(sql, new CustomerRowMapper());
    }

    public boolean deleteById(int customerId) {
        String sql = "delete from customers where customer_id = ?";

        try {

            int affectedRows = jdbcTemplate.update(sql, customerId);
            return (affectedRows > 0);

        } catch (Exception e) {

            e.printStackTrace();
        }

        return false;
    }

    public boolean deleteAll() {
        String sql = "delete from customers;";

        try {

            int affectedRows = jdbcTemplate.update(sql);
            return (affectedRows > 0);

        } catch (Exception e) {

            e.printStackTrace();
        }

        return false;
    }

}
