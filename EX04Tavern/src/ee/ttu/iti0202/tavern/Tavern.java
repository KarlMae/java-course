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
        if (prices != null){
            if (prices.stream().min(Comparator.comparing(Price::getPriceInBaseValue)).isPresent()) {
                return prices.stream().min(Comparator.comparing(Price::getPriceInBaseValue)).get();
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
                if(prices.stream().max(Comparator.comparing(Price::getPriceInBaseValue)).isPresent()){
                    prices.remove(prices.stream().max(Comparator.comparing(Price::getPriceInBaseValue)).get());
                }
                return true;
            }
        }

        return false;
    }
}
