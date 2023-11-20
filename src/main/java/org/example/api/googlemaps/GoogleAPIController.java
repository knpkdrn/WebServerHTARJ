package org.example.api.googlemaps;
import com.google.maps.DirectionsApi;
import com.google.maps.DirectionsApiRequest;
import com.google.maps.GeoApiContext;
import com.google.maps.model.DirectionsResult;
import com.google.maps.model.TravelMode;
public class GoogleAPIController {

    private static final String API_KEY = "AIzaSyCEhRZDgXI2xYen-eEvRzOj36Kp8r9HO4o";
    public static String ORIGIN = "";
    public static String WAYPOINTS = "";
    public static void Calculate(){
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

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}