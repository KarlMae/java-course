package ee.ttu.iti0202.json;

public class Item {

    private String id;
    private String title;
    private double price;
    private int count = 1;

    public double getPrice() {
        return price;
    }

    public int getCount() {
        return count;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Item{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", price=" + price +
                ", count=" + count +
                '}';
    }
}
