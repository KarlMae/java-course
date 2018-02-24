package ee.ttu.iti0202.tavern;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Tavern {

    private static Map<String, Price> foods = new HashMap<>();


    public void addFood(String name, Price price) {
        foods.put(name, price);
    }

    public Price getPriceForFood(String name) {
        return foods.getOrDefault(name, null);
    }

    public boolean buy(String name, Purse purse) {
        if (!foods.containsKey(name)) return false;
        int money = 0;
        List<Coin> coins = purse.getCoins();
        for (Coin coin : coins) {
            money += coin.getCurrency().getRate() * coin.getAmount();
        }


        if (foods.containsKey(name)) {
            Price foodCost = foods.get(name);
            if (money > foodCost.getPriceInBaseValue()) {
                purse.pay(foodCost);
                foods.remove(name);
                return true;
            }
        }

        return false;
    }
}
