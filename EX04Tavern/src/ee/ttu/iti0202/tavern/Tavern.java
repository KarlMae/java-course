package ee.ttu.iti0202.tavern;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Comparator;
import java.util.Collections;

public class Tavern {

    private static Map<String, List<Price>> foods = new HashMap<>();


    public void addFood(String name, Price price) {
        if (foods.containsKey(name)) {
            List<Price> updatedFood = foods.get(name);
            updatedFood.add(price);
            foods.put(name, updatedFood);
        } else {
            List<Price> updatedFood = new ArrayList<>();
            updatedFood.add(price);
            foods.put(name, updatedFood);
        }
    }

    public Price getPriceForFood(String name) {
        List<Price> prices = foods.getOrDefault(name, null);
        if (prices != null) {
            if (prices.stream().min(Comparator.comparing(Price::getPriceInBaseValue)).isPresent()) {
                return prices.stream().min(Comparator.comparing(Price::getPriceInBaseValue)).get();
            }
        }
        return null;
    }

    public List<Coin> returnChange(List<Coin> paidCoins, int foodcost, Purse purse) {
        List<Coin> coinsToReturn = new ArrayList<>();
        List<Currency> availableCurrencies = Currency.getCurrencies();
        availableCurrencies.sort(Collections.reverseOrder());
        int amountPaid = 0;

        for (Coin coin : paidCoins) amountPaid += coin.getValue();
        int payBackAmount = amountPaid - foodcost;

        for (Currency currency : availableCurrencies) {
            int currencyValue = currency.getRate();
            if (currencyValue > payBackAmount) {
                continue;
            }
            int coinUseAmount = payBackAmount / currencyValue;

            for (int i = 0; i < coinUseAmount; i++) {
                payBackAmount -= currencyValue;
                purse.addCoin(new Coin(currency));
            }
        }
        return coinsToReturn;
    }

    public List<Coin> buyWithChange(String name, Purse purse) {
        if (!foods.containsKey(name)) return null;
        int money = 0;
        List<Coin> coins = purse.getCoins();
        for (Coin coin : coins) {
            money += coin.getCurrency().getRate() * coin.getAmount();
        }

        if (foods.containsKey(name)) {
            Price foodCost = getPriceForFood(name);

            if (foodCost == null) return null;

            if (money > foodCost.getPriceInBaseValue()) {
                List<Coin> paidCoins = purse.pay(foodCost);
                List<Coin> coinsToReturn = returnChange(paidCoins, foodCost.getPriceInBaseValue(), purse);

                List<Price> prices = foods.get(name);
                if (prices.stream().max(Comparator.comparing(Price::getPriceInBaseValue)).isPresent()) {
                    prices.remove(prices.stream().max(Comparator.comparing(Price::getPriceInBaseValue)).get());
                }
                return coinsToReturn;
            }
        }
        return null;
    }

    public boolean buy(String name, Purse purse) {
        if (!foods.containsKey(name)) return false;
        int money = 0;
        List<Coin> coins = purse.getCoins();
        for (Coin coin : coins) {
            money += coin.getCurrency().getRate() * coin.getAmount();
        }

        if (foods.containsKey(name)) {
            Price foodCost = getPriceForFood(name);

            if (foodCost == null) return false;

            if (money > foodCost.getPriceInBaseValue()) {
                purse.pay(foodCost);

                List<Price> prices = foods.get(name);
                if (prices.stream().max(Comparator.comparing(Price::getPriceInBaseValue)).isPresent()) {
                    prices.remove(prices.stream().max(Comparator.comparing(Price::getPriceInBaseValue)).get());
                }
                return true;
            }
        }
        return false;
    }
}
