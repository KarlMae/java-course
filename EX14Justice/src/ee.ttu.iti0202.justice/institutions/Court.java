package ee.ttu.iti0202.justice.institutions;

import ee.ttu.iti0202.justice.archive.Archive;
import ee.ttu.iti0202.justice.humans.Judge;
import ee.ttu.iti0202.justice.humans.Lawyer;
import ee.ttu.iti0202.justice.lawsuits.Lawsuit;
import ee.ttu.iti0202.justice.lawsuits.Litigation;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Court {

    private List<Judge> judges = new ArrayList<>();
    private List<Lawyer> lawyers = new ArrayList<>();
    private List<Lawsuit> lawsuits = new ArrayList<>();

    private Archive archive;

    private Random random = new Random();

    public Court(Archive archive) {
        this.archive = archive;
    }

    void addSuit(Lawsuit suit) {
        lawsuits.add(suit);
    }

    public void addLawyer(Lawyer lawyer) {
        lawyers.add(lawyer);
    }

    public void addJudge(Judge judge) {
        judges.add(judge);
    }

    public void startLitigation() {
        archive.addEndedTrial(new Litigation(this,
                lawsuits.remove(0),
                judges.get(random.nextInt(judges.size() - 1)),
                lawyers.get(random.nextInt(lawyers.size() - 1))));
    }

    public List<Judge> getJudges() {
        return judges;
    }

    public List<Lawyer> getLawyers() {
        return lawyers;
    }

    public List<Lawsuit> getLawsuits() {
        return lawsuits;
    }

    public Archive getArchive() {
        return archive;
    }
}
