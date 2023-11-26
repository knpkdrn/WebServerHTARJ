package org.example.api.controllers;

import org.example.api.controllers.googlemaps.GoogleAPIController;
import org.example.tables.models.Shipment;
import org.example.tables.models.User;
import org.example.tables.services.ShipmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.SerializationUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/shipments")
public class ShipmentController {
    @Autowired
    private final ShipmentService shipmentService;

    public ShipmentController(ShipmentService shipmentService) {
        this.shipmentService = shipmentService;
    }

    /** POST shipment */
    @PostMapping
    public ResponseEntity<Shipment> postShipment(@RequestBody Shipment shipment) {
        boolean success = shipmentService.save(shipment);

       if(success) {
            return ResponseEntity.status(HttpStatus.CREATED).body(shipment);
       } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
       }

    }

    /** GET shipment by shipment_id */
    @GetMapping("/shipmentId/{shipmentId}")
    public ResponseEntity<Shipment> getShipment(@PathVariable int shipmentId) {
        Shipment shipment = shipmentService.getById(shipmentId);

        if(shipment != null) {
            return ResponseEntity.status(HttpStatus.OK).body(shipment);
        } else {
           return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    /** GET all shipment */
    @GetMapping("/getAll")
    public ResponseEntity<List<Shipment>> getAllShipment() {
        List<Shipment> shipments = shipmentService.getAll();

        if(shipments != null) {
            return ResponseEntity.status(HttpStatus.OK).body(shipments);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

    }

    /** GET all shipment */
    @GetMapping("/getAllSorted")
    public ResponseEntity<List<Shipment>> getAllShipmentSorted() {
        List<Shipment> shipmentsBase = shipmentService.getByShipmentStatus("scheduled");
        if (shipmentsBase == null || shipmentsBase.isEmpty()) return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        shipmentService.updateShipmentStatusByOldStatus("scheduled", "enroute");
        List<Shipment> shipments = GoogleAPIController.Calculate(shipmentsBase);

        if(shipments != null) {
            return ResponseEntity.status(HttpStatus.OK).body(shipments);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

    }

    /** DELETE shipment by shipment_id */
    @DeleteMapping("/shipmentId/{shipmentId}")
    public ResponseEntity<Shipment> deleteVehicle(@PathVariable int shipmentId) {
        boolean success = shipmentService.deleteById(shipmentId);

        if(success) {
            return ResponseEntity.status(HttpStatus.OK).build();
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    /** DELETE all shipments */
    @DeleteMapping("/deleteAll")
    public ResponseEntity<Shipment> deleteAllShipments() {
        boolean isDone = shipmentService.deleteAll();

        if(isDone) {
            return ResponseEntity.status(HttpStatus.OK).build();
        } else {
           return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

    }
}
