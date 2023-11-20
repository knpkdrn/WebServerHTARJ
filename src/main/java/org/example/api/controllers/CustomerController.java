package org.example.api.controllers;

import org.example.tables.models.Customer;
import org.example.tables.models.RequestHistory;
import org.example.tables.models.User;
import org.example.tables.services.CustomerService;
import org.example.tables.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/customers/")
public class CustomerController {

    @Autowired
    private final CustomerService customerService;
    private RequestHistory requestHistory;

    public CustomerController (CustomerService customerService) {
        this.customerService = customerService;
    }

    /** POST customer */
    @PostMapping("{apiKey}")
    public ResponseEntity<Customer> postCustomer(@RequestBody Customer customer, @PathVariable String apiKey) {
        boolean success = customerService.save(customer);

        requestHistory.setRequestKey(apiKey);
        requestHistory.setRequestTime();
        requestHistory.setRequestType("POST");

        if(!User.CheckIfAdmin(apiKey)) {

            // have to do a spellcheck in the sql table!!!

            requestHistory.setResult("UNATHORIZED");
            requestHistory.uploadRequest();
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        if(success) {
            requestHistory.setResult("CREATED");
            requestHistory.uploadRequest();

            return ResponseEntity.status(HttpStatus.CREATED).body(customer);
        } else {
            requestHistory.setResult("BAD REQUEST");
            requestHistory.uploadRequest();

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

    }

    /** GET customer by CustomerID */
    @GetMapping("{apiKey}/customerId/{customerId}")
    public ResponseEntity<Customer> getCustomer(@PathVariable int customerId, @PathVariable String apiKey) {
        Customer customer = customerService.getById(customerId);

        requestHistory.setRequestKey(apiKey);
        requestHistory.setRequestTime();
        requestHistory.setRequestType("GET");

        if(!User.CheckIfAdmin(apiKey)) {

            // have to do a spellcheck in the sql table!!!

            requestHistory.setResult("UNATHORIZED");
            requestHistory.uploadRequest();
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        if(customer != null) {
            requestHistory.setResult("OK");
            requestHistory.uploadRequest();

            return ResponseEntity.status(HttpStatus.OK).body(customer);
        } else {
            requestHistory.setResult("NOT FOUND");
            requestHistory.uploadRequest();

            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    /** GET all customers */
    @GetMapping("{apiKey}/getAll")
    public ResponseEntity<List<Customer>> getAllCustomers(@PathVariable String apiKey) {
        List<Customer> customers = customerService.getAll();

        requestHistory.setRequestKey(apiKey);
        requestHistory.setRequestTime();
        requestHistory.setRequestType("GET");

        if(!User.CheckIfAdmin(apiKey)) {

            // have to do a spellcheck in the sql table!!!

            requestHistory.setResult("UNATHORIZED");
            requestHistory.uploadRequest();
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        if(customers != null) {
            requestHistory.setResult("OK");
            requestHistory.uploadRequest();

            return ResponseEntity.status(HttpStatus.OK).body(customers);
        } else {
            requestHistory.setResult("NOT FOUND");
            requestHistory.uploadRequest();

            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

    }

    /** DELETE customer by customerId*/
    @DeleteMapping("{apiKey}/customerId/{customerId}")
    public ResponseEntity<Customer> deleteCustomer(@PathVariable int customerId, @PathVariable String apiKey) {
        boolean success = customerService.deleteById(customerId);

        requestHistory.setRequestKey(apiKey);
        requestHistory.setRequestTime();
        requestHistory.setRequestType("DELETE");

        if(!User.CheckIfAdmin(apiKey)) {

            // have to do a spellcheck in the sql table!!!

            requestHistory.setResult("UNATHORIZED");
            requestHistory.uploadRequest();
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        if(success) {
            requestHistory.setResult("OK");
            requestHistory.uploadRequest();

            return ResponseEntity.status(HttpStatus.OK).build();
        } else {
            requestHistory.setResult("NOT FOUND");
            requestHistory.uploadRequest();

            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    /** DELETE all customer */
    @DeleteMapping("{apiKey}/deleteAll")
    public ResponseEntity<Customer> deleteAllCustomers(@PathVariable String apiKey) {
        boolean isDone = customerService.deleteAll();

        requestHistory.setRequestKey(apiKey);
        requestHistory.setRequestTime();
        requestHistory.setRequestType("DELETE");

        if(!User.CheckIfAdmin(apiKey)) {

            // have to do a spellcheck in the sql table!!!

            requestHistory.setResult("UNATHORIZED");
            requestHistory.uploadRequest();
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        if(isDone) {
            requestHistory.setResult("OK");
            requestHistory.uploadRequest();

            return ResponseEntity.status(HttpStatus.OK).build();
        } else {
            requestHistory.setResult("NOT FOUND");
            requestHistory.uploadRequest();

            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

    }
}
