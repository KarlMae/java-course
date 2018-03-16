package ee.ttu.iti0202.coffee.drink;

import java.util.HashMap;

public class Latte implements Drink {
    private static HashMap<String, Integer> ingredients = new HashMap<>();

    public Latte() {
        ingredients.put("milk", 250);
        ingredients.put("water", 250);
        ingredients.put("beans", 100);
    }

    public HashMap<String, Integer> getIngredients() {
        return ingredients;
    }

    @Override
    public String toString() {
        return "Latte";
    }
}
