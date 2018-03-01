package ee.ttu.iti0202.tavern;


import java.util.List;

public class Main {
    public static void main(String[] args) {


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

        Purse purse2 = new Purse(new Coin(cur1),
                new Coin(cur2), new Coin(cur2), new Coin(cur2),
                new Coin(cur4), new Coin(cur4),
                new Coin(cur5)
        );

        Price price8 = new Price(8, cur1);
        System.out.println(purse2.pay(price8)); // [1 4 EUR, 1 4 EUR]

// the same example, using coin amount and one currency
        Currency.reset();
        Currency.add("EUR");
        Currency eur = Currency.get("EUR");
        Purse purse3 = new Purse(new Coin(eur),
                new Coin(2, eur), new Coin(2, eur), new Coin(2, eur), new Coin(2, eur),
                new Coin(4, eur), new Coin(4, eur),
                new Coin(5, eur), new Coin(5, eur)
        );
        price8 = new Price(8, eur);
        System.out.println(purse3.pay(price8)); // [4 EUR, 4 EUR]
        System.out.println(purse3.pay(price8)); // [5 EUR, 2 EUR, 1 EUR]
        System.out.println(purse3.pay(price8)); // [5 EUR, 2 EUR, 2 EUR]
        System.out.println(purse3.pay(price8)); // null <- no more coins left
        System.out.println(purse3.getCoins()); // [2 EUR] <- one coin remaining

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
