package ee.ttu.iti0202.justice.humans;

import ee.ttu.iti0202.justice.judgements.Judgement;
import ee.ttu.iti0202.justice.judgestrategies.JudgeStrategy;
import ee.ttu.iti0202.justice.lawsuits.Lawsuit;
import ee.ttu.iti0202.justice.lawsuits.Litigation;

public class Judge extends Citizen {

    private JudgeStrategy strategy;

    Judge(String firstName, String lastName, int age, JudgeStrategy strategy) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.strategy = strategy;
    }

    public Judgement makeJudgement(Citizen citizen, Lawyer lawyer, Lawsuit lawsuit, Litigation litigation) {
        return strategy.makeJudgement(citizen, lawyer, lawsuit, litigation);
    }
}
