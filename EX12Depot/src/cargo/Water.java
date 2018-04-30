package cargo;

public class Water extends Cargo {

    private final int risk = 3;

    private Class[] incompatible = new Class[]{Fuel.class};

    public Class[] getIncompatible() {
        return incompatible;
    }

    @Override
    public int getRisk() {
        return risk;
    }
}
