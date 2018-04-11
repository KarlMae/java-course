package ee.ttu.iti0202.kt2.shelter.location;

import ee.ttu.iti0202.kt2.shelter.animals.Animal;

public abstract class PetOwner {

    public abstract void addAnimal(Animal animal);
    public abstract void removeAnimal(Animal animal, PetOwner owner);
}
