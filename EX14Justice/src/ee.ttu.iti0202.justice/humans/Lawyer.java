package ee.ttu.iti0202.justice.humans;

public class Lawyer extends Citizen {

    private int lawsuitCount;
    private int lawsuitsWon;

    Lawyer(String firstName, String lastName, int age) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        lawsuitsWon = 0;
        lawsuitCount = 0;
    }

    public void addLawsuitCount() {
        lawsuitCount++;
    }

    public void addLawsuitsWon() {
        lawsuitsWon++;
    }

    public int getLawsuitCount() {
        return lawsuitCount;
    }

    public int getLawsuitsWon() {
        return lawsuitsWon;
    }
}
