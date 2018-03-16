package ee.ttu.iti0202.coffee.coffemakercontainer;

public class MilkContainer extends Container {

    // Maybe add if milk gets spoiled
    public MilkContainer(int containerSize) {
        super.containerSize = containerSize;
        super.ingredientLeft = containerSize;
        super.containerType = ContainerType.MILK;
    }

}
