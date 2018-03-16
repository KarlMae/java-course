package test;

import ee.ttu.iti0202.coffee.coffeemaker.AutomaticCoffeeMaker;
import ee.ttu.iti0202.coffee.coffeemaker.CapsuleCoffeeMaker;
import ee.ttu.iti0202.coffee.coffemakercontainer.Capsule;
import ee.ttu.iti0202.coffee.drink.Cappuccino;
import ee.ttu.iti0202.coffee.drink.Cocoa;
import ee.ttu.iti0202.coffee.drink.Coffee;
import ee.ttu.iti0202.coffee.drink.Latte;
import ee.ttu.iti0202.coffee.drink.Water;
import ee.ttu.iti0202.coffee.kitchen.Kitchen;
import static org.hamcrest.CoreMatchers.instanceOf;
import org.junit.Test;

import static org.junit.Assert.*;

public class KitchenTest {



    @Test
    public void makeCoffee() {
        Kitchen kitchen = new Kitchen();
        AutomaticCoffeeMaker automaticCoffeeMaker = new AutomaticCoffeeMaker();
        Cappuccino cappuccino = new Cappuccino();

        // Doesn't use ingredients, auto-refill water
        for (int i = 0; i < 100; i++) {
            kitchen.makeCoffee(automaticCoffeeMaker, cappuccino);
        }
    }

    @Test
    public void makeCapsuleCoffee() {
        Kitchen kitchen = new Kitchen();
        CapsuleCoffeeMaker capsuleCoffeeMaker = new CapsuleCoffeeMaker();
        Latte latte = new Latte();
        kitchen.addCoffeeMachine(capsuleCoffeeMaker);
        assertThat(kitchen.makeCapsuleCoffee(capsuleCoffeeMaker, latte), instanceOf(Latte.class));
    }

    @Test
    public void makeCapsuleCoffeeFixErrors() {
        Kitchen kitchen = new Kitchen();
        CapsuleCoffeeMaker capsuleCoffeeMaker = new CapsuleCoffeeMaker();
        Coffee coffee = new Coffee();
        kitchen.addCoffeeMachine(capsuleCoffeeMaker);
        for(int i = 0; i < 50; i++) {
            kitchen.makeCapsuleCoffee(capsuleCoffeeMaker, coffee);
        }
    }

    @Test
    public void makeCapsuleCoffeeEmptiesCapsule() {
        Kitchen kitchen = new Kitchen();
        CapsuleCoffeeMaker capsuleCoffeeMaker = new CapsuleCoffeeMaker();
        kitchen.addCoffeeMachine(capsuleCoffeeMaker);

        Cocoa cocoa = new Cocoa();
        Capsule capsule = new Capsule(cocoa);
        assertEquals(2, capsule.getIngredients().size());

        kitchen.makeCapsuleCoffee(capsuleCoffeeMaker, capsule);

        assertThat(capsuleCoffeeMaker.getCapsuleHolder(), instanceOf(Capsule.class));
        assertEquals(0, capsule.getIngredients().size());

        assertThat(capsuleCoffeeMaker.makeCoffee(), instanceOf(Water.class));
    }

    @Test
    public void addCoffeeMachine() {
        Kitchen kitchen = new Kitchen();
        kitchen.addCoffeeMachine(new CapsuleCoffeeMaker());
        kitchen.addCoffeeMachine(new AutomaticCoffeeMaker());
        assertEquals(2, kitchen.getCoffeeMakers().size());
    }
}
