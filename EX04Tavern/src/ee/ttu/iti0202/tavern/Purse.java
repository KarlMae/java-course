package ee.ttu.iti0202.tavern;

import java.util.*;

public class Purse {

    private static ArrayList<Coin> coins = new ArrayList<>();
    private static Map<Integer, ArrayList<Coin>> priceOptimums = new HashMap<>();

    public Purse(Coin... coins) {
        for (Coin c : coins) {
            this.addCoin(c);
        }
    }

    public void addCoin(Coin coin) {
        coins.add(coin);
    }

    public List<Coin> getCoins() {
        Collections.sort(coins);
        return coins;
    }

    private Coin tryEveryCoin(int priceToPay) {
        Coin bestCoin = null;
        Collections.sort(coins);

        for (Coin c : coins) {
            if (bestCoin == null) {
                bestCoin = c;
                continue;
            }

            // If new coin suits better
            int newCoinValue = c.getAmount() * c.getCurrency().getRate();
            if (newCoinValue >= priceToPay) bestCoin = c;
        }

        if (bestCoin != null) {
            return bestCoin;
        } else {
            return null;
        }
    }

    public List<Coin> pay(Price price) {
        int priceToPay = price.getPriceInBaseValue();
        List<Coin> coinsToPay;

        coinsToPay = recursiveCoinFinder(coins, priceToPay, 0);

        if (coinsToPay != null) {
            for (Coin coin : coinsToPay) {
                coins.remove(coin);
            }
        }

        return coinsToPay;
    }

    private ArrayList<Coin> recursiveCoinFinder(ArrayList<Coin> availableCoins, int priceLeft, int index) {

        if (priceOptimums.containsKey(priceLeft)) {
            return priceOptimums.get(priceLeft);
        }

        ArrayList<Coin> optimalCoins = new ArrayList<>();
        int optimalSize = Integer.MAX_VALUE;

        // Try every coin
        for (int i = 0; i < availableCoins.size(); i++) {
            // Select a coin from the arraylist
            Coin coin = availableCoins.get(i);

            // Base case
            if (priceLeft - coin.getValue() <= 0) {
                ArrayList<Coin> returnCoin = new ArrayList<>();
                returnCoin.add(coin);
                return returnCoin;
            }

            // This tree doesn't yield a result
            if (availableCoins.size() == 0 && optimalSize == Integer.MAX_VALUE) {
                return null;
            }

            ArrayList<Coin> passingCoins = new ArrayList<>(availableCoins);
            passingCoins.remove(coin);
            ArrayList<Coin> nextLevelCoins = recursiveCoinFinder(passingCoins, priceLeft - coin.getValue(), index + 1);

            if (nextLevelCoins != null) {
                if (nextLevelCoins.size() + 1 < optimalSize) {
                    optimalCoins.addAll(nextLevelCoins);
                    optimalCoins.add(coin);
                }
            }
        }

        priceOptimums.put(priceLeft, optimalCoins);
        return optimalCoins;
    }
}
