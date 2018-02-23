package ee.ttu.iti0202.kindergarten;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

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
        if (index >= children.size() || index < 1) {
            return "";
        }
        return this.children.remove(index);
    }

    public void summerBreak() {
        this.children.clear();
    }

    public HashMap<String, Integer> getAllFirstNameAmounts() {
        HashMap<String, Integer> nameAmounts = new HashMap<>();

        if (children.size() < 1) {
            return nameAmounts;
        }

        for (String child : this.children) {
            String name = child.split("\\s+")[0];

            if (nameAmounts.containsKey(name)) {
                nameAmounts.put(name, nameAmounts.get(name) + 1);
            } else {
                nameAmounts.put(name, 1);
            }
        }

        return nameAmounts;
    }

    public int getChildrenWithFirstName(String name) {
        int count = 0;

        if (children.size() < 1) {
            return -1;
        }

        for (String child : this.children) {
            if (child.split("\\s+")[0].equals(name)) count++;
        }

        if (count == 0) return -1;
        return count;
    }

    public long getMatchingLastNameAmount() {
        long count = 0L;
        HashMap<String, Boolean> recurringName = new HashMap<>();

        if (children.size() < 1) {
            return 0L;
        }

        for (String child : children) {

            //Skip children who don't have 2 names
            String[] names = child.split("\\s+");
            if (names.length != 2) {
                continue;
            }

            String lastName = names[1];

            if (recurringName.containsKey(lastName)) {
                if (!recurringName.get(lastName)) {
                    recurringName.put(lastName, true);
                    count++;
                }
            } else {
                recurringName.put(lastName, false);
            }

        }
        return count;
    }
}



