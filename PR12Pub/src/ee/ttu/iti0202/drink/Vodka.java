package ee.ttu.iti0202.drink;

public class Vodka extends Drink {

    private int price;

    Vodka(int price) {
        this.price = price;
    }

    public int getPrice() {
        return price;
    }
}
