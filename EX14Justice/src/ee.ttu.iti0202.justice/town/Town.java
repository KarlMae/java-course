package ee.ttu.iti0202.justice.town;

import ee.ttu.iti0202.justice.institutions.Court;
import ee.ttu.iti0202.justice.institutions.Prison;
import ee.ttu.iti0202.justice.institutions.Station;

import java.util.List;

public class Town {

    private List<Station> stations;
    private List<Prison> prisons;
    private List<Court> courts;
    private String name;

    Town(List<Station> stations, List<Prison> prisons, List<Court> courts, String name) {
        this.stations = stations;
        this.prisons = prisons;
        this.courts = courts;
        this.name = name;
    }

    public Town(String name) {
        this.name = name;
    }

    public List<Station> getStations() {
        return stations;
    }

    public List<Prison> getPrisons() {
        return prisons;
    }

    public List<Court> getCourts() {
        return courts;
    }

    @Override
    public String toString() {
        return name;
    }
}
