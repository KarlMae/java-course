package ee.ttu.iti0202.coffee.coffemakercontainer;

import ee.ttu.iti0202.coffee.drink.Drink;

import java.util.HashMap;
import java.util.List;

public class Capsule {

    private HashMap<String, Integer> ingredients;
    private Drink drink;

    public Capsule(Drink drink) {
        ingredients = drink.getIngredients();
        this.drink = drink;
    }

    public void addIngredient(String ingredient, Integer amount) {
        if (amount <= 0) return;

        if (ingredients.containsKey(ingredient)) {
            ingredients.put(ingredient, ingredients.get(ingredient) + amount);
        } else {
            ingredients.put(ingredient, amount);
        }
    }

    public void useCapsule() {
        ingredients.clear();
    }

    public Drink getDrink() {
        return drink;
    }

    public HashMap<String, Integer> getIngredients() {
        return ingredients;
    }
}
