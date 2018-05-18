package ee.ttu.iti0202.justice.humans;

import ee.ttu.iti0202.justice.exceptions.PersonTooYoungException;
import ee.ttu.iti0202.justice.judgestrategies.JudgeStrategy;
import ee.ttu.iti0202.justice.judgestrategies.NaiveJudgeStrategy;

import java.util.Objects;

public class CitizenBuilder {
    private String firstName;
    private String lastName;
    private int age;
    private Citizen.Profession profession;
    private JudgeStrategy strategy;

    public CitizenBuilder setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public CitizenBuilder setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public CitizenBuilder setAge(int age) {
        this.age = age;
        return this;
    }

    public CitizenBuilder setProfession(Citizen.Profession profession) {
        this.profession = profession;
        return this;
    }

    public CitizenBuilder setStrategy(JudgeStrategy strategy) {
        this.strategy = strategy;
        return this;
    }


    public Citizen createCitizen() throws PersonTooYoungException {

        if (profession == Citizen.Profession.LAWYER) {
            if (age <= 25) throw new PersonTooYoungException();
            return new Lawyer(firstName, lastName, age);
        } else if (profession == Citizen.Profession.JUDGE) {
            if (age <= 30) throw new PersonTooYoungException();
            return new Judge(firstName, lastName, age, Objects.requireNonNullElseGet(strategy, NaiveJudgeStrategy::new));
        } else {
            return new Citizen(firstName, lastName, age);
        }
    }
}