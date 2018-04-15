package ee.ttu.iti0202.stargate.planet;

import java.util.List;

public class PlanetBuilder {
    private String name;
    private long inhabitants;
    private boolean stargateAvailable;
    private boolean dhdAvailable;
    private List<String> teamsVisited;

    public PlanetBuilder name(String name) {
        this.name = name;
        return this;
    }

    public PlanetBuilder inhabitants(long inhabitants) {
        this.inhabitants = inhabitants;
        return this;
    }

    public PlanetBuilder stargateAvailable(boolean stargateAvailable) {
        this.stargateAvailable = stargateAvailable;
        return this;
    }

    public PlanetBuilder dhdAvailable(boolean dhdAvailable) {
        this.dhdAvailable = dhdAvailable;
        return this;
    }

    public PlanetBuilder teamsVisited(List<String> teamsVisited) {
        this.teamsVisited = teamsVisited;
        return this;
    }

    public Planet createPlanet() {
        return new Planet(name, inhabitants, stargateAvailable, dhdAvailable, teamsVisited);
    }
}
