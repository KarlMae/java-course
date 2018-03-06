package ee.ttu.iti0202.bank;

import ee.ttu.iti0202.card.BankCard;
import ee.ttu.iti0202.card.CreditCard;
import ee.ttu.iti0202.card.DebitCard;

import java.util.ArrayList;
import java.util.List;

import static ee.ttu.iti0202.card.BankCard.CardType.CREDIT;
import static ee.ttu.iti0202.card.BankCard.CardType.DEBIT;

public class Bank {

    public String name;
    private List<DebitCard> debitCards = new ArrayList<>();
    private List<CreditCard> creditCards = new ArrayList<>();
    public static List<Bank> banks = new ArrayList<>();


    public Bank(String name) {
        this.name = name;
        banks.add(this);
    }

    public String getName() {
        return name;
    }

    public void addCard(BankCard card) {
        if (card.type == CREDIT) {
            for (Bank bank : banks) {
                if (bank.creditCards.contains((CreditCard) card)) {
                    return;
                }
            }
            creditCards.add((CreditCard) card);
        } else if (card.type == DEBIT) {
            for (Bank bank : banks) {
                if (bank.debitCards.contains((DebitCard) card)) {
                    return;
                }
            }
            debitCards.add((DebitCard) card);
        }
    }

    public List<BankCard> getAllCards() {
        List<BankCard> allCards = new ArrayList<>(debitCards);
        allCards.addAll(creditCards);
        return allCards;
    }

    public List<DebitCard> getAllDebitCards() {
        return debitCards;
    }

    public List<CreditCard> getAllCreditCards() {
        return creditCards;
    }

    @Override
    public String toString() {
        return "Bank[" + name + "]: " + (creditCards.size() + debitCards.size()) + " cards.";
    }

}
