package ee.ttu.iti0202.coffee.coffemakercontainer;

public class BeanContainer extends Container {

    public BeanContainer(int containerSize) {
        super.containerSize = containerSize;
        super.ingredientLeft = containerSize;
        super.containerType = ContainerType.BEAN;
    }
}
