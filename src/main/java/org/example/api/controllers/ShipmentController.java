package org.example.api.controllers;

import org.example.api.controllers.googlemaps.GoogleAPIController;
import org.example.tables.models.RequestHistory;
import org.example.tables.models.Shipment;
import org.example.tables.models.User;
import org.example.tables.services.RequestHistoryService;
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
    private RequestHistory requestHistory = new RequestHistory();

    @Autowired
    private RequestHistoryService rhs;
    public ShipmentController(ShipmentService shipmentService) {
        this.shipmentService = shipmentService;
    }

    /** POST shipment */
    @PostMapping("{apiKey}")
    public ResponseEntity<Shipment> postShipment(@RequestBody Shipment shipment, @PathVariable String apiKey) {
        boolean success = shipmentService.save(shipment);

        requestHistory.setRequestKey(apiKey);
        requestHistory.setRequestTime();
        requestHistory.setRequestType("POST");

        if(success) {
            requestHistory.setResult("CREATED");
            rhs.save(requestHistory);
            return ResponseEntity.status(HttpStatus.CREATED).body(shipment);
        } else {
            requestHistory.setResult("BAD REQUEST");
            rhs.save(requestHistory);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

    }

    /** GET shipment by shipment_id */
    @GetMapping("{apiKey}/shipmentId/{shipmentId}")
    public ResponseEntity<Shipment> getShipment(@PathVariable int shipmentId, @PathVariable String apiKey) {
        Shipment shipment = shipmentService.getById(shipmentId);

        requestHistory.setRequestKey(apiKey);
        requestHistory.setRequestTime();
        requestHistory.setRequestType("GET");

        if(User.CheckIfAdmin(apiKey)) {

            // have to do a spellcheck in the sql table!!!

            requestHistory.setResult("UNATHORIZED");
            rhs.save(requestHistory);
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        if(shipment != null) {
            requestHistory.setResult("OK");
            rhs.save(requestHistory);

            return ResponseEntity.status(HttpStatus.OK).body(shipment);
        } else {
            requestHistory.setResult("NOT FOUND");
            rhs.save(requestHistory);

            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    /** GET all shipment */
    @GetMapping("{apiKey}/getAll")
    public ResponseEntity<List<Shipment>> getAllShipment(@PathVariable String apiKey) {
        List<Shipment> shipments = shipmentService.getAll();

        requestHistory.setRequestKey(apiKey);
        requestHistory.setRequestTime();
        requestHistory.setRequestType("GET");

        if(shipments != null) {
            requestHistory.setResult("OK");
            rhs.save(requestHistory);

            return ResponseEntity.status(HttpStatus.OK).body(shipments);
        } else {
            requestHistory.setResult("NOT FOUND");
            rhs.save(requestHistory);

            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

    }

    /** GET all shipment */
    @GetMapping("{apiKey}/getAllSorted")
    public ResponseEntity<List<Shipment>> getAllShipmentSorted(@PathVariable String apiKey) {
        List<Shipment> shipmentsBase = shipmentService.getAll();
        List<Shipment> shipments = GoogleAPIController.Calculate(shipmentsBase);

        requestHistory.setRequestKey(apiKey);
        requestHistory.setRequestTime();
        requestHistory.setRequestType("GET");

        if(shipments != null) {
            requestHistory.setResult("OK");
            rhs.save(requestHistory);

            return ResponseEntity.status(HttpStatus.OK).body(shipments);
        } else {
            requestHistory.setResult("NOT FOUND");
            rhs.save(requestHistory);

            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

    }

    /** DELETE shipment by shipment_id */
    @DeleteMapping("{apiKey}/shipmentId/{shipmentId}")
    public ResponseEntity<Shipment> deleteVehicle(@PathVariable int shipmentId, @PathVariable String apiKey) {
        boolean success = shipmentService.deleteById(shipmentId);

        requestHistory.setRequestKey(apiKey);
        requestHistory.setRequestTime();
        requestHistory.setRequestType("DELETE");

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

    /** DELETE all shipments */
    @DeleteMapping("{apiKey}/deleteAll")
    public ResponseEntity<Shipment> deleteAllShipments(@PathVariable String apiKey) {
        boolean isDone = shipmentService.deleteAll();

        requestHistory.setRequestKey(apiKey);
        requestHistory.setRequestTime();
        requestHistory.setRequestType("DELETE");

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
