package ee.ttu.iti0202.drink;

public class Rum extends Drink {

    private int price;

    Rum(int price) {
        this.price = price;
    }

    public int getPrice() {
        return price;
    }
}
