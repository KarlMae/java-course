package ee.ttu.iti0202.coffee.coffemakercontainer;

public abstract class Container {
    /*Container for different kinds of ingredients.
     * To make a new container:
     * - Add enum Container type
     * - Make new Exception, for when the container gets empty
     * - Add container to coffeemaker*/

    enum ContainerType {BEAN, MILK, COCOA}

    public ContainerType containerType;
    int containerSize;
    int ingredientLeft;

    public void useIngredient(int amount) {
        if (amount <= 0) return;
        ingredientLeft -= amount;
    }

    public boolean isEmpty() {
        return ingredientLeft <= 0;
    }

    public void fillContainer() {
        ingredientLeft = containerSize;
    }

    public int getIngredientLeft() {
        return ingredientLeft;
    }

}
