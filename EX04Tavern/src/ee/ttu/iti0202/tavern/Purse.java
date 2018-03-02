package ee.ttu.iti0202.tavern;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Purse {

    private ArrayList<Coin> coins = new ArrayList<>();
    private ArrayList<Coin> bestSolution = new ArrayList<>();
    private ArrayList<Coin> bestSolutionCoinsLeft = new ArrayList<>();
    private int bestSolutionSize = Integer.MAX_VALUE;
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
        bestSolutionSize = Integer.MAX_VALUE;
        recursiveCoinFinder(coins, coinsToPay, priceToPay);
        coins = new ArrayList<>(bestSolutionCoinsLeft);

        if (bestSolution.size() != 0) {
            Collections.reverse(bestSolution);
            return bestSolution;
        } else {
            return null;
        }
    }

    private void recursiveCoinFinder(ArrayList<Coin> availableCoins, ArrayList<Coin> usedCoins, int priceLeft) {
        // Base case
        if (priceLeft <= 0) {
            if (usedCoins.size() < bestSolutionSize && priceLeft == 0) {
                bestSolutionOverPay = 0;
                bestSolution = new ArrayList<>(usedCoins);
                bestSolutionCoinsLeft = new ArrayList<>(availableCoins);
                return;
            }
            if (bestSolutionSize == Integer.MAX_VALUE) {
                bestSolution = new ArrayList<>(usedCoins);
                bestSolutionCoinsLeft = new ArrayList<>(availableCoins);
                bestSolutionOverPay = priceLeft;
                return;
            }
            if (bestSolutionOverPay == 0) {
                return;
            }

            if (usedCoins.size() == bestSolution.size() && priceLeft > bestSolutionOverPay) {
                bestSolution = new ArrayList<>(usedCoins);
                bestSolutionCoinsLeft = new ArrayList<>(availableCoins);
                bestSolutionOverPay = priceLeft;
            }
            return;
        }

        List<Coin> usedCoinAvoidPermutation = new ArrayList<>();

        // Try every coin
        for (int i = 0; i < availableCoins.size(); i++) {
            Coin coin = availableCoins.get(i);

            // Select a coin from the arraylist
            if (usedCoinAvoidPermutation.size() == 0){
                if (usedCoinAvoidPermutation.contains(coin)){
                    return;
                }
                usedCoinAvoidPermutation.add(coin);
            } else {
                usedCoinAvoidPermutation.add(coin);
            }

            usedCoins.add(coin);
            availableCoins.remove(i);

            recursiveCoinFinder(availableCoins, usedCoins, priceLeft - coin.getValue());

            usedCoins.remove(usedCoins.size() - 1);
            availableCoins.add(i, coin);
        }
    }
}
