package ee.ttu.iti0202.coffee.coffeemaker;


import ee.ttu.iti0202.coffee.coffemakercontainer.BeanContainer;
import ee.ttu.iti0202.coffee.coffemakercontainer.CocoaContainer;
import ee.ttu.iti0202.coffee.coffemakercontainer.GarbageContainer;
import ee.ttu.iti0202.coffee.coffemakercontainer.MilkContainer;
import ee.ttu.iti0202.coffee.coffemakercontainer.WaterContainer;
import ee.ttu.iti0202.coffee.drink.Drink;
import ee.ttu.iti0202.coffee.exceptions.CannotMakeCoffeeException;

import java.util.HashMap;

public abstract class CoffeeMaker {

    BeanContainer beanContainer;
    MilkContainer milkContainer;
    WaterContainer waterContainer = new WaterContainer(5000);
    CocoaContainer cocoaContainer;
    GarbageContainer garbageContainer;


    public CoffeeMaker() {
        this.garbageContainer = new GarbageContainer(5);
    }

    public CoffeeMaker(int garbageLimit) {
        this.garbageContainer = new GarbageContainer(garbageLimit);

    }

    void checkContainers(Drink drink) {
        if (garbageContainer.isFull()) {
            throw new CannotMakeCoffeeException(this, garbageContainer, CannotMakeCoffeeException.Reason.GARBAGE_FULL);
        }
        if (drink.getIngredients().containsKey("water")
                && waterContainer.isEmpty() || drink.getIngredients().get("water") > waterContainer.waterLeft()) {
            throw new CannotMakeCoffeeException(this, waterContainer, CannotMakeCoffeeException.Reason.OUT_OF_WATER);
        }
        if (drink.getIngredients().containsKey("milk")
                && (milkContainer.isEmpty() || drink.getIngredients().get("milk") > milkContainer.getIngredientLeft(
        ))) {
            throw new CannotMakeCoffeeException(this, milkContainer, CannotMakeCoffeeException.Reason
                    .OUT_OF_INGREDIENT);
        }
        if (drink.getIngredients().containsKey("coffee beans")
                && beanContainer.isEmpty() || drink.getIngredients().get("coffee beans") > beanContainer
                .getIngredientLeft()) {
            throw new CannotMakeCoffeeException(this, beanContainer, CannotMakeCoffeeException.Reason
                    .OUT_OF_INGREDIENT);
        }
        if (drink.getIngredients().containsKey("cocoa beans")
                && cocoaContainer.isEmpty() || drink.getIngredients().get("cocoa beans") > cocoaContainer
                .getIngredientLeft()) {
            throw new CannotMakeCoffeeException(this, cocoaContainer, CannotMakeCoffeeException.Reason
                    .OUT_OF_INGREDIENT);
        }
    }

    void emptyContainers(HashMap<String, Integer> ingredients) {
        if (ingredients.containsKey("milk")) {
            milkContainer.useIngredient(ingredients.get("milk"));
        }
        if (ingredients.containsKey("water")) {
            waterContainer.useWater(ingredients.get("water"));
        }
        if (ingredients.containsKey("coffee beans")) {
            beanContainer.useIngredient(ingredients.get("coffee beans"));
        }
        if (ingredients.containsKey("cocoa beans")) {
            cocoaContainer.useIngredient(ingredients.get("cocoa beans"));
        }
    }

    public Drink makeCoffee(Drink drink) {
        checkContainers(drink);
        emptyContainers(drink.getIngredients());
        garbageContainer.addGarbage();
        return drink;
    }

    public CocoaContainer getCocoaContainer() {
        return cocoaContainer;
    }

    public GarbageContainer getGarbageContainer() {
        return garbageContainer;
    }

    public WaterContainer getWaterContainer() {
        return waterContainer;
    }

    public BeanContainer getBeanContainer() {

        return beanContainer;
    }

    public MilkContainer getMilkContainer() {

        return milkContainer;
    }
}
