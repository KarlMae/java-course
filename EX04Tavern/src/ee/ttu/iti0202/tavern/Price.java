package ee.ttu.iti0202.tavern;

import java.util.*;

public class Price {

    private static Map<Price, String> prices = new HashMap<>();
    private Map<Currency, Integer> priceInCoins = new HashMap<>();
    private int priceInBaseValue;

    public Price(Currency currency, int amount) {
        this.priceInCoins = new HashMap<>();
        this.priceInCoins.put(currency, amount);
    }

    public Price add(Currency currency, int amount) {
        if(priceInCoins.containsKey(currency)){
            priceInCoins.put(currency, priceInCoins.get(currency) + amount);
        } else {
            priceInCoins.put(currency, amount);
        }
        return this;
    }

    public Map<Currency, Integer> getPrice() {
        Map<Currency, Integer> optimalPayment = new HashMap<>();
        ArrayList<Integer> values = new ArrayList<>();


        //Sort currencies by value
        for (Currency currency : priceInCoins.keySet()) {
            values.add(Currency.getRate(currency));
            priceInBaseValue += values.get(values.size());
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
        Price newPrice = new Price(Currency.get("gold"), gold);
        newPrice.add(Currency.get("silver"), silver);
        newPrice.add(Currency.get("copper"), copper);

        return newPrice;
    }


    public static Price of(int copper) {
        return new Price(Currency.get("copper"), copper);
    }


    public int getPriceInBaseValue(){
        return this.priceInBaseValue;
    }

    @Override
    public String toString(){
        StringBuilder output = new StringBuilder();
        Map<Currency, Integer> currencies = this.getPrice();
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
