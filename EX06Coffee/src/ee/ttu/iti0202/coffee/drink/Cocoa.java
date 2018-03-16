package ee.ttu.iti0202.coffee.drink;

import java.util.HashMap;

public class Cocoa implements Drink {

    private final int milkAmount = 500;
    private final int cocoaAmount = 100;

    private static HashMap<String, Integer> ingredients = new HashMap<>();

    public Cocoa() {
        ingredients.put("milk", milkAmount);
        ingredients.put("cocoa beans", cocoaAmount);
    }

    public HashMap<String, Integer> getIngredients() {
        return ingredients;
    }

    @Override
    public String toString() {
        return "Cocoa";
    }
}
