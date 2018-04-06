package ee.ttu.iti0202.kt2.shelter.location;

import ee.ttu.iti0202.kt2.shelter.animals.Animal;
import ee.ttu.iti0202.kt2.shelter.exceptions.CannotAdoptException;

import java.util.ArrayList;
import java.util.List;

public class Human extends PetOwner {

    private List<Animal> animals = new ArrayList<>();
    private int budget;
    private int animalCosts;
    private List<Animal> allergies = new ArrayList<>();

    public Human(int budget, List<Animal> allergies) {
        this.budget = budget;
        this.allergies = allergies;
    }

    // TODO: Exception
    public boolean canAdopt(Animal animal) {
        if (animals.get(0).getClass() == animal.getClass()) return false;
        if (animalCosts + animal.getMonthlyCost() <= budget) return false;
        if (allergies.contains(animal)) return false;
        if (animals.get(0).toString().equals(animal.getDislikes())) return false;
        return true;
    }

    public void addAnimal(Animal animal){
        if (!canAdopt(animal)) return;
        animalCosts += animal.getMonthlyCost();
        animal.getLocation().removeAnimal(animal, this);
        animal.setLocation(this);
    }

    public void removeAnimal(Animal animal, PetOwner owner){
        if (!animals.contains(animal)) return;
        animals.remove(animal);
    }

    public List<Animal> getAllergies() {
        return allergies;
    }

    public void addAllergies(Animal animal) {
        if (allergies.contains(animal)) return;
        allergies.add(animal);
    }

    public void removeAllergies(Animal animal) {
        if (!allergies.contains(animal)) return;
        allergies.remove(animal);
    }
}
