package ee.ttu.iti0202.coffee.coffemakercontainer;

public class GarbageContainer {

    private int garbage;
    private int garbageLimit;


    public GarbageContainer(int garbageLimit) {
        this.garbageLimit = garbageLimit;
    }

    public void addGarbage(int amount) {
        if (amount < 0) return;
        garbage += amount;
    }

    public void addGarbage() {
        addGarbage(1);
    }

    public void emptyGarbage() {
        garbage = 0;
    }

    public boolean isFull() {
        return garbage >= garbageLimit;
    }
}
