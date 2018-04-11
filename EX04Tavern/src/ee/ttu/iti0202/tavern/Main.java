package ee.ttu.iti0202.tavern;

import java.util.List;

public class Main {
    public static void main(String[] args) {

// let's register currencies
        Currency.add("copper");
        Currency.add("silver", 10);
        Currency.add("gold", 100);

// use "variables" to shorten currency usage
        Currency c = Currency.get("copper");
        Currency s = Currency.get("silver");
        Currency g = Currency.get("gold");

        Coin c1 = new Coin(s);
        System.out.println(c1.getAmount());   // 1
        System.out.println(c1.getCurrency()); // silver

        Coin c2 = new Coin(s);
        Coin c3 = new Coin(g);
// this won't be used in purse in main part
// but this should work
        Coin c4 = new Coin(10, g);

        System.out.println(c4.getAmount());    // 10
        System.out.println(c4.getCurrency());  // gold

        Purse purse = new Purse(c1, c2, c3);

        Price price = Price.of(1, 0, 10);
        Price price2 = Price.of(110);

        System.out.println(price.equals(price2)); // true

        Price price3 = Price.of(111);
        System.out.println(price3); // 1 gold, 1 silver, 1 copper
        System.out.println(price.equals(price3)); // false

        List<Coin> paidCoins = purse.pay(price);
        System.out.println(paidCoins); // [1 gold, 1 silver]
        System.out.println(purse.getCoins()); // [1 silver]

        Tavern tavern = new Tavern();
        tavern.addFood("pasta", Price.of(11));

        System.out.println(tavern.getPriceForFood("pasta")); // 1 silver, 1 copper

        System.out.println(tavern.buy("pasta", purse)); // false, not enough money
        purse.addCoin(new Coin(g));
        System.out.println(tavern.buy("pasta", purse)); // true
        System.out.println(purse.getCoins()); // [1 silver]

        System.out.println(tavern.getPriceForFood("pasta")); // null, no more this food

        tavern.addFood("pasta", Price.of(20));
// let's add 2 silver coins
        purse.addCoin(new Coin(s));
        purse.addCoin(new Coin(s));
        System.out.println(purse.getCoins()); // [1 silver, 1 silver, 1 silver]
        tavern.buy("pasta", purse);
        System.out.println(purse.getCoins()); // [1 silver]

    }
}
