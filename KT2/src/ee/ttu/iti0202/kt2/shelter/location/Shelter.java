package ee.ttu.iti0202.kt2.shelter.location;

import ee.ttu.iti0202.kt2.shelter.animals.Animal;

import java.util.ArrayList;
import java.util.List;

public class Shelter extends PetOwner{

    private List<Animal> animals = new ArrayList<>();
    private List<PetOwner> adopters = new ArrayList<>();

    public void addAnimal(Animal animal){
        animal.getLocation().removeAnimal(animal, this);
        animal.setLocation(this);
    }

    public void removeAnimal(Animal animal, PetOwner owner){
        if (!animals.contains(animal)) return;
        adopters.add(owner);
        animals.remove(animal);
    }

    public List<Animal> getAnimals() {
        return animals;
    }

    public List<PetOwner> getAdopters() {
        return adopters;
    }
}
