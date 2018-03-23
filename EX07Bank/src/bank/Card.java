package bank;

import javafx.beans.property.SimpleStringProperty;

public class Card {
    private final SimpleStringProperty ownerName = new SimpleStringProperty("");
    private final SimpleStringProperty cardType = new SimpleStringProperty("");
    private final SimpleStringProperty cardNumber = new SimpleStringProperty("");
    private final SimpleStringProperty credit = new SimpleStringProperty("");


    public Card(String ownerName, String cardType, String cardNumber, String credit) {
        setOwnerName(ownerName);
        setCardType(cardType);
        setCardNumber(cardNumber);
        setCredit(credit);
    }

    public void setOwnerName(String ownerName) {
        this.ownerName.set(ownerName);
    }

    public void setCardType(String cardType) {
        this.cardType.set(cardType);
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber.set(cardNumber);
    }

    public void setCredit(String credit) {
        this.credit.set(credit);
    }

    public String getOwnerName() {
        return ownerName.get();
    }

    public String getCardType() {
        return cardType.get();
    }

    public String getCardNumber() {
        return cardNumber.get();
    }

    public String getCredit() {
        return credit.get();
    }

    public SimpleStringProperty creditProperty() {
        return credit;
    }

    public SimpleStringProperty cardNumberProperty() {
        return cardNumber;
    }

    public SimpleStringProperty cardTypeProperty() {
        return cardType;
    }

    public SimpleStringProperty ownerNameProperty() {
        return ownerName;
    }

}
