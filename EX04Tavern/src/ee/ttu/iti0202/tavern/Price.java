package ee.ttu.iti0202.tavern;


import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.List;

public class Price {

    private int priceInBaseValue;
    private Map<Currency, Integer> priceInCoins = new HashMap<>();


    public Price(int amount, Currency currency) {
        this.priceInBaseValue = amount * Currency.getRate(currency);
    }

    public Price add(int amount, Currency currency) {
        if (amount <= 0) return this;

        if (this.priceInCoins.containsKey(currency)) {
            this.priceInCoins.put(currency, this.priceInCoins.get(currency) + amount);
            this.priceInBaseValue += amount * Currency.getRate(currency);
        } else {
            this.priceInCoins.put(currency, amount);
            this.priceInBaseValue += amount * Currency.getRate(currency);
        }
        return this;
    }

    public Map<Currency, Integer> getPrice() {
        Map<Currency, Integer> optimalPayment = new HashMap<>();
        ArrayList<Integer> values = new ArrayList<>();
        int priceInBaseValue = this.priceInBaseValue;


        //Sort currencies by value
        for (Currency currency : Currency.getCurrencies()) {
            values.add(Currency.getRate(currency));
        }
        values.sort(Collections.reverseOrder());


        //Use sorted list and divide price in base value into the currencies
        for (Integer value : values) {
            for (Currency currency : Currency.getCurrencies()) {

                if (priceInBaseValue == 0) {
                    return optimalPayment;
                }

                if (Currency.getRate(currency) == value) {
                    int amountOfCoinsToAdd = priceInBaseValue / Currency.getRate(currency);
                    priceInBaseValue -= amountOfCoinsToAdd * Currency.getRate(currency);
                    if (amountOfCoinsToAdd > 0) {
                        optimalPayment.put(currency, amountOfCoinsToAdd);
                    }
                }
            }
        }

        return optimalPayment;
    }

    public static Price of(int gold, int silver, int copper) {
        Price newPrice = new Price(gold, Currency.get("gold"));
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
        List<Currency> currencyList = new ArrayList<>(currencies.keySet());
        Collections.sort(currencyList);

        if (currencies.size() == 0) return "";

        for (Currency c : currencyList) {
            if (currencies.get(c) == 0) continue;
            output.append(currencies.get(c));
            output.append(" ");
            output.append(c.getName());
            output.append(", ");
        }

        return output.toString().substring(0, output.length() - 2);
    }

    @Override
    public boolean equals(Object obj) {
        return obj == this || (((Price) obj).priceInBaseValue) == this.priceInBaseValue;
    }

    @Override
    public int hashCode() {

        return Objects.hash(priceInBaseValue);
    }
}
