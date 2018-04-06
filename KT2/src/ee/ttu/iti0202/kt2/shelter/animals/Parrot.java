package ee.ttu.iti0202.kt2.shelter.animals;

import ee.ttu.iti0202.kt2.shelter.location.PetOwner;

public class Parrot extends Animal {

    private PetOwner location;
    private int monthlycost;

    public Parrot(PetOwner location, int cost) {
    }

    public PetOwner getLocation() {
        return location;
    }

    public void setLocation(PetOwner location) {
        this.location = location;
    }

    public int getMonthlyCost() {
        return monthlycost;
    }


    public String getDislikes() {
        return "";
    }

    @Override
    public String toString() {
        return "Parrot";
    }
}
