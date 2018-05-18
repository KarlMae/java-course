package ee.ttu.iti0202.justice.humans;

import ee.ttu.iti0202.justice.exceptions.PersonTooYoungException;
import ee.ttu.iti0202.justice.judgestrategies.JudgeStrategy;
import ee.ttu.iti0202.justice.judgestrategies.NaiveJudgeStrategy;

import java.util.Objects;

public class Citizen {

    public enum Profession { JUDGE, LAWYER, CITIZEN }

    String firstName;
    String lastName;
    int age;

    Citizen() {

    }

    Citizen(String firstName, String lastName, int age) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public int getAge() {
        return age;
    }

    public boolean isAdult() {
        return age >= 18;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Citizen citizen = (Citizen) o;
        return age == citizen.age &&
                Objects.equals(firstName, citizen.firstName) &&
                Objects.equals(lastName, citizen.lastName);
    }

    @Override
    public int hashCode() {

        return Objects.hash(firstName, lastName, age);
    }

    @Override
    public String toString() {
        return "[ " +  firstName + ' ' + lastName + ", age= " + age + " ]";
    }
}
