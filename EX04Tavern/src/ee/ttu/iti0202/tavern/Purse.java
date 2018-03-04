package ee.ttu.iti0202.tavern;


import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Purse {

    private ArrayList<Coin> coins = new ArrayList<>();
    private ArrayList<Coin> bestSolution = new ArrayList<>();
    private ArrayList<Coin> bestSolutionCoinsLeft = new ArrayList<>();
    private int bestSolutionOverPay = Integer.MAX_VALUE;

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
        bestSolutionOverPay = Integer.MAX_VALUE;
        Collections.sort(coins);
        recursiveCoinFinder(coins, coinsToPay, priceToPay);
        coins = new ArrayList<>(bestSolutionCoinsLeft);

        if (bestSolution.size() != 0) {
            return bestSolution;
        } else {
            return null;
        }
    }

    private void setSolution(List<Coin> usedCoins, List<Coin> availableCoins, int priceLeft) {
        bestSolution = new ArrayList<>(usedCoins);
        bestSolutionCoinsLeft = new ArrayList<>(availableCoins);
        bestSolutionOverPay = priceLeft;
    }

    private void recursiveCoinFinder(ArrayList<Coin> availableCoins, ArrayList<Coin> usedCoins, int priceLeft) {
        // Base case

        if (usedCoins.size() > bestSolution.size() && priceLeft < bestSolutionOverPay && bestSolution.size() != 0) return;

        if (priceLeft <= 0) {
            if (usedCoins.size() < bestSolution.size() && priceLeft == 0) {
                setSolution(usedCoins, availableCoins, priceLeft);
                return;
            }

            if (bestSolution.size() == 0) {
                setSolution(usedCoins, availableCoins, priceLeft);
                return;
            }

            if (bestSolutionOverPay == 0) {
                return;
            }
            if (usedCoins.size() < bestSolution.size()) {
                setSolution(usedCoins, availableCoins, priceLeft);
                return;
            }

            if (usedCoins.size() == bestSolution.size() && priceLeft > bestSolutionOverPay) {
                setSolution(usedCoins, availableCoins, priceLeft);
                return;
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

            if (i < availableCoins.size() - 1) {
                Coin nextCoin = availableCoins.get(i + 1);
                if (coin == nextCoin) {
                    i++;
                }
            }
        }
    }
}
