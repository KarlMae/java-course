package ee.ttu.iti0202.tavern;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Comparator;

public class Tavern {

    private List<Coin> giveChangeOptimum = new ArrayList<>();
    private Boolean optimumFound = false;
    private int optimumOverPay = Integer.MAX_VALUE;
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
    public List<Coin> returnChange(List<Coin> paidCoins, int foodcost, Purse purse) {
        ArrayList<Coin> currenciesToReturn = new ArrayList<>();

        //Calculate the amount to pay back
        int amountPaid = 0;
        for (Coin coin : paidCoins) amountPaid += coin.getValue();
        int payBackAmount = amountPaid - foodcost;


        //Make a list of all currencies
        availableCurrencies.addAll(Currency.getCurrencies());

        recursiveCoinFinder(currenciesToReturn, payBackAmount);

        for (Coin coin : giveChangeOptimum) {
            purse.addCoin(coin);
        }

        return giveChangeOptimum;

    }


    private void setOptimumOverPay (ArrayList<Coin> coins) {
        int coinSum = 0;
        for (Coin coin : coins) {
            coinSum += coin.getValue();
        }
        optimumOverPay = coinSum;
    }

    /* Recursive coin finder */
    private void recursiveCoinFinder(ArrayList<Coin> usedCoins, int priceLeft) {
        // Base case
        if (priceLeft <= 0) {

            if (optimumFound && priceLeft == 0) {
                if (usedCoins.size() < giveChangeOptimum.size()) {
                    giveChangeOptimum = new ArrayList<>(usedCoins);
                    setOptimumOverPay(usedCoins);
                } else {
                    return;
                }

            } else if (priceLeft == 0) {
                giveChangeOptimum = new ArrayList<>(usedCoins);
                setOptimumOverPay(usedCoins);
                }
                optimumFound = true;
            } else if (priceLeft > optimumOverPay) {
                giveChangeOptimum = new ArrayList<>(usedCoins);
                setOptimumOverPay(usedCoins);
            } else if (usedCoins.size() < giveChangeOptimum.size()) {
                giveChangeOptimum = new ArrayList<>(usedCoins);
                setOptimumOverPay(usedCoins);
            }
            return;

        // Try every coin
        for (Currency currency : availableCurrencies) {
            usedCoins.add(new Coin(currency));
            recursiveCoinFinder(usedCoins, priceLeft - currency.getRate());
            usedCoins.remove(usedCoins.size() - 1);
        }
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

            if (money >= foodCost.getPriceInBaseValue()) {
                List<Coin> paidCoins = purse.pay(foodCost);
                List<Coin> coinsToReturn = returnChange(paidCoins, foodCost.getPriceInBaseValue(), purse);

                List<Price> prices = foods.get(name);
                if (prices.stream().min(Comparator.comparing(Price::getPriceInBaseValue)).isPresent()) {
                    prices.remove(prices.stream().min(Comparator.comparing(Price::getPriceInBaseValue)).get());                }

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
