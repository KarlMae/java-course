package ee.ttu.iti0202.oven;

import ee.ttu.iti0202.storage.ResourceStorage;

public class InfinityMagicOven extends MagicOven{

    public InfinityMagicOven(String name, ResourceStorage resourceStorage) {
        super.name = name;
        super.resourceStorage = resourceStorage;
    }

    @Override
    public boolean isBroken() {
        return false;
    }
}
