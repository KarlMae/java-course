package ee.ttu.iti0202.coffee.drink;

import java.util.HashMap;

public class Cocoa implements Drink{

    private static HashMap<String, Integer> ingredients = new HashMap<>();

    public Cocoa() {
        ingredients.put("milk", 500);
        ingredients.put("cocoa beans", 100);
    }

    public HashMap<String, Integer> getIngredients() {
        return ingredients;
    }

    @Override
    public String toString() {
        return "Cocoa";
    }
}
