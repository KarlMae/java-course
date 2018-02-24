package ee.ttu.iti0202.tavern;

public class Coin implements Comparable<Coin> {

    private int amount;
    private Currency currency;

    public Coin(int amount, Currency currency) {
        this.amount = amount;
        this.currency = currency;
    }

    public Coin(Currency currency) {
        this.amount = 1;
        this.currency = currency;
    }

    public int getAmount() {
        return this.amount;
    }

    public Currency getCurrency() {
        return this.currency;
    }

    public int getValue() {
        return this.amount * Currency.getRate(this.currency);
    }


    @Override
    public String toString() {
        return String.format("%s %s", amount, currency);
    }

    @Override
    public boolean equals(Object obj) {
        return (((Coin) obj).amount == this.amount && ((Coin) obj).currency == this.currency);
    }

    @Override
    public int compareTo(Coin c) {
        int compareValue = (c.getValue());
        //For descending order
        return compareValue - this.getValue();
    }
}
