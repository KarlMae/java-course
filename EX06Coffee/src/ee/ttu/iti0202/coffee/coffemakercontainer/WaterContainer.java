package ee.ttu.iti0202.coffee.coffemakercontainer;

public class WaterContainer {

    private int waterInTank;
    private int tankCapacity;

    /* Water measured in milliliters
     *  i.e. 1000 units = 1 litre */
    public WaterContainer(int tankCapacity) {
        this.tankCapacity = tankCapacity;
        this.waterInTank = tankCapacity;
    }

    public void useWater(int amount) {
        // Could throw exception of not having enough here, but opted against it

        if (amount <= 0) return;

        waterInTank -= amount;

        if (waterInTank < 0) waterInTank = 0;
    }

    public boolean isEmpty() {
        return waterInTank <= 0;
    }

    public int waterLeft() {
        return waterInTank;
    }

    public void fillTank() {
        waterInTank = tankCapacity;
    }

}
