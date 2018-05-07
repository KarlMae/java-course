package ee.ttu.iti0202.publictransport;

import java.util.Set;

public class Stop {

    private String id;
    private String name;
    private String direction;
    private Location location;
    private DeparturesFromStop departures;

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDirection() {
        return direction;
    }

    public Location getLocation() {
        return location;
    }

    public DeparturesFromStop getDepartures() {
        return departures;
    }
}
