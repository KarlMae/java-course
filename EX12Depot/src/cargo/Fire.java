package cargo;

public class Fire extends Cargo {

    private final int risk = 6;

    private Class[] incompatible = new Class[]{Water.class};

    public Class[] getIncompatible() {
        return incompatible;
    }

    @Override
    public int getRisk() {
        return risk;
    }
}
