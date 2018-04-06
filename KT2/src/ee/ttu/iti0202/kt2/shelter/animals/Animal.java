package ee.ttu.iti0202.kt2.shelter.animals;

import ee.ttu.iti0202.kt2.shelter.location.PetOwner;

public abstract class Animal {

    public abstract PetOwner getLocation();
    public abstract void setLocation(PetOwner petOwner);
    public abstract int getMonthlyCost();
    public abstract String getDislikes();

}
