package ee.ttu.iti0202.tavern;

import java.util.List;

public class Main {
    public static void main(String[] args) {


// let's register currencies
        Currency.add("copper");
        Currency.add("silver", 10);
        Currency.add("gold", 100);
        Tavern t = new Tavern();
        System.out.println(t.getPriceForFood("Pizza")); // null
        t.addFood("Pizza", Price.of(13));
        System.out.println(t.getPriceForFood("Pizza")); // 1 silver, 3 copper
        Purse purseForTavern = new Purse(new Coin(Currency.get("gold")));
        System.out.println(t.buy("Pizza", purseForTavern)); // true
        System.out.println(t.getPriceForFood("Pizza")); // null       <- oh noes, no more pizza

        t.addFood("Beer", Price.of(3)); // cheap beer
        purseForTavern = new Purse(new Coin(Currency.get("copper")));
        System.out.println(t.buy("Beer", purseForTavern)); // false   <- nooo, not enough money
        System.out.println(t.getPriceForFood("Beer")); // 3 copper    <- beer still exists

// ok, let's fix the last problem
        purseForTavern.addCoin(new Coin(Currency.get("gold")));
        System.out.println(t.buy("Beer", purseForTavern)); // true    <- much success
        System.out.println(t.getPriceForFood("Beer")); // null

    }
}
