package org.example.api.controllers;

import org.example.tables.models.RequestHistory;
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
@RequestMapping("/api/vehicles/")
public class VehicleController {

    @Autowired
    private final VehicleService vehicleService;
    private RequestHistory requestHistory;

    public VehicleController(VehicleService vehicleService) {
        this.vehicleService = vehicleService;
    }

    /** POST vehicle */
    @PostMapping("{apiKey}")
    public ResponseEntity<Vehicle> postVehicle(@RequestBody Vehicle vehicle, @PathVariable String apiKey) {
        boolean success = vehicleService.save(vehicle);

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

            return ResponseEntity.status(HttpStatus.CREATED).body(vehicle);
        } else {
            requestHistory.setResult("BAD REQUEST");
            requestHistory.uploadRequest();

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

    }

    /** GET vehicle by license_plate */
    @GetMapping("{apiKey}/licensePlate/{licensePlate}")
    public ResponseEntity<Vehicle> getVehicle(@PathVariable String licensePlate, @PathVariable String apiKey) {
        Vehicle vehicle = vehicleService.getById(licensePlate);

        requestHistory.setRequestKey(apiKey);
        requestHistory.setRequestTime();
        requestHistory.setRequestType("GET");

        if(!User.CheckIfAdmin(apiKey)) {

            // have to do a spellcheck in the sql table!!!

            requestHistory.setResult("UNATHORIZED");
            requestHistory.uploadRequest();
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        if(vehicle != null) {
            requestHistory.setResult("OK");
            requestHistory.uploadRequest();

            return ResponseEntity.status(HttpStatus.OK).body(vehicle);
        } else {
            requestHistory.setResult("NOT FOUND");
            requestHistory.uploadRequest();

            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    /** GET all vehicles */
    @GetMapping("{apiKey}/getAll")
    public ResponseEntity<List<Vehicle>> getAllVehicle(@PathVariable String apiKey) {
        List<Vehicle> vehicles = vehicleService.getAll();

        requestHistory.setRequestKey(apiKey);
        requestHistory.setRequestTime();
        requestHistory.setRequestType("GET");

        if(!User.CheckIfAdmin(apiKey)) {

            // have to do a spellcheck in the sql table!!!

            requestHistory.setResult("UNATHORIZED");
            requestHistory.uploadRequest();
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        if(vehicles != null) {
            requestHistory.setResult("OK");
            requestHistory.uploadRequest();

            return ResponseEntity.status(HttpStatus.OK).body(vehicles);
        } else {
            requestHistory.setResult("NOT FOUND");
            requestHistory.uploadRequest();

            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

    }

    /** DELETE vehicle by licensePlate */
    @DeleteMapping("{apiKey}/licensePlate/{licensePlate}")
    public ResponseEntity<Vehicle> deleteVehicle(@PathVariable String licensePlate, @PathVariable String apiKey) {
        boolean success = vehicleService.deleteById(licensePlate);

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

    /** DELETE all vehicles */
    @DeleteMapping("{apiKey}/deleteAll")
    public ResponseEntity<Vehicle> deleteAllVehicle(@PathVariable String apiKey) {
        boolean isDone = vehicleService.deleteAll();

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
