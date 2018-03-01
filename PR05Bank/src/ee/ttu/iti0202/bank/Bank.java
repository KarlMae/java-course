package ee.ttu.iti0202.bank;

import ee.ttu.iti0202.card.BankCard;
import ee.ttu.iti0202.card.DebitCard;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Bank {

    private String name;
    private Map<BankCard> cards = new HashMap<>();


    public Bank(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void addCard(BankCard card) {

    }

    public List<BankCard> getAllCards() {
        return cards;
    }

    public List<DebitCard> getAllDebitCards() {
        return
    }


}
