package ee.ttu.iti0202.shelter.animals;

public class Dog extends Animal {

    private String location;
    private int monthlycost;

    public Dog(String location, int cost) {
    }

    public String getLocation() {
        return location;
    }

    public int getMonthlyCost() {
        return monthlycost;
    }
}
