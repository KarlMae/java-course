package ee.ttu.iti0202.tavern;


import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class Price {

    private Map<Currency, Integer> priceInCoins = new HashMap<>();
    private int priceInBaseValue;
    private int amount;
    private Currency currency;

    public Price(int amount, Currency currency) {
        priceInCoins.put(currency, amount);
        this.amount = amount;
        this.currency = currency;
        this.priceInBaseValue = amount * currency.getRate();
    }

    public Price add(int amount, Currency currency) {
        if (this.priceInCoins.containsKey(currency)) {
            this.priceInCoins.put(currency, this.priceInCoins.get(currency) + amount);
        } else {
            this.priceInCoins.put(currency, amount);
        }
        return this;
    }

    public Map<Currency, Integer> getPrice() {
        Map<Currency, Integer> optimalPayment = new HashMap<>();
        ArrayList<Integer> values = new ArrayList<>();


        //Sort currencies by value
        for (Currency currency : this.priceInCoins.keySet()) {
            values.add(Currency.getRate(currency));
            this.priceInBaseValue += values.get(values.size());
        }
        Collections.sort(values);


        //Use sorted list and divide price in base value into the currencies
        for (Integer value : values) {
            for (Currency currency : priceInCoins.keySet()) {
                if (Currency.getRate(currency) == value) {
                    int amountOfCoinsToAdd = priceInBaseValue / Currency.getRate(currency);
                    priceInBaseValue -= amountOfCoinsToAdd * Currency.getRate(currency);
                    optimalPayment.put(currency, amountOfCoinsToAdd);
                }
            }
        }

        return optimalPayment;
    }

    public static Price of(int gold, int silver, int copper) {
        Currency g = Currency.get("gold");
        Price newPrice = new Price(gold, g);
        newPrice.add(silver, Currency.get("silver"));
        newPrice.add(copper, Currency.get("copper"));

        return newPrice;
    }


    public static Price of(int copper) {
        return new Price(copper, Currency.get("copper"));
    }


    public int getPriceInBaseValue() {
        return this.priceInBaseValue;
    }

    @Override
    public String toString() {
        StringBuilder output = new StringBuilder();
        Map<Currency, Integer> currencies = this.getPrice();
        if (currencies == null) return "";
        for (Currency c : currencies.keySet())
            output.append(currencies.get(c));
        output.append(getClass().getName());
        return output.toString();
    }

    @Override
    public boolean equals(Object obj) {
        return (((Price) obj).priceInBaseValue == this.priceInBaseValue);
    }
}
