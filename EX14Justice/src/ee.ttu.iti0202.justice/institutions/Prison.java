package ee.ttu.iti0202.justice.institutions;

import ee.ttu.iti0202.justice.humans.Citizen;

import java.util.ArrayList;
import java.util.List;

public class Prison {

    private List<Citizen> prisoners = new ArrayList<>();

    public void addPrisoner(Citizen citizen) {
        prisoners.add(citizen);
    }

    public List<Citizen> getPrisoners() {
        return prisoners;
    }
}
