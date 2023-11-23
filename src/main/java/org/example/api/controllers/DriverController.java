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
@RequestMapping("/api/drivers/")
public class DriverController {
    @Autowired
    private final DriverService driverService;

    public DriverController (DriverService driverService) {
        this.driverService = driverService;
    }

    /** POST driver */
    @PostMapping("{apiKey}")
    public ResponseEntity<Driver> postDriver(@RequestBody Driver driver, @PathVariable String apiKey) {
        boolean success = driverService.save(driver);

        if(!User.CheckIfAdmin(apiKey)) {
            // have to do a spellcheck in the sql table!!!
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        if(success) {
            return ResponseEntity.status(HttpStatus.CREATED).body(driver);
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    /** GET driver by DriverId */
    @GetMapping("{apiKey}/driverId/{driverId}")
    public ResponseEntity<Driver> getDriver(@PathVariable int driverId, @PathVariable String apiKey) {
        Driver driver = driverService.getById(driverId);

        if(!User.CheckIfAdmin(apiKey)) {
            // have to do a spellcheck in the sql table!!!
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        if(driver != null) {
            return ResponseEntity.status(HttpStatus.OK).body(driver);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    /** GET all drivers */
    @GetMapping("{apiKey}/getAll")
    public ResponseEntity<List<Driver>> getAllDrivers(@PathVariable String apiKey) {
        List<Driver> drivers = driverService.getAll();

        if(!User.CheckIfAdmin(apiKey)) {
            // have to do a spellcheck in the sql table!!!
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        if(drivers != null) {
            return ResponseEntity.status(HttpStatus.OK).body(drivers);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    /** DELETE driver by driverID */
    @DeleteMapping("{apiKey}/driverID/{driverId}")
    public ResponseEntity<Driver> deleteDriver(@PathVariable int driverId, @PathVariable String apiKey) {
        boolean success = driverService.deleteById(driverId);

        if(!User.CheckIfAdmin(apiKey)) {
            // have to do a spellcheck in the sql table!!!
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        if(success) {
            return ResponseEntity.status(HttpStatus.OK).build();
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    /** DELETE all drivers */
    @DeleteMapping("{apiKey}/deleteAll")
    public ResponseEntity<Driver> deleteAllDrivers(@PathVariable String apiKey) {
        boolean isDone = driverService.deleteAll();

        if(!User.CheckIfAdmin(apiKey)) {
            // have to do a spellcheck in the sql table!!!
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        if(isDone) {
            return ResponseEntity.status(HttpStatus.OK).build();
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

}
