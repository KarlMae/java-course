package ee.ttu.iti0202.json;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Order {

    @SerializedName("order_number")
    private Integer orderNumber;
    @SerializedName("total_price")
    private double totalPrice;
    private String customer;
    private List<Item> items;

    public List<Item> getItems() {
        return items;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    @Override
    public String toString() {
        return "Order{" + "number=" + orderNumber + ", total_price=" + totalPrice + ", customer='" + customer
                + '\'' + ", items=" + items + '}';
    }
}
