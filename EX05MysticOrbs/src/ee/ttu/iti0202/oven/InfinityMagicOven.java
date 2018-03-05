package ee.ttu.iti0202.oven;

import ee.ttu.iti0202.exceptions.CannotFixException;
import ee.ttu.iti0202.storage.ResourceStorage;

public class InfinityMagicOven extends MagicOven {

    public InfinityMagicOven(String name, ResourceStorage resourceStorage) {
        super.name = name;
        super.resourceStorage = resourceStorage;
    }

    //
    public void fix() {
        throw new CannotFixException(this, CannotFixException.Reason.IS_NOT_BROKEN);
    }

    @Override
    public boolean isBroken() {
        return false;
    }
}
