package ee.ttu.iti0202.tavern;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Purse {


    private static ArrayList<Coin> coins = new ArrayList<>();
    private int balance = 0;

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
        List<Coin> coinsToPay = new ArrayList<>();

        if (priceToPay <= 0) return null;

        while (priceToPay > 0) {
            Coin coinToPay = tryEveryCoin(priceToPay);
            if (coinToPay == null) return coinsToPay;
            priceToPay -= coinToPay.getAmount() * coinToPay.getCurrency().getRate();

            coins.remove(coinToPay);
            coinsToPay.add(coinToPay);
        }

        return coinsToPay;
    }


}
