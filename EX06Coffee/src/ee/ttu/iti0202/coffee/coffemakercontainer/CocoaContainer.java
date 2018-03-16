package ee.ttu.iti0202.coffee.coffemakercontainer;

public class CocoaContainer extends Container {

    public CocoaContainer(int containerSize) {
        super.containerSize = containerSize;
        super.ingredientLeft = containerSize;
        super.containerType = Container.ContainerType.COCOA;
    }
}
