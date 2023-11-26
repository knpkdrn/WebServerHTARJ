package org.example.api.controllers;

import org.example.tables.models.Driver;
import org.example.tables.models.User;
import org.example.tables.services.DriverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/drivers")
public class DriverController {
    @Autowired
    private final DriverService driverService;

    public DriverController (DriverService driverService) {
        this.driverService = driverService;
    }

    /** POST driver */
    @PostMapping
    public ResponseEntity<Driver> postDriver(@RequestBody Driver driver) {
        boolean success = driverService.save(driver);

        if(success) {

            return ResponseEntity.status(HttpStatus.CREATED).body(driver);
        } else {

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    /** GET driver by DriverId */
    @GetMapping("/driverId/{driverId}")
    public ResponseEntity<Driver> getDriver(@PathVariable int driverId) {
        Driver driver = driverService.getById(driverId);

        if(driver != null) {
            return ResponseEntity.status(HttpStatus.OK).body(driver);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    /** GET all drivers */
    @GetMapping("/getAll")
    public ResponseEntity<List<Driver>> getAllDrivers() {
        List<Driver> drivers = driverService.getAll();

        if(drivers != null) {
            return ResponseEntity.status(HttpStatus.OK).body(drivers);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    /** DELETE driver by driverID */
    @DeleteMapping("/driverID/{driverId}")
    public ResponseEntity<Driver> deleteDriver(@PathVariable int driverId) {
        boolean success = driverService.deleteById(driverId);

        if(success) {
            return ResponseEntity.status(HttpStatus.OK).build();
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    /** DELETE all drivers */
    @DeleteMapping("/deleteAll")
    public ResponseEntity<Driver> deleteAllDrivers() {
        boolean isDone = driverService.deleteAll();

        if(isDone) {
            return ResponseEntity.status(HttpStatus.OK).build();
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

}
