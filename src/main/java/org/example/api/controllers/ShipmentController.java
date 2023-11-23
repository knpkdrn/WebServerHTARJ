package org.example.api.controllers;

import org.example.api.controllers.googlemaps.GoogleAPIController;
import org.example.tables.models.Shipment;
import org.example.tables.models.User;
import org.example.tables.services.ShipmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/shipments/")
public class ShipmentController {
    @Autowired
    private final ShipmentService shipmentService;

    public ShipmentController(ShipmentService shipmentService) {
        this.shipmentService = shipmentService;
    }

    /** POST shipment */
    @PostMapping("{apiKey}")
    public ResponseEntity<Shipment> postShipment(@RequestBody Shipment shipment, @PathVariable String apiKey) {
        boolean success = shipmentService.save(shipment);

        if(success) {
            return ResponseEntity.status(HttpStatus.CREATED).body(shipment);
        } else {
           return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

    }

    /** GET shipment by shipment_id */
    @GetMapping("{apiKey}/shipmentId/{shipmentId}")
    public ResponseEntity<Shipment> getShipment(@PathVariable int shipmentId, @PathVariable String apiKey) {
        Shipment shipment = shipmentService.getById(shipmentId);

        if(User.CheckIfAdmin(apiKey)) {
            // have to do a spellcheck in the sql table!!!
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        if(shipment != null) {
            return ResponseEntity.status(HttpStatus.OK).body(shipment);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    /** GET all shipment */
    @GetMapping("{apiKey}/getAll")
    public ResponseEntity<List<Shipment>> getAllShipment(@PathVariable String apiKey) {
        List<Shipment> shipments = shipmentService.getAll();

        if(shipments != null) {
            return ResponseEntity.status(HttpStatus.OK).body(shipments);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

    }

    /** GET all shipment */
    @GetMapping("{apiKey}/getAllSorted")
    public ResponseEntity<List<Shipment>> getAllShipmentSorted(@PathVariable String apiKey) {
        List<Shipment> shipmentsBase = shipmentService.getAll();
        List<Shipment> shipments = GoogleAPIController.Calculate(shipmentsBase);

        if(shipments != null) {
            return ResponseEntity.status(HttpStatus.OK).body(shipments);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

    }

    /** DELETE shipment by shipment_id */
    @DeleteMapping("{apiKey}/shipmentId/{shipmentId}")
    public ResponseEntity<Shipment> deleteVehicle(@PathVariable int shipmentId, @PathVariable String apiKey) {
        boolean success = shipmentService.deleteById(shipmentId);

        if(success) {
            return ResponseEntity.status(HttpStatus.OK).build();
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    /** DELETE all shipments */
    @DeleteMapping("{apiKey}/deleteAll")
    public ResponseEntity<Shipment> deleteAllShipments(@PathVariable String apiKey) {
        boolean isDone = shipmentService.deleteAll();

        if(isDone) {
            return ResponseEntity.status(HttpStatus.OK).build();
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

    }
}
