package ee.ttu.iti0202.justice.judgestrategies;

import ee.ttu.iti0202.justice.humans.Citizen;
import ee.ttu.iti0202.justice.humans.Lawyer;
import ee.ttu.iti0202.justice.judgements.Acquital;
import ee.ttu.iti0202.justice.judgements.Conviction;
import ee.ttu.iti0202.justice.judgements.Judgement;
import ee.ttu.iti0202.justice.lawsuits.Lawsuit;
import ee.ttu.iti0202.justice.lawsuits.Litigation;

public class PervJudgeStrategy implements JudgeStrategy {

    public Judgement makeJudgement(Citizen citizen, Lawyer lawyer, Lawsuit lawsuit, Litigation litigation) {
            if (citizen.getAge() < 20) {
                return new Acquital();
            } else {
                return new Conviction(lawsuit.getMinimumFine(), lawsuit.getMinimumTimeToServe(), litigation);
            }
    }
}
