package ee.ttu.iti0202.justice.judgestrategies;

import ee.ttu.iti0202.justice.humans.Citizen;
import ee.ttu.iti0202.justice.humans.Lawyer;
import ee.ttu.iti0202.justice.judgements.Judgement;
import ee.ttu.iti0202.justice.lawsuits.Lawsuit;
import ee.ttu.iti0202.justice.lawsuits.Litigation;

public interface JudgeStrategy {
    Judgement makeJudgement(Citizen citizen, Lawyer lawyer, Lawsuit lawsuit, Litigation litigation);
}
