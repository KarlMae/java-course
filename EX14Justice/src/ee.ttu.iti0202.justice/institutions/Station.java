package ee.ttu.iti0202.justice.institutions;

import ee.ttu.iti0202.justice.archive.Archive;
import ee.ttu.iti0202.justice.exceptions.ArchiveDoesntExistException;
import ee.ttu.iti0202.justice.humans.Citizen;
import ee.ttu.iti0202.justice.humans.Judge;
import ee.ttu.iti0202.justice.humans.Lawyer;
import ee.ttu.iti0202.justice.lawsuits.Lawsuit;
import ee.ttu.iti0202.justice.town.Town;

import java.util.*;
import java.util.stream.Collectors;

public class Station {

    private List<Citizen> population = new ArrayList<>();
    private Random random = new Random();

    public void addCitizen(Citizen citizen) {
        this.population.add(citizen);
    }

    public void removeCitizen(Citizen citizen) {
        System.out.println("You can't escape the system!");
    }

    public List<Citizen> getPopulation() {
        return population;
    }

    public void accuseSomeone(Court court, Town town) {
        Citizen victim = pickRandomCitizen();
        Lawsuit lawsuit = new Lawsuit(victim,
                List.of(Lawsuit.Reason.values()).get(random.nextInt(Lawsuit.Reason.values().length)), town);
        court.addSuit(lawsuit);
    }

    private Citizen pickRandomCitizen() {
        List<Citizen> validPeople = population.stream()
                .filter(Citizen::isAdult)

                .filter(citizen -> {
                    try {
                        return Archive.of().getCitizensFree().contains(citizen);
                    } catch (ArchiveDoesntExistException e) {
                        System.out.println("No argument version of Archive.of cannot be called, when there is no archive");
                    }

                    return true;
                })

                .filter(citizen -> citizen.getClass() != Judge.class)
                .filter(citizen -> citizen.getClass() != Lawyer.class)
                .collect(Collectors.toList());

        return validPeople.get(random.nextInt(validPeople.size()));
    }
}
