package ee.ttu.iti0202.justice.judgements;

import ee.ttu.iti0202.justice.archive.Archive;
import ee.ttu.iti0202.justice.exceptions.ArchiveDoesntExistException;
import ee.ttu.iti0202.justice.humans.Citizen;
import ee.ttu.iti0202.justice.lawsuits.Litigation;

public class Conviction extends Judgement {

    private int timeInDays;
    private int fine;
    private Citizen citizen;

    public Conviction(int fine, int timeInDays, Litigation litigation) {
        this.timeInDays = timeInDays;
        this.fine = fine;

        try {
            Archive.of().getCitizensFree().remove(litigation.getLawsuit().getCitizen());
        } catch (ArchiveDoesntExistException e) {
            System.out.println("No archive to remove citizen from!");
        }


    }

    public int getTimeInDays() {
        return timeInDays;
    }

    public int getFine() {
        return fine;
    }

    @Override
    public boolean suspectGuilty() {
        return true;
    }
}
