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

        Coin c1 = new Coin(c);
        Coin c2 = new Coin(s);
        Coin c3 = new Coin(g);
        Coin c4 = new Coin(10, g);


        Purse purse = new Purse(c1, c2, c3);
        purse.addCoin(new Coin(g));

        Price price = Price.of(1, 0, 10);
        Price price2 = Price.of(110);
        Price price3 = Price.of(111);

        List<Coin> paidCoins = purse.pay(price);
        System.out.println(paidCoins); // [1 gold, 1 silver]
        System.out.println(purse.getCoins()); // [1 silver]

        Tavern tavern = new Tavern();

        tavern.addFood("pasta", Price.of(11));



    }
}
