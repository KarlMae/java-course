package ee.ttu.iti0202.json.order;

import ee.ttu.iti0202.json.item.Item;

import java.util.List;

public class Order {

    private int order_number;
    private double total_price;
    private String customer;
    private List<Item> items;

    public List<Item> getItems() {
        return items;
    }

    public void setTotalPrice(double totalPrice) {
        this.total_price = totalPrice;
    }

    @Override
    public String toString() {
        return "Order{" +
                "number=" + order_number +
                ", total_price=" + total_price +
                ", customer='" + customer + '\'' +
                ", items=" + items +
                '}';
    }
}
