package ee.ttu.iti0202.oven;

import ee.ttu.iti0202.orb.MagicOrb;
import ee.ttu.iti0202.orb.Orb;
import ee.ttu.iti0202.storage.ResourceStorage;

import java.util.Optional;

public class MagicOven extends Oven{

    MagicOven(){}

    public MagicOven(String name, ResourceStorage resourceStorage) {
        super.name = name;
        super.resourceStorage = resourceStorage;
    }

    @Override
    public boolean isBroken() {
        return super.createdOrbs >= 5;
    }

    @Override
    public Optional<Orb> craftOrb() {
        Optional<Orb> orbOptional = Optional.empty();


        if (!this.isBroken()  && resourceStorage.getResourceAmount("gold") >= 1
                && resourceStorage.getResourceAmount("dust") >= 3) {
            Orb orb;

            if (super.createdOrbs % 2 == 1) {
                orb = new MagicOrb(super.name);
            } else {
                orb = new Orb(super.name);
            }
            createdOrbs++;


            orb.charge("gold", 1);

            orbOptional = Optional.of(orb);

            resourceStorage.takeResource("gold", 1);
            resourceStorage.takeResource("dust", 3);
        }

        return orbOptional;
    }
}
