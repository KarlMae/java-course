package ee.ttu.iti0202.coffee.drink;

import java.util.HashMap;

public class Coffee implements Drink {

    private static HashMap<String, Integer> ingredients = new HashMap<>();

    public Coffee() {
        ingredients.put("water", 500);
        ingredients.put("coffee beans", 150);
    }

    public HashMap<String, Integer> getIngredients() {
        return ingredients;
    }

    @Override
    public String toString() {
        return "Coffee";
    }
}
