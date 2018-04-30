package ee.ttu.iti0202.drink;

import java.util.ArrayList;
import java.util.List;

public abstract class Drink {

    private static List<Drink> availableDrinks = new ArrayList<>();

    public enum Drinks { VODKA, RUM, ABSINTHE }

    public static Drink makeDrink(int price, Drinks drinkToMake) {
        Drink returnDrink = null;

        switch (drinkToMake) {
            case VODKA:
                for (Drink drink : availableDrinks) {
                    if (drink instanceof Vodka) {
                        return drink;
                    }
                }
                returnDrink = new Vodka(price);
                break;

            case RUM:
                for (Drink drink : availableDrinks) {
                    if (drink instanceof Rum) {
                        return drink;
                    }
                }
                returnDrink = new Rum(price);
                break;

            case ABSINTHE:
                for (Drink drink : availableDrinks) {
                    if (drink instanceof Absinthe) {
                        return drink;
                    }
                }
                returnDrink = new Absinthe(price);
                break;
        }

        availableDrinks.add(returnDrink);
        return returnDrink;
    }
}
