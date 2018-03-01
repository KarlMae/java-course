package ee.ttu.iti0202.card;

import java.math.BigDecimal;

public final class DebitCard extends BankCard {

    void DebitCard() {
        super.balance = BigDecimal.valueOf(0);
    }


    @Override
    public boolean withdraw(BigDecimal value) {
        if (value.compareTo(BigDecimal.valueOf(0)) <= 0) {
            return false;
        }

        if (value.compareTo(balance) < 0) {
            balance = balance.subtract(value);
            return true;
        }
        return false;
    }
}
