package test;

import ee.ttu.iti0202.coffee.coffeemaker.AutomaticCoffeeMaker;
import ee.ttu.iti0202.coffee.drink.Latte;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.*;

public class AutomaticCoffeeMakerTest {


    @Test
    public void checkContainers() {
        AutomaticCoffeeMaker automaticCoffeeMaker = new AutomaticCoffeeMaker();
        Latte latte = new Latte();
        assertEquals(5000, automaticCoffeeMaker.getWaterContainer().waterLeft());
        for (int i = 0; i < 5; i++) {
            automaticCoffeeMaker.makeCoffee(latte);
        }
        assertFalse(automaticCoffeeMaker.getWaterContainer().isEmpty());
        assertTrue(automaticCoffeeMaker.getGarbageContainer().isFull());

        automaticCoffeeMaker.getGarbageContainer().emptyGarbage();

        assertThat(automaticCoffeeMaker.makeCoffee(latte), instanceOf(Latte.class));
    }

    @Test
    public void makeCoffee() {
        AutomaticCoffeeMaker automaticCoffeeMaker = new AutomaticCoffeeMaker();
        Latte latte = new Latte();
        assertThat(automaticCoffeeMaker.makeCoffee(latte), instanceOf(Latte.class));
    }
}