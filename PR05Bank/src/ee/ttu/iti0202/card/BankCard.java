package ee.ttu.iti0202.card;

import ee.ttu.iti0202.bank.Bank;

import java.math.BigDecimal;

import static ee.ttu.iti0202.card.BankCard.CardType.CREDIT;
import static ee.ttu.iti0202.card.BankCard.CardType.DEBIT;

public abstract class BankCard {

    public enum CardType {DEBIT, CREDIT}
    Bank bank;
    BigDecimal balance;

    public static BankCard createCard(CardType cardType, Bank bank) {
        if (cardType == DEBIT) {
            BankCard temp = new DebitCard();
            temp.bank = bank;
            bank.addCard(temp);
            return temp;
        }
        if (cardType == CREDIT) {
            BankCard temp = new CreditCard();
            temp.bank = bank;
            bank.addCard(temp);
            return temp;
        }
        return null;
    }

    public Bank getBank() {
        return bank;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void deposit(BigDecimal value) {
        if (value.compareTo(BigDecimal.valueOf(0)) > 0) value = balance.add(value);
    }

    public abstract boolean withdraw(BigDecimal value);
}
