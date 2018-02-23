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
            String name = child.split("\\s+")[0];

            if (nameAmounts.containsKey(name)) {
                nameAmounts.put(name, nameAmounts.get(child) + 1);
            } else {
                nameAmounts.put(name, 1);
            }
        }

        return nameAmounts;
    }

    public int getChildrenWithFirstName(String name) {
        int count = 0;

        for (String child : this.children) {
            if (child.split("\\s+")[0].equals(name)) count++;
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
                    count++;
                }
            } else {
                recurringName.put(lastName, false);
            }

        }
        return count;
    }

    public static void main(String[] args) {

        Kindergarten kindergarten = new Kindergarten();
        for (int i = 0; i < 10; i++) {
            kindergarten.addChild("Jon Snow");
        }
        for (int i = 0; i < 13; i++) {
            kindergarten.addChild("Arya Stark");
        }

        System.out.println("Children list contains " + kindergarten.getChildrenList().size() + " children."); // Children list contains 20 children.
        System.out.println("First names " + kindergarten.getAllFirstNameAmounts() + "."); // First names {Arya=10, Jon=10}.
        System.out.println("With name Jon amount " + kindergarten.getChildrenWithFirstName("Jon") + "."); // With name Jon amount 10.
        System.out.println("Pairs with matching last names " + kindergarten.getMatchingLastNameAmount() + "."); // Pairs with matching last names 2.
        System.out.println("Return the fifth child " + kindergarten.returnChild(5) + ".");  // Return the fifth child Jon Snow.
        System.out.println("List amount has decreased to " + kindergarten.getChildrenList().size() + "."); // List amount has decreased to 19.
        kindergarten.summerBreak(); // void
        System.out.println("No more children " + kindergarten.getChildrenList() + "."); // No more children [].
        System.out.println("Nobody with the name so return " + kindergarten.getChildrenWithFirstName("Arya") + "."); // Nobody with the name so return -1.
        System.out.println("Pairs with matching last names " + kindergarten.getMatchingLastNameAmount() + "."); // Pairs with matching last names 2.
        System.out.println("First names " + kindergarten.getAllFirstNameAmounts() + "."); // First names {Arya=10, Jon=10}.
        System.out.println("Return the fifth child " + kindergarten.returnChild(0) + ".");
    }
}



