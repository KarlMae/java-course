package ee.ttu.iti0202.coffee.drink;

import java.util.HashMap;

public class Cappuccino implements Drink {

    private static HashMap<String, Integer> ingredients = new HashMap<>();

    private final int waterAmount = 250;
    private final int coffeeAmount = 200;

    public Cappuccino() {
        ingredients.put("water", waterAmount);
        ingredients.put("coffee beans", coffeeAmount);
    }

    public HashMap<String, Integer> getIngredients() {
        return ingredients;
    }

    @Override
    public String toString() {
        return "Cappuccino";
    }
}
