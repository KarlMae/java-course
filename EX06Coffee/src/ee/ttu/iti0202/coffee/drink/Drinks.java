package ee.ttu.iti0202.coffee.drink;

import java.util.HashMap;

public class Drinks {

    private static HashMap<String, HashMap<String, Integer>> drinks = new HashMap<>();

    private void Latte() {
        HashMap<String, Integer> ingredients = new HashMap<>();
        ingredients.put("milk", 400);
        ingredients.put("water", 100);
        ingredients.put("coffee beans", 100);
        drinks.put("latte", ingredients);
    }

    private void Coffee() {
        HashMap<String, Integer> ingredients = new HashMap<>();
        ingredients.put("water", 500);
        ingredients.put("coffee beans", 200);
        drinks.put("coffee", ingredients);
    }

    private void Cacao() {
        HashMap<String, Integer> ingredients = new HashMap<>();
        ingredients.put("milk", 500);
        ingredients.put("cacao beans", 100);
        drinks.put("cacao", ingredients);
    }

    private void Cappuccino() {
        HashMap<String, Integer> ingredients = new HashMap<>();
        ingredients.put("water", 250);
        ingredients.put("coffee beans", 250);
        drinks.put("cappuccino", ingredients);
    }

    private void Water() {
        HashMap<String, Integer> ingredients = new HashMap<>();
        ingredients.put("water", 500);
        drinks.put("water", ingredients);

    }

    public Drinks() {
        Cappuccino();
        Cacao();
        Coffee();
        Latte();
        Water();
    }

    public static HashMap<String, HashMap<String, Integer>> getDrinks() {
        return drinks;
    }
}
