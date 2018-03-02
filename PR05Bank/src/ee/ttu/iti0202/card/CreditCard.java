package ee.ttu.iti0202.card;

import java.math.BigDecimal;

public final class CreditCard extends BankCard {

    private final BigDecimal startingMoney = BigDecimal.valueOf(10000);
    private final BigDecimal debtLimit = BigDecimal.valueOf(5000);

    CreditCard() {
        super.balance = startingMoney;
    }


    public BigDecimal getDebt() {
        if (balance.compareTo(BigDecimal.valueOf(0)) < 0) {
            return balance.negate();
        }
        return BigDecimal.valueOf(0);
    }

    @Override
    public BigDecimal getBalance() {
        if (balance.compareTo(BigDecimal.valueOf(0)) < 0) {
            return BigDecimal.valueOf(0);
        } else {
            return balance;
        }
    }

    @Override
    public boolean withdraw(BigDecimal value) {
        if (value.compareTo(BigDecimal.valueOf(0)) <= 0) {
            return false;
        }

        if (value.compareTo(balance.add(debtLimit)) <= 0) {
            balance = balance.subtract(value);
            return true;
        }
        return false;
    }
}
