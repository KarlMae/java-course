package ee.ttu.iti0202.coffee.coffeemaker;


import ee.ttu.iti0202.coffee.coffemakercontainer.BeanContainer;
import ee.ttu.iti0202.coffee.coffemakercontainer.CocoaContainer;
import ee.ttu.iti0202.coffee.coffemakercontainer.GarbageContainer;
import ee.ttu.iti0202.coffee.coffemakercontainer.MilkContainer;
import ee.ttu.iti0202.coffee.coffemakercontainer.WaterContainer;
import ee.ttu.iti0202.coffee.drink.Drink;
import ee.ttu.iti0202.coffee.exceptions.CannotMakeCoffeeException;

import java.util.HashMap;

public class AutomaticCoffeeMaker extends CoffeeMaker {

    private final int liquidContainerSize = 5000;

    public AutomaticCoffeeMaker() {
        this.garbageContainer = new GarbageContainer(5);
        beanContainer = new BeanContainer(1000);
        milkContainer = new MilkContainer(liquidContainerSize);
        waterContainer = new WaterContainer(liquidContainerSize);
        cocoaContainer = new CocoaContainer(1000);
    }


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


    @Override
    void emptyContainers(HashMap<String, Integer> ingredients) {
        if (ingredients.containsKey("water")) {
            waterContainer.useWater(ingredients.get("water"));
        }
    }


    @Override
    public Drink makeCoffee(Drink drink) {
        checkContainers(drink);
        emptyContainers(drink.getIngredients());
        garbageContainer.addGarbage();
        return drink;
    }

    @Override
    public String toString() {
        return "An automatic coffeemaker";
    }
}
