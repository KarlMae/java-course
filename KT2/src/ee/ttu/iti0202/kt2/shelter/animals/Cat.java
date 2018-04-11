package ee.ttu.iti0202.kt2.shelter.animals;

import ee.ttu.iti0202.kt2.shelter.location.PetOwner;
import ee.ttu.iti0202.kt2.shelter.location.Shelter;

public class Cat extends Animal {

    private PetOwner location;
    private int monthlycost;

    public Cat(PetOwner location, int cost) {
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
        return "Dog";
    }

    @Override
    public String toString() {
        return "Cat";
    }
}
