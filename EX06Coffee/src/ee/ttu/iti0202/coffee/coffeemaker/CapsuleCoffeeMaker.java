package ee.ttu.iti0202.coffee.coffeemaker;

import ee.ttu.iti0202.coffee.coffemakercontainer.Capsule;
import ee.ttu.iti0202.coffee.coffemakercontainer.GarbageContainer;
import ee.ttu.iti0202.coffee.drink.Drink;
import ee.ttu.iti0202.coffee.drink.Water;
import ee.ttu.iti0202.coffee.exceptions.CannotMakeCoffeeException;
import ee.ttu.iti0202.coffee.exceptions.CapsuleAlreadyInMachineException;
import ee.ttu.iti0202.coffee.exceptions.NoCapsuleInMachineException;

public class CapsuleCoffeeMaker extends CoffeeMaker {

    private Capsule capsuleHolder;

    /*Capsule holder is the socket in which you put the capsule. Null means empty.*/
    public CapsuleCoffeeMaker() {
        capsuleHolder = null;
        this.garbageContainer = new GarbageContainer(10);
    }

    /*Takes in drink and checks, if capsule coffee maker has enough water and garbage room.
     * If something is wrong, throws Exception.*/
    @Override
    void checkContainers(Drink drink) {
        if (garbageContainer.isFull()) {
            throw new CannotMakeCoffeeException(this, garbageContainer, CannotMakeCoffeeException.Reason.GARBAGE_FULL);
        }
        if (drink.getIngredients().containsKey("water")) {
            if (waterContainer.isEmpty() || drink.getIngredients().get("water") > waterContainer.waterLeft()) {
                throw new CannotMakeCoffeeException(this, waterContainer, CannotMakeCoffeeException.Reason
                        .OUT_OF_WATER);
            }
        }
    }

    /* Make coffee by giving in drink.
     * Currently always has enough capsules.*/
    @Override
    public Drink makeCoffee(Drink drink) {
        if (capsuleHolder != null) takeOutCapsule();
        insertCapsule(new Capsule(drink));
        return makeCoffee();
    }

    /* Make coffee using the capsule already in the capsule holder.
     * If capsule holder is empty or the capsule is empty, makes water.*/
    public Drink makeCoffee() {
        Drink drinkToMake;
        if (capsuleHolder == null || capsuleHolder.getIngredients().size() <= 0) {
            drinkToMake = new Water();
        } else {
            drinkToMake = capsuleHolder.getDrink();
        }

        checkContainers(capsuleHolder.getDrink());
        if (drinkToMake.getIngredients().containsKey("water")) {
            waterContainer.useWater(drinkToMake.getIngredients().get("water"));
        }
        capsuleHolder.useCapsule();
        garbageContainer.addGarbage();
        return drinkToMake;
    }

    public void changeCapsule(Capsule capsule) {
        takeOutCapsule();
        insertCapsule(capsule);
    }

    public void insertCapsule(Capsule capsule) {
        if (capsuleHolder != null) throw new CapsuleAlreadyInMachineException();

        capsuleHolder = capsule;
    }

    public void takeOutCapsule() {
        if (capsuleHolder == null) throw new NoCapsuleInMachineException();

        capsuleHolder = null;
    }

    public Capsule getCapsuleHolder() {
        return capsuleHolder;
    }

    @Override
    public String toString() {
        return "A coffeemaker, that uses capsules";
    }
}
