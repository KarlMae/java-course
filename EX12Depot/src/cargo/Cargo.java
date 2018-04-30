package cargo;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class Cargo {

    public abstract int getRisk();

    public abstract Class[] getIncompatible();
}
