package ee.ttu.iti0202.tavern;


import java.util.List;

public class Main {
    public static void main(String[] args) {

// let's register currencies
        Currency.add("copper");
        Currency.add("silver", 10);
        Currency.add("gold", 100);



// test different currencies

        Currency.reset();
        Currency.add("1 EUR"); // base
        Currency.add("2 EUR", 2);
        Currency.add("4 EUR", 4);
        Currency.add("5 EUR", 5);

        Currency cur1 = Currency.get("1 EUR");
        Currency cur2 = Currency.get("2 EUR");
        Currency cur4 = Currency.get("4 EUR");
        Currency cur5 = Currency.get("5 EUR");

//
// test buyWithChange
        Currency.reset();
        Currency.add("1-EUR"); // base
        Currency.add("2-EUR", 2);
        Currency.add("4-EUR", 4);
        Currency.add("5-EUR", 5);
        Currency.add("13-EUR", 13);
        Purse purseForChange = new Purse(new Coin(Currency.get("13-EUR")), new Coin(Currency.get("13-EUR")));
        Tavern tavern1ForChange = new Tavern();
        tavern1ForChange.addFood("Ice Cream", new Price(6, Currency.getBaseCurrency()));
        tavern1ForChange.addFood("Ice Cream", new Price(10, Currency.getBaseCurrency()));
        tavern1ForChange.addFood("Ice Cream", new Price(6, Currency.getBaseCurrency()));
        tavern1ForChange.addFood("Ice Cream", new Price(5, Currency.getBaseCurrency()));
        System.out.println(tavern1ForChange.getPriceForFood("Ice Cream"));  // 1 5-EUR
        System.out.println(tavern1ForChange.buyWithChange("Ice Cream", purseForChange)); // [1 4-EUR, 1 4-EUR]
        System.out.println(purseForChange.getCoins()); // [1 13-EUR, 1 4-EUR, 1 4-EUR]
        System.out.println(tavern1ForChange.getPriceForFood("Ice Cream"));  // 1 5-EUR, 1 1-EUR
        System.out.println(tavern1ForChange.buyWithChange("Ice Cream", purseForChange)); // [1 2-EUR]   <- used 4 + 4 for paying
        System.out.println("Reaining coins: " + purseForChange.getCoins()); // [1 13-EUR, 1 2-EUR]
        System.out.println(tavern1ForChange.getPriceForFood("Ice Cream"));  // 1 5-EUR, 1 1-EUR
        System.out.println(tavern1ForChange.buyWithChange("Ice Cream", purseForChange)); // [1 5-EUR, 1 2-EUR]
        System.out.println("Remaining coins: " + purseForChange.getCoins()); // [1 5-EUR, 1 2-EUR, 1 2-EUR]
        System.out.println(tavern1ForChange.buyWithChange("Ice Cream", purseForChange)); // null <- no money for ice cream 10EUR


    }
}
