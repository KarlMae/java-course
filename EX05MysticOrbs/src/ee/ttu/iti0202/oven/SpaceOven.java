package ee.ttu.iti0202.oven;

import ee.ttu.iti0202.orb.MagicOrb;
import ee.ttu.iti0202.orb.Orb;
import ee.ttu.iti0202.orb.SpaceOrb;
import ee.ttu.iti0202.storage.ResourceStorage;

import java.util.Optional;

public class SpaceOven extends Oven{

    public SpaceOven(String name, ResourceStorage resourceStorage) {
        super.name = name;
        super.resourceStorage = resourceStorage;
    }

    @Override
    public Optional<Orb> craftOrb() {
        Optional<Orb> orbOptional = Optional.empty();

        if (!this.isBroken()  && resourceStorage.getResourceAmount("meteorite stone") >= 1
                && resourceStorage.getResourceAmount("star fragment") >= 15) {

            Orb orb = new SpaceOrb(super.name);
            orbOptional = Optional.of(orb);

            resourceStorage.takeResource("gold", 1);
            resourceStorage.takeResource("dust", 3);
        }

        orbOptional = super.craftOrb();

        return orbOptional;
    }
}
