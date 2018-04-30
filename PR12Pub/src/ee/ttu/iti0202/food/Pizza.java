package ee.ttu.iti0202.food;

import java.util.List;

public class Pizza {

    public enum PizzaComponent { ONION, EXTRA_CHEESE, OLIVE, ANANAS }

    private int diameter = 20;
    private int slices = 8;
    private String name;
    private List<PizzaComponent> components;

    Pizza(int diameter, int slices, String name, List<PizzaComponent> components) {
        this.slices = slices;
        this.diameter = diameter;
        this.name = name;
        this.components = components;
    }

    void addComponent(PizzaComponent component) {
        this.components.add(component);
    }

    public int getDiameter() {
        return diameter;
    }

    public int getSlices() {
        return slices;
    }

    public String getName() {
        return name;
    }
}
