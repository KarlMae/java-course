package ee.ttu.iti0202.storage;

import java.util.HashMap;
import java.util.Map;

public class ResourceStorage {

    Map<String, Integer> storage = new HashMap<>();

    public boolean isEmpty() {
        if (storage.size() > 0) {
            for (Integer amount : storage.values()) {
                if (amount > 0) return true;
            }
        }

        return false;
    }

    public void addResource(String resource, int amount) {
        if (storage.keySet().contains(resource)) {
            storage.put(resource, storage.get(resource) + amount);
        } else {
            storage.put(resource, amount);
        }
    }

    public int getResourceAmount(String resource) {
        if (storage.containsKey(resource)) {
            return storage.get(resource);
        }
        return 0;
    }

    public boolean hasEnoughResource(String resource, int amount) {
        if (amount < 1) return false;

        if (storage.containsKey(resource)) return storage.get(resource) > amount;

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
