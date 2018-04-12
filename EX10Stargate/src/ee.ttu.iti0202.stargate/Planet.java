package ee.ttu.iti0202.stargate;

import java.util.List;

public class Planet {

    private String name;
    private long inhabitants;
    private boolean stargateAvailable;
    private boolean dhdhAvailable;
    private List<String> teamVisited;

    public Planet(String name, long inhabitants, boolean stargateAvailable, boolean dhdAvailable, List<String> teamsVisited) {
        this.name = name;
        this.inhabitants = inhabitants;
        this.stargateAvailable = stargateAvailable;
        this.dhdhAvailable = dhdAvailable;
        this.teamVisited = teamsVisited;
    }

    public String getName() {
        return name;
    }

    public long getInhabitants() {
        return inhabitants;
    }

    public boolean isStargateAvailable() {
        return stargateAvailable;
    }

    public boolean isDhdhAvailable() {
        return dhdhAvailable;
    }

    public List<String> getTeamVisited() {
        return teamVisited;
    }

    @Override
    public String toString() {
        return name;
    }
}
