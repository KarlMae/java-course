package ee.ttu.iti0202.coffee.drink;

import java.util.HashMap;
import java.util.Objects;

public class Latte implements Drink {
    private static HashMap<String, Integer> ingredients = new HashMap<>();

    private final int coffeeAmount = 100;
    private final int waterAmount = 250;
    private final int milkAmount = 250;

    public Latte() {
        ingredients.put("milk", milkAmount);
        ingredients.put("water", waterAmount);
        ingredients.put("coffee beans", coffeeAmount);
    }

    public HashMap<String, Integer> getIngredients() {
        return ingredients;
    }

    @Override
    public String toString() {
        return "Latte";
    }
}
