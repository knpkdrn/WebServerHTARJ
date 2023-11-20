package org.example.api.controllers;

import org.example.tables.models.Driver;
import org.example.tables.models.RequestHistory;
import org.example.tables.models.User;
import org.example.tables.services.DriverService;
import org.example.tables.services.RequestHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/drivers/")
public class DriverController {
    @Autowired
    private final DriverService driverService;
    private RequestHistory requestHistory;

    @Autowired
    private RequestHistoryService rhs;

    public DriverController (DriverService driverService) {
        this.driverService = driverService;
    }

    /** POST driver */
    @PostMapping("{apiKey}")
    public ResponseEntity<Driver> postDriver(@RequestBody Driver driver, @PathVariable String apiKey) {
        boolean success = driverService.save(driver);

        requestHistory.setRequestKey(apiKey);
        requestHistory.setRequestTime();
        requestHistory.setRequestType("POST");

        if(!User.CheckIfAdmin(apiKey)) {

            // have to do a spellcheck in the sql table!!!

            requestHistory.setResult("UNATHORIZED");
            rhs.save(requestHistory);
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        if(success) {
            requestHistory.setResult("CREATED");
            rhs.save(requestHistory);

            return ResponseEntity.status(HttpStatus.CREATED).body(driver);
        } else {
            requestHistory.setResult("BAD REQUEST");
            rhs.save(requestHistory);

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

    }

    /** GET driver by DriverId */
    @GetMapping("{apiKey}/driverId/{driverId}")
    public ResponseEntity<Driver> getDriver(@PathVariable int driverId, @PathVariable String apiKey) {
        Driver driver = driverService.getById(driverId);

        requestHistory.setRequestKey(apiKey);
        requestHistory.setRequestTime();
        requestHistory.setRequestType("GET");

        if(!User.CheckIfAdmin(apiKey)) {

            // have to do a spellcheck in the sql table!!!

            requestHistory.setResult("UNATHORIZED");
            rhs.save(requestHistory);
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        if(driver != null) {
            requestHistory.setResult("OK");
            rhs.save(requestHistory);

            return ResponseEntity.status(HttpStatus.OK).body(driver);
        } else {
            requestHistory.setResult("NOT FOUND");
            rhs.save(requestHistory);

            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    /** GET all drivers */
    @GetMapping("{apiKey}/getAll")
    public ResponseEntity<List<Driver>> getAllDrivers(@PathVariable String apiKey) {
        List<Driver> drivers = driverService.getAll();
        requestHistory.setRequestKey(apiKey);
        requestHistory.setRequestTime();
        requestHistory.setRequestType("GET");

        if(!User.CheckIfAdmin(apiKey)) {

            // have to do a spellcheck in the sql table!!!

            requestHistory.setResult("UNATHORIZED");
            rhs.save(requestHistory);
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        if(drivers != null) {
            requestHistory.setResult("OK");
            rhs.save(requestHistory);

            return ResponseEntity.status(HttpStatus.OK).body(drivers);
        } else {
            requestHistory.setResult("NOT FOUND");
            rhs.save(requestHistory);

            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    /** DELETE driver by driverID */
    @DeleteMapping("{apiKey}/driverID/{driverId}")
    public ResponseEntity<Driver> deleteDriver(@PathVariable int driverId, @PathVariable String apiKey) {
        boolean success = driverService.deleteById(driverId);

        requestHistory.setRequestKey(apiKey);
        requestHistory.setRequestTime();
        requestHistory.setRequestType("DELETE");

        if(!User.CheckIfAdmin(apiKey)) {

            // have to do a spellcheck in the sql table!!!

            requestHistory.setResult("UNATHORIZED");
            rhs.save(requestHistory);
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        if(success) {
            requestHistory.setResult("OK");
            rhs.save(requestHistory);

            return ResponseEntity.status(HttpStatus.OK).build();
        } else {
            requestHistory.setResult("NOT FOUND");
            rhs.save(requestHistory);

            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    /** DELETE all drivers */
    @DeleteMapping("{apiKey}/deleteAll")
    public ResponseEntity<Driver> deleteAllDrivers(@PathVariable String apiKey) {
        boolean isDone = driverService.deleteAll();

        requestHistory.setRequestKey(apiKey);
        requestHistory.setRequestTime();
        requestHistory.setRequestType("DELETE");

        if(!User.CheckIfAdmin(apiKey)) {

            // have to do a spellcheck in the sql table!!!

            requestHistory.setResult("UNATHORIZED");
            rhs.save(requestHistory);

            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        if(isDone) {
            requestHistory.setResult("OK");
            rhs.save(requestHistory);

            return ResponseEntity.status(HttpStatus.OK).build();
        } else {
            requestHistory.setResult("NOT FOUND");
            rhs.save(requestHistory);

            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

    }

}
