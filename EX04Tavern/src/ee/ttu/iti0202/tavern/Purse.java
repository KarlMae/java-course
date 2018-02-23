package ee.ttu.iti0202.tavern;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        return coins;
    }

    private Coin tryEveryCoin(int priceToPay) {
        Coin smallestCoin = null;

        for (Coin c : coins) {
            if (c.getAmount() * c.getCurrency().getRate() < priceToPay) {
                if (smallestCoin == null) { smallestCoin = c; }

                // If new coin is higher in value
                if (c.getAmount() * c.getCurrency().getRate() > smallestCoin.getAmount() * smallestCoin.getCurrency().getRate()) {
                    smallestCoin = c;
                }
            }
        }
        if (smallestCoin != null) {
            return smallestCoin;
        } else {
            return null;
        }
    }

    public List<Coin> pay(Price price) {
        int priceToPay = price.getPriceInBaseValue();
        List<Coin> coinsToPay = new ArrayList<>();

        if (priceToPay <= 0) return null;

        while(priceToPay > 0){
            Coin coinToPay = tryEveryCoin(priceToPay);
            if (coinToPay == null) return null;
            priceToPay -= coinToPay.getAmount() * coinToPay.getCurrency().getRate();

            coinsToPay.add(coinToPay);
        }

        return coinsToPay;
    }


}
