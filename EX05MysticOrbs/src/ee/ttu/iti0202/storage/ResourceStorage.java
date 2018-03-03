package ee.ttu.iti0202.storage;

import java.util.HashMap;
import java.util.Map;

public class ResourceStorage {

    private Map<String, Integer> storage = new HashMap<>();

    public boolean isEmpty() {
        if (storage.size() > 0) {
            for (Integer amount : storage.values()) {
                if (amount > 0) return false;
            }
        }

        return true;
    }

    public void addResource(String resource, int amount) {
        if (amount < 0 || resource.equals("") || resource.matches(" +")) return;

        if (storage.keySet().contains(resource.toLowerCase())) {
            storage.put(resource.toLowerCase(), storage.get(resource.toLowerCase()) + amount);
        } else {
            storage.put(resource.toLowerCase(), amount);
        }
    }

    public int getResourceAmount(String resource) {
        if (storage.containsKey(resource.toLowerCase())) {
            return storage.get(resource.toLowerCase());
        }
        return 0;
    }

    public boolean hasEnoughResource(String resource, int amount) {
        if (amount < 1) return false;

        if (storage.containsKey(resource.toLowerCase())) return storage.get(resource.toLowerCase()) >= amount;

        return false;
    }

    public boolean takeResource(String resource, int amount) {
        if (this.hasEnoughResource(resource, amount)) {
            storage.put(resource, storage.get(resource) - amount);
            return true;
        }
        return false;
    }
}
