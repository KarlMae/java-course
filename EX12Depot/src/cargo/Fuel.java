package cargo;

public class Fuel extends Cargo {

    private final int risk = 5;

    private Class[] incompatible = new Class[]{Fire.class};

    public Class[] getIncompatible() {
        return incompatible;
    }

    @Override
    public int getRisk() {
        return risk;
    }
}
