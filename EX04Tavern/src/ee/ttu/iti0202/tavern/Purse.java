package ee.ttu.iti0202.tavern;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Purse {

    private static ArrayList<Coin> coins = new ArrayList<>();

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

        coinsToPay = recursiveCoinFinder(coins, priceToPay);

        if (coinsToPay != null) {
            for (Coin coin : coinsToPay) {
                coins.remove(coin);
            }
        }

        return coinsToPay;
    }

    private ArrayList<Coin> recursiveCoinFinder(ArrayList<Coin> availableCoins, int priceLeft) {


        ArrayList<Coin> tempCoins = new ArrayList<>();
        ArrayList<Coin> optimalCoins = new ArrayList<>();
        int optimalSize = Integer.MAX_VALUE;

        // Try every coin
        for (int i = 0; i < availableCoins.size(); i++) {

            // New loop, reset values
            int price = priceLeft;
            tempCoins.clear();

            // Select a coin from the arraylist
            Coin coin = availableCoins.get(i);
            tempCoins.add(coin);
            price = price - coin.getValue();

            // Base case
            if (price == 0) {
                ArrayList<Coin> returnCoin = new ArrayList<>();
                returnCoin.add(coin);
                return returnCoin;
            }

            // This tree doesn't yield a result
            if (price < 0 || availableCoins.size() == 0 && optimalSize == Integer.MAX_VALUE) {
                return null;
            }

            ArrayList<Coin> nextLevelCoins = recursiveCoinFinder(availableCoins, price);
            if (nextLevelCoins != null) {
                tempCoins.addAll(nextLevelCoins);
            }

            if (tempCoins.size() < optimalSize) {
                optimalCoins = tempCoins;
                optimalSize = tempCoins.size();
            }
        }
        return optimalCoins;
    }


}
