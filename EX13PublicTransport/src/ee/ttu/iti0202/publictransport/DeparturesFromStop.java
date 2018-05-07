package ee.ttu.iti0202.publictransport;

import java.util.List;
import java.util.Set;

public class DeparturesFromStop extends Departure{

    private Stop stop;
    private List<Departure> departures;

    public Stop getStop() {
        return stop;
    }

    public List<Departure> getDepartures() {
        return departures;
    }

    @Override
    public String toString() {
        return "DeparturesFromStop{" +
                "stop=" + stop +
                ", departures=" + departures +
                '}';
    }
}
