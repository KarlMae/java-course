package ee.ttu.iti0202.publictransport;

import java.util.List;

public class NearbyStop extends Stop {

    private List<String> transportations;
    private int distance;

    public List<String> getTransportations() {
        return transportations;
    }

    public int getDistance() {
        return distance;
    }
}
