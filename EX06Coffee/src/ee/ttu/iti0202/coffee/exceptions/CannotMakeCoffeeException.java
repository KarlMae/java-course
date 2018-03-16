package ee.ttu.iti0202.coffee.exceptions;

import ee.ttu.iti0202.coffee.coffeemaker.CoffeeMaker;
import ee.ttu.iti0202.coffee.coffemakercontainer.Container;
import ee.ttu.iti0202.coffee.coffemakercontainer.GarbageContainer;
import ee.ttu.iti0202.coffee.coffemakercontainer.WaterContainer;

/*Exception for when trying to make coffee, but there is something wrong.
 *Constructors second parameter indicates, where the problem lies.*/

public class CannotMakeCoffeeException extends RuntimeException {

    // After adding a new container, add new exception type here
    public enum Reason {
        GARBAGE_FULL, OUT_OF_WATER, OUT_OF_INGREDIENT
    }

    private CoffeeMaker coffeeMaker;
    private Reason reason;
    private Container container;
    private WaterContainer waterContainer;
    private GarbageContainer garbageContainer;

    public CannotMakeCoffeeException(CoffeeMaker coffeeMaker, Container container, Reason reason) {
        super(coffeeMaker + " cannot make coffee, because " + reason);
        this.coffeeMaker = coffeeMaker;
        this.reason = reason;
        this.container = container;
    }

    public CannotMakeCoffeeException(CoffeeMaker coffeeMaker, GarbageContainer garbageContainer, Reason reason) {
        super(coffeeMaker + " cannot make coffee, because " + reason);
        this.coffeeMaker = coffeeMaker;
        this.reason = reason;
        this.garbageContainer = garbageContainer;
    }

    public CannotMakeCoffeeException(CoffeeMaker coffeeMaker, WaterContainer waterContainer, Reason reason) {
        super(coffeeMaker + " cannot make coffee, because " + reason);
        this.coffeeMaker = coffeeMaker;
        this.reason = reason;
        this.waterContainer = waterContainer;
    }

    public CoffeeMaker getCoffeeMaker() {
        return coffeeMaker;
    }

    public Reason getReason() {
        return reason;
    }

    public Container getContainer() {
        return container;
    }

    public WaterContainer getWaterContainer() {
        return waterContainer;
    }

    public GarbageContainer getGarbageContainer() {
        return garbageContainer;
    }
}
