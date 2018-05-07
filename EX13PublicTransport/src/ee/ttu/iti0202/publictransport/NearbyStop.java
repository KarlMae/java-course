package ee.ttu.iti0202.publictransport;

import java.util.Set;

public class NearbyStop extends Stop{

    private Set<String> transportations;
    private int distance;

    public Set<String> getTransportations() {
        return transportations;
    }

    public int getDistance() {
        return distance;
    }
}
