package ee.ttu.iti0202.kt2.shelter.animals;

public class Parrot extends Animal {

    private String location;
    private int monthlycost;

    public Parrot(String location, int cost) {
        this.location = location;
        this.monthlycost = cost;
    }

    public String getLocation() {
        return location;
    }

    public int getMonthlyCost() {
        return monthlycost;
    }
}
