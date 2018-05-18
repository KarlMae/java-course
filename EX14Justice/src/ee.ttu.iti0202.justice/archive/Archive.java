package ee.ttu.iti0202.justice.archive;

import ee.ttu.iti0202.justice.exceptions.ArchiveDoesntExistException;
import ee.ttu.iti0202.justice.humans.Citizen;
import ee.ttu.iti0202.justice.lawsuits.Litigation;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Archive {

    private Map<Litigation, Citizen> endedTrials = new HashMap<>();
    private List<Citizen> citizensFree;
    private static Archive archive;

    public static Archive of() throws ArchiveDoesntExistException {
        if (archive != null) {
            return archive;
        } else {
            throw new ArchiveDoesntExistException();
        }

    }

    public static Archive of(List<Citizen> citizens) {
        if (archive != null) return archive;

        Archive newArchive = new Archive(citizens);
        archive = newArchive;
        return newArchive;
    }

    private Archive(List<Citizen> citizens) {
        citizensFree = citizens;
    }

    public Map<Litigation, Citizen> getEndedTrials() {
        return endedTrials;
    }

    public void addEndedTrial(Litigation litigation) {
        this.endedTrials.put(litigation, litigation.getCitizen());
    }

    public List<Citizen> getCitizensFree() {
        return citizensFree;
    }

    public void removeFromInnocentList(Citizen citizen) {
        citizensFree.remove(citizen);
    }
}
