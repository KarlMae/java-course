package cargo;

public class Wood extends Cargo {

    private int risk = 2;

    private Class[] incompatible = new Class[]{Fire.class, Water.class};

    public Class[] getIncompatible() {
        return incompatible;
    }

    @Override
    public int getRisk() {
        return risk;
    }
}
