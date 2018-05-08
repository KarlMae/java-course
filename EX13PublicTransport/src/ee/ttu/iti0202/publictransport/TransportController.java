package ee.ttu.iti0202.publictransport;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.net.URL;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;


public class TransportController {

    private Gson parser = new Gson();

    private String readUrl(String urlString) throws Exception {

        URL url = new URL(urlString);

        BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));

        String inputLine;
        StringBuilder buffer = new StringBuilder();

        while ((inputLine = in.readLine()) != null) {
            buffer.append(inputLine);
        }

        in.close();
        return buffer.toString();
    }

    private Set<NearbyStop> getNearbyStops(String latitude, String longitude) {
        String json;
        Set<NearbyStop> nearbyStops = new HashSet<>();

        try {
            json = readUrl("https://public-transport-api.herokuapp.com/stops/" + latitude + "/" + longitude);
            Type nearbyStopSetType = new TypeToken<HashSet<NearbyStop>>() {
            }.getType();
            nearbyStops = parser.fromJson(json, nearbyStopSetType);

        } catch (Exception e) {
            System.out.println("Failed reading API!");
        }

        return nearbyStops;
    }

    private Optional<DeparturesFromStop> getNextDepartures(String stopId) {
        String json;
        DeparturesFromStop stop = null;

        try {
            json = readUrl("https://public-transport-api.herokuapp.com/departures/tallinn/" + stopId);
            stop = parser.fromJson(json, DeparturesFromStop.class);
        } catch (Exception e) {
            System.out.println("Failed reading API!");
        }

        return Optional.of(stop);
    }

    public DeparturesFromStop getDeparturesFromStop(String stopId) {
        return getNextDepartures(stopId).get();
    }

    public Set<NearbyStop> getNearbyStops(Location location) {
        return getNearbyStops(String.valueOf(location.getLatitude()), String.valueOf(location.getLongitude()));
    }

    public Optional<NearbyStop> getNearestStop(Location location) {
        return getNearbyStops(location).stream().min(Comparator.comparing(NearbyStop::getDistance));
    }

    public Optional<Departure> getNextDepartureFromStop(String stopId) {
        return getDeparturesFromStop(stopId).getDepartures().stream().min(Comparator.comparing(Departure::getTime));
    }


    public static void main(String[] args) {

    }
}
