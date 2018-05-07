package ee.ttu.iti0202.json.order;

import com.google.gson.Gson;
import ee.ttu.iti0202.json.item.Item;

public class OrderProcessor {

    private Gson gson = new Gson();
    public enum OrderProcessorType {
        DISCOUNT_10,
        REMOVE_FIRST_ITEM,
        CALCULATE_TOTAL
    }

    private OrderProcessorType type;

    public OrderProcessor(OrderProcessorType type) {
        this.type = type;
    }

    private Order getOrderFromJson(String json) {
        return gson.fromJson(json, Order.class);
    }

    private String getJsonFromOder(Order order) {
        return gson.toJson(order);
    }

    public String process(String jsonInput) {
        Order order = getOrderFromJson(jsonInput);

        if (type == OrderProcessorType.DISCOUNT_10) order.getItems()
                .forEach(item -> item.setPrice(item.getPrice() - item.getPrice() / 10));
        if (type == OrderProcessorType.CALCULATE_TOTAL) order.setTotalPrice(order.getItems().stream()
                .mapToDouble(Item::getPrice).average().getAsDouble());
        if (type == OrderProcessorType.REMOVE_FIRST_ITEM) order.getItems().remove(0);
        return getJsonFromOder(order);
    }


    public static void main(String[] args) {

    }
}