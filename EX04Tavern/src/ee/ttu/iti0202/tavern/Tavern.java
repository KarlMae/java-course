package ee.ttu.iti0202.tavern;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Comparator;
import java.util.Collections;

public class Tavern {

    private List<Currency> giveChangeOptimum = new ArrayList<>();
    private List<Currency> availableCurrencies = new ArrayList<>();
    private static Map<String, List<Price>> foods = new HashMap<>();


    // Add new food
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

    // Return lowest price of given food
    public Price getPriceForFood(String name) {
        List<Price> prices = foods.getOrDefault(name, null);
        if (prices != null) {
            if (prices.stream().min(Comparator.comparing(Price::getPriceInBaseValue)).isPresent()) {
                return prices.stream().min(Comparator.comparing(Price::getPriceInBaseValue)).get();
            }
        }
        return null;
    }

    // Return change
    public List<Currency> returnChange(List<Coin> paidCoins, int foodcost, Purse purse) {
        ArrayList<Currency> currenciesToReturn = new ArrayList<>();

        //Calculate the amount to pay back
        int amountPaid = 0;
        for (Coin coin : paidCoins) amountPaid += coin.getValue();
        int payBackAmount = amountPaid - foodcost;


        //Make a list of all currencies
        availableCurrencies.addAll(Currency.getCurrencies());
        List<List<Currency>> previousCombinations = new ArrayList<>();

        recursiveCoinFinder(currenciesToReturn, payBackAmount);

        for (Currency currency : giveChangeOptimum) {
            purse.addCoin(new Coin(currency));
        }

        return giveChangeOptimum;

    }


    /* Recursive coin finder */
    private void recursiveCoinFinder(ArrayList<Currency> usedCurrencies, int priceLeft) {
        // Base case
        if (priceLeft <= 0) {
            if (priceLeft == 0 && usedCurrencies.size() < giveChangeOptimum.size()) {
                giveChangeOptimum = new ArrayList<>(usedCurrencies);
            }
            if (giveChangeOptimum.size() == 0) {
                giveChangeOptimum = new ArrayList<>(usedCurrencies);
            }
            return;
        }

        // Try every coin
        for (Currency currency : availableCurrencies) {
            usedCurrencies.add(currency);
            recursiveCoinFinder(usedCurrencies, priceLeft - currency.getRate());
            usedCurrencies.remove(usedCurrencies.size() - 1);
        }
    }


    public List<Currency> buyWithChange(String name, Purse purse) {
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
                List<Currency> coinsToReturn = returnChange(paidCoins, foodCost.getPriceInBaseValue(), purse);

                List<Price> prices = foods.get(name);
                if (prices.stream().max(Comparator.comparing(Price::getPriceInBaseValue)).isPresent()) {
                    prices.remove(prices.stream().max(Comparator.comparing(Price::getPriceInBaseValue)).get());
                    foods.put(name, prices);
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
