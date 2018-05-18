package ee.ttu.iti0202.justice.lawsuits;

import ee.ttu.iti0202.justice.humans.Citizen;
import ee.ttu.iti0202.justice.institutions.Station;
import ee.ttu.iti0202.justice.town.Town;

import java.time.LocalDateTime;

public class Lawsuit {

    public enum Reason { MURDER, THEFT, ROBBERY, VIOLENCE, INDECENCY, PSEUDO }

    private Citizen citizen;
    private Reason reason;
    private LocalDateTime localDateTime;
    private Town town;
    private int minimumTimeToServe;
    private int fine;

    public Lawsuit(Citizen citizen, Reason reason, Town town) {
        this.citizen = citizen;
        this.reason = reason;
        this.localDateTime = LocalDateTime.now();
        this.town = town;
        setMinimumPunishments(reason);
    }

    private void setMinimumPunishments(Reason reason) {
        switch (reason) {
            case MURDER:
                minimumTimeToServe = 900;
                fine = 100000;
                break;

            case ROBBERY:
                minimumTimeToServe = 365;
                fine = 10000;
                break;

            case THEFT:
                minimumTimeToServe = 300;
                fine = 1000;
                break;

            case VIOLENCE:
                minimumTimeToServe = 100;
                fine = 1000;
                break;

            case INDECENCY:
                minimumTimeToServe = 10;
                fine = 100;
                break;

            case PSEUDO:
                minimumTimeToServe = 0;
                fine = 0;
                break;

            default:
        }
    }

    public Town getTown() {
        return town;
    }

    public Citizen getCitizen() {
        return citizen;
    }

    public Reason getReason() {
        return reason;
    }

    public int getMinimumTimeToServe() {
        return minimumTimeToServe;
    }

    public int getMinimumFine() {
        return fine;
    }

    public LocalDateTime getLocalDateTime() {
        return localDateTime;
    }
}
