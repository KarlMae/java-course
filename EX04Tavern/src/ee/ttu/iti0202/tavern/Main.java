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

        Purse purse1 = new Purse(new Coin(c),
                new Coin(s),
                new Coin(g)
        );

        System.out.println(purse1.pay(Price.of(102))); // [1 copper, 1 copper]   should not be 1 silver

    }
}
