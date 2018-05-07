package ee.ttu.iti0202.publictransport;

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

    @Override
    public String toString() {
        return "Stop name: " + name + ", location=" + location;
    }
}
