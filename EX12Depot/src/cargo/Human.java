package cargo;

public class Human extends Cargo {

    private final int risk = 1;

    private Class[] incompatible = new Class[]{Fire.class, Fuel.class};

    public Class[] getIncompatible() {
        return incompatible;
    }

    @Override
    public int getRisk() {
        return risk;
    }
}
