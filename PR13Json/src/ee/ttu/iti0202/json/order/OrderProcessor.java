package ee.ttu.iti0202.json.order;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import ee.ttu.iti0202.json.item.Item;

import java.util.List;
import java.util.stream.Collectors;

public class OrderProcessor {

    Gson gson = new Gson();
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
        Order order = gson.fromJson(json, Order.class);
        return order;
    }

    private String getJsonFromOder(Order order) {
        return gson.toJson(order);
    }

    public String process(String jsonInput) {

        Order order = getOrderFromJson(jsonInput);

        if (type == OrderProcessorType.DISCOUNT_10) order.getItems().forEach(item -> item.setPrice(item.getPrice() - item.getPrice() / 10));
        if (type == OrderProcessorType.CALCULATE_TOTAL) order.setTotalPrice(order.getItems().stream().mapToDouble(Item::getPrice).average().getAsDouble());
        if (type == OrderProcessorType.REMOVE_FIRST_ITEM) order.getItems().remove(0);
        return getJsonFromOder(order);
    }


    public static void main(String[] args) {
        OrderProcessor orderProcessor = new OrderProcessor(OrderProcessorType.CALCULATE_TOTAL);
        String result = orderProcessor.process("{\n" +
                "  \"order_number\": 12,\n" +
                "  \"customer\": \"Ago Luberg\",\n" +
                "  \"items\": [\n" +
                "    {\n" +
                "      \"item_id\": \"BOX1\",\n" +
                "      \"title\": \"A box\",\n" +
                "      \"price\": 14.00,\n" +
                "      \"count\": 3\n" +
                "    },\n" +
                "    {\n" +
                "      \"item_id\": \"CHOCO\",\n" +
                "      \"title\": \"Chocolate\",\n" +
                "      \"price\": 3.50\n" +
                "    }\n" +
                "  ]\n" +
                "}");

        System.out.println(result);
    }
}