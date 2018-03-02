package ee.ttu.iti0202.card;

import ee.ttu.iti0202.bank.Bank;

import java.math.BigDecimal;

public abstract class BankCard {

    public enum CardType {CREDIT, DEBIT}
    public CardType type;
    Bank bank;
    BigDecimal balance;

    public static BankCard createCard(CardType cardType, Bank bank) {

        switch (cardType) {
            case DEBIT :    DebitCard debitCard = new DebitCard();
                            debitCard.type = cardType;
                            bank.addCard(debitCard);
                            debitCard.bank = bank;
                            return debitCard;

            case CREDIT :   CreditCard creditCard = new CreditCard();
                            creditCard.type = cardType;
                            bank.addCard(creditCard);
                            creditCard.bank = bank;
                            return creditCard;
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
        if (value.compareTo(BigDecimal.valueOf(0)) > 0) balance = balance.add(value);
    }

    public abstract boolean withdraw(BigDecimal value);
}
