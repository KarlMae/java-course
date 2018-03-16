package ee.ttu.iti0202.coffee.drink;

import java.util.HashMap;

public class Coffee implements Drink {

    private final int waterAmount = 500;
    private final int coffeeAmount = 150;

    private static HashMap<String, Integer> ingredients = new HashMap<>();

    public Coffee() {
        ingredients.put("water", waterAmount);
        ingredients.put("coffee beans", coffeeAmount);
    }

    public HashMap<String, Integer> getIngredients() {
        return ingredients;
    }

    @Override
    public String toString() {
        return "Coffee";
    }
}
