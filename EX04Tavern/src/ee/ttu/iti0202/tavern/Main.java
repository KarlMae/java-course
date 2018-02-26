package ee.ttu.iti0202.tavern;

import java.util.List;

public class Main {
    public static void main(String[] args) {

        Currency.add("copper");
        Currency.add("silver", 10);
        Currency.add("gold", 100);

        Currency c = Currency.get("copper");
        Currency s = Currency.get("silver");
        Currency g = Currency.get("gold");

        Coin c1 = new Coin(s);
        Coin c2 = new Coin(s);
        Coin c3 = new Coin(g);
        Coin c4 = new Coin(10, g);
        Purse purse = new Purse(c1, c2, c3);

        Price price = Price.of(1, 0, 10);
        Price price2 = Price.of(110);
        Price price3 = Price.of(111);
        List<Coin> paidCoins = purse.pay(price);
        Tavern tavern = new Tavern();
        tavern.addFood("pasta", Price.of(11));


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
