package ee.ttu.iti0202.coffee.drink;

import java.util.HashMap;

public class Water implements Drink {

    private static HashMap<String, Integer> ingredients = new HashMap<>();

    public Water() {
        ingredients.put("water", 500);
    }

    public HashMap<String, Integer> getIngredients() {
        return ingredients;
    }

    @Override
    public String toString() {
        return "Water";
    }
}
