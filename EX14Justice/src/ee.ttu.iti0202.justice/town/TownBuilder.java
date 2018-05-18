package ee.ttu.iti0202.justice.town;

import ee.ttu.iti0202.justice.institutions.Court;
import ee.ttu.iti0202.justice.institutions.Prison;
import ee.ttu.iti0202.justice.institutions.Station;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TownBuilder {
    private List<Station> stations;
    private List<Prison> prisons;
    private List<Court> courts;
    private String name;

    public TownBuilder setStations(List<Station> stations) {
        this.stations = stations;
        return this;
    }

    public TownBuilder setStation(Station station) {
        this.stations = new ArrayList<>(Arrays.asList(station));
        return this;
    }

    public TownBuilder setPrisons(List<Prison> prisons) {
        this.prisons = prisons;
        return this;
    }

    public TownBuilder setPrison(Prison prison) {
        this.prisons = new ArrayList<>(Arrays.asList(prison));
        return this;
    }

    public TownBuilder setCourts(List<Court> courts) {
        this.courts = courts;
        return this;
    }

    public TownBuilder setCourt(Court court) {
        this.courts = new ArrayList<>(Arrays.asList(court));
        return this;
    }

    public TownBuilder setName(String name) {
        this.name = name;
        return this;
    }

    public Town createTown() {
        return new Town(stations, prisons, courts, name);
    }
}