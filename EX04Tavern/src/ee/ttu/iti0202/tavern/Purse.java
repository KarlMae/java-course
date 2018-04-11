package ee.ttu.iti0202.tavern;


import java.util.ArrayList;
import java.util.Collections;
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

    public List<Coin> pay(Price price) {
        int priceToPay = price.getPriceInBaseValue();

        // Reset best solution
        ArrayList<Coin> coinsToPay = new ArrayList<>();
        bestSolution.clear();
        bestSolutionOverPay = Integer.MAX_VALUE;

        // Used to avoid coins, that are already used
        Collections.sort(coins);

        // Find the best solution
        recursiveCoinFinder(coins, coinsToPay, priceToPay);

        coins = new ArrayList<>(bestSolutionCoinsLeft);

        if (bestSolution.size() != 0) {
            return bestSolution;
        } else {
            return null;
        }
    }

    private void saveSolution(List<Coin> usedCoins, List<Coin> availableCoins, int priceLeft) {
        bestSolution = new ArrayList<>(usedCoins);
        bestSolutionCoinsLeft = new ArrayList<>(availableCoins);
        bestSolutionOverPay = priceLeft;
    }

    private void recursiveCoinFinder(ArrayList<Coin> availableCoins, ArrayList<Coin> usedCoins, int priceLeft) {

        // Avoid unnecessary branches
        if (usedCoins.size() > bestSolution.size() && priceLeft < bestSolutionOverPay && bestSolutionOverPay != Integer.MAX_VALUE) return;

        System.out.println(usedCoins);

        // Base case
        if (priceLeft <= 0) {

            // This solution is better than the last one
            if (usedCoins.size() < bestSolution.size() && priceLeft == 0) {
                saveSolution(usedCoins, availableCoins, priceLeft);
                return;
            }

            // No solution yet, add one
            if (bestSolution.size() == 0) {
                saveSolution(usedCoins, availableCoins, priceLeft);
                return;
            }

            // This solution is worse than the saved one
            if (bestSolutionOverPay == 0) {
                return;
            }

            // This solution is better than the last one
            if (priceLeft > bestSolutionOverPay) {
                saveSolution(usedCoins, availableCoins, priceLeft);
                return;
            }

            // This solution is better than the last one
            if (usedCoins.size() < bestSolution.size() && priceLeft == bestSolutionOverPay) {
                saveSolution(usedCoins, availableCoins, priceLeft);
                return;
            }
            return;
        }

        // Try every coin
        for (int i = 0; i < availableCoins.size(); i++) {
            usedCoins.add(availableCoins.remove(i));
            recursiveCoinFinder(availableCoins, usedCoins, priceLeft - usedCoins.get(usedCoins.size() - 1).getValue());
            availableCoins.add(i, usedCoins.remove(usedCoins.size() - 1));

            if (i < availableCoins.size() - 1) {
                if (availableCoins.get(i).getValue() == availableCoins.get(i + 1).getValue()) {
                    i++;
                }
            }
        }
    }
}
