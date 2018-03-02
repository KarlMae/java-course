package ee.ttu.iti0202.card;

import ee.ttu.iti0202.bank.Bank;

import java.math.BigDecimal;

public final class CreditCard extends BankCard {

    CreditCard() {
        super.balance = BigDecimal.valueOf(10000);
    }


    public BigDecimal getDebt() {
        if (balance.compareTo(BigDecimal.valueOf(0)) < 0) {
            return balance.negate();
        }
        return BigDecimal.valueOf(0);
    }

    @Override
    public boolean withdraw(BigDecimal value){
        if (value.compareTo(BigDecimal.valueOf(0)) <= 0) {
            return false;
        }

        if (value.compareTo(balance.add(BigDecimal.valueOf(5000))) <= 0) {
            balance = balance.subtract(value);
            return true;
        }
        return false;
    }
}
