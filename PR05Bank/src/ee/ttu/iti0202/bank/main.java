package ee.ttu.iti0202.bank;

import ee.ttu.iti0202.card.BankCard;
import ee.ttu.iti0202.card.CreditCard;

import java.math.BigDecimal;

public class main {

    public static void main(String[] args) {
        Bank ttuBank = new Bank("TTU pank");

        BankCard ttuDebitCard1 = BankCard.createCard(BankCard.CardType.DEBIT, ttuBank);
        BankCard ttuCreditCard1 = BankCard.createCard(BankCard.CardType.CREDIT, ttuBank);

        System.out.println(ttuBank); // Bank[TTU pank]: 2 cards.

        System.out.println("TTU debit card:");
        System.out.println(ttuDebitCard1.getBank() == ttuBank); // true
        System.out.println(ttuDebitCard1.getBalance()); // 0

        System.out.println("TTU credit card:");
        System.out.println(ttuCreditCard1.getBank() == ttuBank); // true
        System.out.println(ttuCreditCard1.getBalance()); // 10000
        System.out.println(((CreditCard) ttuCreditCard1).getDebt()); // 0

        System.out.println(ttuBank.getName()); // TTU pank
        System.out.println(ttuBank.getAllCards().size()); // 2
        System.out.println(ttuBank.getAllCreditCards().size()); // 1
        System.out.println(ttuBank.getAllDebitCards().size()); // 1

        System.out.println("Actions with debit card");
        ttuDebitCard1.deposit(new BigDecimal("5000"));
        System.out.println(ttuDebitCard1.getBalance()); // 5000
        System.out.println(ttuDebitCard1.withdraw(new BigDecimal("5001"))); // false
        System.out.println(ttuDebitCard1.getBalance()); // 5000
        System.out.println(ttuDebitCard1.withdraw(new BigDecimal("5000"))); // true
        System.out.println(ttuDebitCard1.getBalance()); // 0

        System.out.println("Actions with credit card");
        System.out.println(ttuCreditCard1.withdraw(new BigDecimal("9999"))); // true
        System.out.println(ttuCreditCard1.getBalance()); // 1
        System.out.println(ttuCreditCard1.withdraw(new BigDecimal("2"))); // true
        System.out.println(ttuCreditCard1.getBalance()); // 0
        System.out.println(((CreditCard) ttuCreditCard1).getDebt()); // 1
        System.out.println(ttuCreditCard1.withdraw(new BigDecimal("4999"))); // true
        System.out.println(ttuCreditCard1.getBalance()); // 0
        System.out.println(((CreditCard) ttuCreditCard1).getDebt()); // 5000
        System.out.println(ttuCreditCard1.withdraw(new BigDecimal("1"))); // false
    }
}
