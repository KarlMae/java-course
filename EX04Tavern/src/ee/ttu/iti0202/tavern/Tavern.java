package ee.ttu.iti0202.tavern;

import java.util.*;

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

    public void returnChange(List<Coin> paidCoins, Purse purse) {
        int returnamount = 0;
        for (Coin coin : paidCoins) {
            returnamount += coin.getValue();
        }
        paidCoins.sort(Collections.reverseOrder());

        for (Coin coin : paidCoins) {
            int coinValue = coin.getValue();
            if (coinValue > returnamount) {
                continue;
            }
            int coinUseAmount = returnamount / coinValue;

            for (int i = 0; i < coinUseAmount; i++) {
                returnamount -= coinValue;
                purse.addCoin(coin);
            }
        }
    }

    public List<Coin> buyWithChange(String name, Purse purse) {
        List<Coin> x = new ArrayList<>();

        if (!foods.containsKey(name)) return x;
        int money = 0;
        List<Coin> coins = purse.getCoins();
        for (Coin coin : coins) {
            money += coin.getCurrency().getRate() * coin.getAmount();
        }

        if (foods.containsKey(name)) {
            Price foodCost = getPriceForFood(name);

            if (foodCost == null) return x;

            if (money > foodCost.getPriceInBaseValue()) {
                List<Coin> paidCoins = purse.pay(foodCost);
                returnChange(paidCoins, purse);

                List<Price> prices = foods.get(name);
                if (prices.stream().max(Comparator.comparing(Price::getPriceInBaseValue)).isPresent()) {
                    prices.remove(prices.stream().max(Comparator.comparing(Price::getPriceInBaseValue)).get());
                }
                return x;
            }
        }
        return x;
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
