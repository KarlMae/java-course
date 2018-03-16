package ee.ttu.iti0202.coffee.drink;

import java.util.HashMap;

public class Cappuccino implements Drink{

    private static HashMap<String, Integer> ingredients = new HashMap<>();

    public Cappuccino() {
        ingredients.put("water", 250);
        ingredients.put("beans", 200);
    }

    public HashMap<String, Integer> getIngredients() {
        return ingredients;
    }

    @Override
    public String toString() {
        return "Cappuccino";
    }
}
