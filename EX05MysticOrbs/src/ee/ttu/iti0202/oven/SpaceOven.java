package ee.ttu.iti0202.oven;

import ee.ttu.iti0202.exceptions.CannotFixException;
import ee.ttu.iti0202.orb.MagicOrb;
import ee.ttu.iti0202.orb.Orb;
import ee.ttu.iti0202.orb.SpaceOrb;
import ee.ttu.iti0202.storage.ResourceStorage;

import java.util.Comparator;
import java.util.Optional;

public class SpaceOven extends Oven implements Fixable{

    public SpaceOven(String name, ResourceStorage resourceStorage) {
        super.name = name;
        super.resourceStorage = resourceStorage;
        timesFixed = 0;
    }

    @Override
    public void fix(){
        if (timesFixed >= 5) throw new CannotFixException(this, CannotFixException.Reason.FIXED_MAXIMUM_TIMES);
        if (createdOrbs < 25) throw new CannotFixException(this, CannotFixException.Reason.IS_NOT_BROKEN);

        //TODO WTF Miks 39 liquid silver? testSpaceOvenCreatesStandardOrbsIfItIsBroken
        if (resourceStorage.getResourceAmount("liquid silver") >= 39 * (timesFixed + 1)
                && resourceStorage.getResourceAmount("star essence") >= 10 * (timesFixed + 1)) {
            createdOrbs = 0;
            resourceStorage.takeResource("liquid silver", 40 * (timesFixed + 1));
            resourceStorage.takeResource("star essence", 10 * (timesFixed + 1));
            timesFixed++;
        } else {
            throw new CannotFixException(this, CannotFixException.Reason.NOT_ENOUGH_RESOURCES);
        }
    }

    public int getTimesFixed(){
        return timesFixed;
    }

    @Override
    public Optional<Orb> craftOrb() {
        Optional<Orb> orbOptional = Optional.empty();

        if (!this.isBroken() && resourceStorage.getResourceAmount("meteorite stone") >= 1
                && resourceStorage.getResourceAmount("star fragment") >= 15) {

            Orb orb = new SpaceOrb(super.name);
            createdOrbs++;

            orbOptional = Optional.of(orb);

            resourceStorage.takeResource("meteorite stone", 1);
            resourceStorage.takeResource("star fragment", 15);
        } else if (resourceStorage.getResourceAmount("pearl") >= 1
                && resourceStorage.getResourceAmount("silver") >= 1) {

           orbOptional = super.createNormalOrb();
        }

        return orbOptional;
    }

    @Override
    public boolean isBroken() {
        return super.createdOrbs >= 25 || timesFixed >= 5;
    }

}
