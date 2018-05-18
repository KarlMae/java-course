package ee.ttu.iti0202.justice.lawsuits;

import ee.ttu.iti0202.justice.humans.Citizen;
import ee.ttu.iti0202.justice.humans.Judge;
import ee.ttu.iti0202.justice.humans.Lawyer;
import ee.ttu.iti0202.justice.institutions.Court;
import ee.ttu.iti0202.justice.judgements.Acquital;
import ee.ttu.iti0202.justice.judgements.Judgement;

public class Litigation {

    private Judge judge;
    private Lawyer lawyer;
    private Lawsuit lawsuit;
    private Judgement judgement;
    private Court court;
    private Citizen citizen;

    public Litigation(Court court, Lawsuit lawsuit, Judge judge, Lawyer lawyer) {
        this.lawsuit = lawsuit;
        this.judge = judge;
        this.lawyer = lawyer;
        this.court = court;
        this.citizen = lawsuit.getCitizen();
        this.judgement = trial();

        postProcess();
    }

    private Judgement trial() {
        return judge.makeJudgement(citizen, lawyer, lawsuit, this);
    }

    private void postProcess() {
        lawyer.addLawsuitCount();

        if (judgement.getClass() == Acquital.class) {
            lawyer.addLawsuitsWon();
            court.getArchive().addEndedTrial(this);
        } else {
            court.getArchive().removeFromInnocentList(lawsuit.getCitizen());
            court.getArchive().addEndedTrial(this);

            // Could add to random prison
            lawsuit.getTown().getPrisons().get(0).addPrisoner(citizen);
        }
    }

    public Judge getJudge() {
        return judge;
    }

    public Lawyer getLawyer() {
        return lawyer;
    }

    public Citizen getCitizen() {
        return citizen;
    }

    public Lawsuit getLawsuit() {
        return lawsuit;
    }
}
