package org.example.api.googlemaps;
import com.google.maps.DirectionsApi;
import com.google.maps.DirectionsApiRequest;
import com.google.maps.GeoApiContext;
import com.google.maps.model.DirectionsResult;
import com.google.maps.model.TravelMode;
import org.example.tables.models.Shipment;
import org.example.tables.services.ShipmentService;

import java.util.ArrayList;
import java.util.List;

public class GoogleAPIController {

    private static final String API_KEY = "AIzaSyCEhRZDgXI2xYen-eEvRzOj36Kp8r9HO4o";
    public static String ORIGIN = "";
    public static String WAYPOINTS = "";
    public static void Calculate(){
        ShipmentService shipmentService = new ShipmentService();
        List<Shipment> shipments = shipmentService.getAll();
        List<Shipment> orderedShipments = new ArrayList<>();

        ORIGIN = shipments.get(0).getOrigin();
        WAYPOINTS += shipments.get(0).getDestination();
        for (int i=1; i<shipments.size();++i){
            WAYPOINTS += "|" + shipments.get(i).getDestination();
        }

        GeoApiContext context = new GeoApiContext.Builder()
                .apiKey(API_KEY)
                .build();

        DirectionsApiRequest request = DirectionsApi.getDirections(context,
                ORIGIN,
                ORIGIN
        ).waypoints(WAYPOINTS).mode(TravelMode.DRIVING).optimizeWaypoints(true);

        try {
            DirectionsResult result = request.await();
            int[] waypointOrder = result.routes[0].waypointOrder;

            for (int i = 0; i<waypointOrder.length; ++i)
            orderedShipments.add(shipments.get(i));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
