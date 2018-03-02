package ee.ttu.iti0202.tavern;

import java.util.Objects;

public class Coin implements Comparable<Coin> {

    private int amount;
    private Currency currency;
    private int value;

    public Coin(int amount, Currency currency) {
        this.amount = amount;
        this.currency = currency;
        this.value = amount * Currency.getRate(currency);
    }

    public Coin(Currency currency) {
        this.amount = 1;
        this.currency = currency;
        this.value = amount * Currency.getRate(currency);
    }

    public int getAmount() {
        return this.amount;
    }

    public Currency getCurrency() {
        return this.currency;
    }

    public int getValue() {
        return this.value;
    }


    @Override
    public String toString() {
        return String.format("%s %s", amount, currency);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Coin coin = (Coin) o;
        return amount == coin.amount
                && Objects.equals(currency, coin.currency);
    }

    @Override
    public int hashCode() {
        return Objects.hash(amount, currency);
    }

    @Override
    public int compareTo(Coin c) {
        int compareValue = (c.getValue());
        //For descending order
        return compareValue - this.getValue();
    }
}
