package ee.ttu.iti0202.pub;

import ee.ttu.iti0202.drink.Drink;
import ee.ttu.iti0202.food.Pizza;
import ee.ttu.iti0202.food.PizzaBuilder;

import java.util.ArrayList;
import java.util.List;

public class Pub {

    private static List<Pizza> pizzas = new ArrayList<>();
    private static List<Drink> drinks = new ArrayList<>();

    public Pub() {

    }

    private static void makePizza(int diameter) {
        pizzas.add(new PizzaBuilder().setDiameter(diameter).setName("Grass").setSlices(2).addComponent(Pizza
                .PizzaComponent.ANANAS).createPizza());
    }

    private static void makeDrink(int price) {
        drinks.add(Drink.makeDrink(price, Drink.Drinks.VODKA));
    }


    public static void main(String[] args) {
        makeDrink(20);
        makeDrink(20);
        makePizza(20);
        System.out.println(pizzas);
        System.out.println(drinks);
    }
}
