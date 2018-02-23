package ee.ttu.iti0202.kindergarten;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Kindergarten {

    private List<String> children = new ArrayList<>();

    public Kindergarten() {

    }

    public void addChild(String name) {
        this.children.add(name);
    }

    public List getChildrenList() {
        return this.children;
    }

    public String returnChild(int index) {
        if (index + 1 > children.size()) {
            return "";
        }
        return this.children.remove(index);
    }

    public void summerBreak() {
        this.children.clear();
    }

    public HashMap<String, Integer> getAllFirstNameAmounts() {
        HashMap<String, Integer> nameAmounts = new HashMap<>();

        for (String child : this.children) {
            if (nameAmounts.containsKey(child)) {
                nameAmounts.put(child, nameAmounts.get(child) + 1);
            } else {
                nameAmounts.put(child, 1);
            }
        }

        return nameAmounts;
    }

    public int getChildrenWithFirstName(String name) {
        int count = 0;

        for (String child : this.children){
            if (child.split("\\s+")[0].equals(name)) count ++;
        }

        if (count == 0) return -1;
        return count;
    }

    public long getMatchingLastNameAmount() {
        long count = 0L;
        HashMap<String, Boolean> recurringName = new HashMap<>();

        for (String child : children) {
            String lastName = child.split("\\s+")[1];
            if (recurringName.containsKey(lastName)) {
                if (!recurringName.get(lastName)) {
                    recurringName.put(lastName, true);
                    count ++;
                }
            } else {
                recurringName.put(lastName, false);
            }

        }
        return count;
    }
}



