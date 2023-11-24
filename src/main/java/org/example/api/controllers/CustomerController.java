package org.example.api.controllers;

import org.example.tables.models.Customer;
import org.example.tables.models.User;
import org.example.tables.services.CustomerService;
import org.example.tables.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {

    @Autowired
    private final CustomerService customerService;
    public CustomerController (CustomerService customerService) {
        this.customerService = customerService;
    }

    /** POST customer */
    @PostMapping
    public ResponseEntity<Customer> postCustomer(@RequestBody Customer customer) {
        boolean success = customerService.save(customer);

        if(success) {
            return ResponseEntity.status(HttpStatus.CREATED).body(customer);
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    /** GET customer by CustomerID */
    @GetMapping("/customerId/{customerId}")
    public ResponseEntity<Customer> getCustomer(@PathVariable int customerId) {
        Customer customer = customerService.getById(customerId);


        if(customer != null) {
            return ResponseEntity.status(HttpStatus.OK).body(customer);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    /** GET all customers */
    @GetMapping("/getAll")
    public ResponseEntity<List<Customer>> getAllCustomers() {
        List<Customer> customers = customerService.getAll();

        if(customers != null) {
            return ResponseEntity.status(HttpStatus.OK).body(customers);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    /** DELETE customer by customerId */
    @DeleteMapping("/customerId/{customerId}")
    public ResponseEntity<Customer> deleteCustomer(@PathVariable int customerId) {
        boolean success = customerService.deleteById(customerId);

        if(success) {
            return ResponseEntity.status(HttpStatus.OK).build();
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    /** DELETE all customer */
    @DeleteMapping("/deleteAll")
    public ResponseEntity<Customer> deleteAllCustomers() {
        boolean isDone = customerService.deleteAll();

        if(isDone) {
            return ResponseEntity.status(HttpStatus.OK).build();
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}
