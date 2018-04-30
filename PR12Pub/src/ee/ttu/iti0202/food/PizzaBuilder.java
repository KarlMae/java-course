package ee.ttu.iti0202.food;

import java.util.ArrayList;
import java.util.List;

public class PizzaBuilder {
    private int diameter;
    private int slices;
    private String name;
    private List<Pizza.PizzaComponent> components = new ArrayList<>();


    public PizzaBuilder setDiameter(int diameter) {
        this.diameter = diameter;
        return this;
    }

    public PizzaBuilder setSlices(int slices) {
        this.slices = slices;
        return this;
    }

    public PizzaBuilder setName(String name) {
        this.name = name;
        return this;
    }


    // Miks siin Pizzabuilder ees on
    public PizzaBuilder addComponent(Pizza.PizzaComponent component) {
        this.components.add(component);
        return this;
    }

    public Pizza createPizza() {
        return new Pizza(diameter, slices, name, components);
    }
}
