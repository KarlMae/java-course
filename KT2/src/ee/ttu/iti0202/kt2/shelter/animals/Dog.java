package ee.ttu.iti0202.kt2.shelter.animals;

import ee.ttu.iti0202.kt2.shelter.location.PetOwner;

import java.util.ArrayList;
import java.util.List;

public class Dog extends Animal {

    private PetOwner location;
    private int monthlycost;

    public Dog(PetOwner location, int cost) {
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
        return "Cat";
    }


    @Override
    public String toString() {
        return "Dog";
    }
}
