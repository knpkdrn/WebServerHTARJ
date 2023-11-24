package org.example.api.controllers;

import org.example.tables.models.User;
import org.example.tables.models.Vehicle;
import org.example.tables.services.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/vehicles")
public class VehicleController {

    @Autowired
    private final VehicleService vehicleService;


    public VehicleController(VehicleService vehicleService) {
        this.vehicleService = vehicleService;
    }

    /** POST vehicle */
    @PostMapping
    public ResponseEntity<Vehicle> postVehicle(@RequestBody Vehicle vehicle) {
        boolean success = vehicleService.save(vehicle);

       if(success) {
           return ResponseEntity.status(HttpStatus.CREATED).body(vehicle);
       } else {
           return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
       }

    }

    /** GET vehicle by license_plate */
    @GetMapping("/licensePlate/{licensePlate}")
    public ResponseEntity<Vehicle> getVehicle(@PathVariable String licensePlate) {
        Vehicle vehicle = vehicleService.getById(licensePlate);

        if(vehicle != null) {
            return ResponseEntity.status(HttpStatus.OK).body(vehicle);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    /** GET all vehicles */
    @GetMapping("{apiKey}/getAll")
    public ResponseEntity<List<Vehicle>> getAllVehicle() {
        List<Vehicle> vehicles = vehicleService.getAll();

        if(vehicles != null) {
            return ResponseEntity.status(HttpStatus.OK).body(vehicles);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

    }

    /** DELETE vehicle by licensePlate */
    @DeleteMapping("/licensePlate/{licensePlate}")
    public ResponseEntity<Vehicle> deleteVehicle(@PathVariable String licensePlate) {
        boolean success = vehicleService.deleteById(licensePlate);

        if(success) {
            return ResponseEntity.status(HttpStatus.OK).build();
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    /** DELETE all vehicles */
    @DeleteMapping("/deleteAll")
    public ResponseEntity<Vehicle> deleteAllVehicle() {
        boolean isDone = vehicleService.deleteAll();

        if(isDone) {
            return ResponseEntity.status(HttpStatus.OK).build();
        } else {
           return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

}
