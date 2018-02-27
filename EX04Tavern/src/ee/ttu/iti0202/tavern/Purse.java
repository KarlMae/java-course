package ee.ttu.iti0202.tavern;

import java.util.*;

public class Purse {

    private ArrayList<Coin> coins = new ArrayList<>();
    private ArrayList<Coin> bestSolution = new ArrayList<>();
    private ArrayList<Coin> bestSolutionCoinsLeft = new ArrayList<>();
    private Boolean bestSolutionExact = false;

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
        ArrayList<Coin> coinsToPay = new ArrayList<>();
        bestSolution.clear();
        bestSolutionExact = false;
        recursiveCoinFinder(coins, coinsToPay, priceToPay);
        coins = new ArrayList<>(bestSolutionCoinsLeft);

        return bestSolution;
    }

    private void recursiveCoinFinder(ArrayList<Coin> availableCoins, ArrayList<Coin> usedCoins, int priceLeft) {
        // Base case
        if (priceLeft  <= 0) {
            if (usedCoins.size() < bestSolution.size() && priceLeft == 0) {
                bestSolution = new ArrayList<>(usedCoins);
                bestSolutionCoinsLeft = new ArrayList<>(availableCoins);
                bestSolutionExact = true;
                return;
            }
            if (bestSolution.size() == 0){
                bestSolution = new ArrayList<>(usedCoins);
                bestSolutionCoinsLeft = new ArrayList<>(availableCoins);
                if (priceLeft == 0) bestSolutionExact = true;
                return;
            }
            if (bestSolutionExact) {
                return;
            }
            if (priceLeft < 0 && usedCoins.size() < bestSolution.size()) {
                bestSolution = new ArrayList<>(usedCoins);
                bestSolutionCoinsLeft = new ArrayList<>(availableCoins);
            }
            return;
        }

        // Try every coin
        for (int i = 0; i < availableCoins.size(); i++) {
            // Select a coin from the arraylist
            Coin coin = availableCoins.get(i);
            usedCoins.add(coin);
            availableCoins.remove(i);

            recursiveCoinFinder(availableCoins, usedCoins, priceLeft - coin.getValue());

            usedCoins.remove(usedCoins.size() - 1);
            availableCoins.add(i, coin);
        }
    }
}
