package ee.ttu.iti0202.tavern;

import java.util.List;

public class Main {
    public static void main(String[] args) {

        // let's register currencies
        Currency.add("copper");
        Currency.add("silver", 10);
        Currency.add("gold", 100);

        // to shorten the code

        Currency c = Currency.get("copper");
        Currency s = Currency.get("silver");
        Currency g = Currency.get("gold");

        Purse purse1 = new Purse(
                new Coin(s), new Coin(s), new Coin(s),
                new Coin(g), new Coin(g), new Coin(g)
        );

        System.out.println(purse1.pay(Price.of(30))); // [1 silver, 1 silver, 1 silver]   should not be 1 gold
        System.out.println(purse1.pay(Price.of(200))); // [1 gold, 1 gold]
        System.out.println(purse1.pay(Price.of(100))); // [1 gold]   should not be [1 gold, 1 gold]
        System.out.println(purse1.pay(Price.of(1))); // null   purse is empty

// another try, a bit harder cases
        purse1 = new Purse(new Coin(c), new Coin(c),
                new Coin(s), new Coin(s), new Coin(s),
                new Coin(g), new Coin(g), new Coin(g)
        );

        System.out.println(purse1.pay(Price.of(3))); // [1 silver]     should not be [1 copper, 1 copper, 1 silver]
        System.out.println(purse1.getCoins()); // [1 copper, 1 copper, 1 silver, 1 silver, 1 gold, 1 gold, 1 gold]
        System.out.println(purse1.pay(Price.of(30))); // [1 gold]     should not be [1 silver, 1 silver, 1 gold]
        System.out.println(purse1.getCoins()); // [1 copper, 1 copper, 1 silver, 1 silver, 1 gold, 1 gold]
        System.out.println(purse1.pay(Price.of(23))); // [1 gold]     should not be [1 copper, 1 copper, 1 silver, 1 silver, 1 gold]
        System.out.println(purse1.getCoins()); // [1 copper, 1 copper, 1 silver, 1 silver, 1 gold]
        System.out.println(purse1.pay(Price.of(123))); // null
        System.out.println(purse1.getCoins()); // [1 copper, 1 copper, 1 silver, 1 silver, 1 gold]




    }
}
