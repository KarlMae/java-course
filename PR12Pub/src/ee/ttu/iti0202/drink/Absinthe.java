package ee.ttu.iti0202.drink;

public class Absinthe extends Drink {

    private int price;

    Absinthe(int price) {
        this.price = price;
    }

    public int getPrice() {
        return price;
    }
}
