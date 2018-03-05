package ee.ttu.iti0202.oven;

import ee.ttu.iti0202.exceptions.CannotFixException;
import ee.ttu.iti0202.orb.Orb;
import ee.ttu.iti0202.orb.SpaceOrb;
import ee.ttu.iti0202.storage.ResourceStorage;

import java.util.Optional;

public class SpaceOven extends Oven implements Fixable {

    private final int ovenBrokenAt = 25;
    private final int maxFixTimes = 5;

    public SpaceOven(String name, ResourceStorage resourceStorage) {
        super.name = name;
        super.resourceStorage = resourceStorage;
        timesFixed = 0;
    }

    @Override
    public void fix() {
        if (timesFixed >= maxFixTimes) {
            throw new CannotFixException(this, CannotFixException.Reason.FIXED_MAXIMUM_TIMES);
        }
        if (createdOrbs < ovenBrokenAt) {
            throw new CannotFixException(this, CannotFixException.Reason.IS_NOT_BROKEN);
        }

        int silverToFix = 40;
        int essenceToFix = 10;
        if (resourceStorage.getResourceAmount("liquid silver") >= silverToFix * (timesFixed + 1)) {
            createdOrbs = 0;
            resourceStorage.takeResource("liquid silver", silverToFix * (timesFixed + 1));
            timesFixed++;
        } else if (resourceStorage.getResourceAmount("star essence") >= essenceToFix * (timesFixed + 1)) {
            createdOrbs = 0;
            resourceStorage.takeResource("star essence", essenceToFix * (timesFixed + 1));
            timesFixed++;
        } else {
            throw new CannotFixException(this, CannotFixException.Reason.NOT_ENOUGH_RESOURCES);
        }
    }

    public int getTimesFixed() {
        return timesFixed;
    }

    @Override
    public Optional<Orb> craftOrb() {
        Optional<Orb> orbOptional = Optional.empty();

        final int starForOrb = 15;
        final int meteoriteForOrb = 1;
        if (!this.isBroken()
                && resourceStorage.getResourceAmount("meteorite stone") >= meteoriteForOrb
                && resourceStorage.getResourceAmount("star fragment") >= starForOrb) {

            Orb orb = new SpaceOrb(super.name);
            createdOrbs++;

            orbOptional = Optional.of(orb);

            resourceStorage.takeResource("meteorite stone", meteoriteForOrb);
            resourceStorage.takeResource("star fragment", starForOrb);
        } else if (resourceStorage.getResourceAmount("pearl") >= 1
                && resourceStorage.getResourceAmount("silver") >= 1) {

            orbOptional = super.createNormalOrb();
        }

        return orbOptional;
    }

    @Override
    public boolean isBroken() {
        if (timesFixed < maxFixTimes) {
            return createdOrbs >= ovenBrokenAt;
        }
        return false;
    }

}
