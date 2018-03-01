package ee.ttu.iti0202.bank;

import ee.ttu.iti0202.card.BankCard;
import ee.ttu.iti0202.card.CreditCard;
import ee.ttu.iti0202.card.DebitCard;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Bank {

    private String name;
    private List<DebitCard> debitCards = new ArrayList<>();
    private List<CreditCard> creditCards = new ArrayList<>();


    public Bank(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void addCard(BankCard card) {

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
        return "Bank " + name + ": " + (creditCards.size() + debitCards.size()) + ".";
    }

    public static void main(String[] args) {
        System.out.println(new Bank("Tere").toString());
    }

}
