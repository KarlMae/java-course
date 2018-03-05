package ee.ttu.iti0202.oven;

import ee.ttu.iti0202.exceptions.CannotFixException;
import ee.ttu.iti0202.orb.MagicOrb;
import ee.ttu.iti0202.orb.Orb;
import ee.ttu.iti0202.storage.ResourceStorage;

import java.util.Optional;

public class MagicOven extends Oven implements Fixable {

    private int ovenBrokenAt = 5;

    MagicOven() {
    }

    public MagicOven(String name, ResourceStorage resourceStorage) {
        super.name = name;
        super.resourceStorage = resourceStorage;
        timesFixed = 0;
    }

    @Override
    public void fix() {
        int maxFixTimes = 10;
        if (timesFixed >= maxFixTimes) {
            throw new CannotFixException(this, CannotFixException.Reason.FIXED_MAXIMUM_TIMES);
        }
        if (createdOrbs < ovenBrokenAt) {
            throw new CannotFixException(this, CannotFixException.Reason.IS_NOT_BROKEN);
        }

        int clayToFix = 25;
        int powderToFix = 100;
        if (resourceStorage.getResourceAmount("clay") >= clayToFix * (timesFixed + 1)
                && resourceStorage.getResourceAmount("freezing powder") >= powderToFix * (timesFixed + 1)) {
            createdOrbs = 0;
            resourceStorage.takeResource("clay", clayToFix * (timesFixed + 1));
            resourceStorage.takeResource("freezing powder", powderToFix * (timesFixed + 1));
            timesFixed++;
        } else {
            throw new CannotFixException(this, CannotFixException.Reason.NOT_ENOUGH_RESOURCES);
        }
    }

    public int getTimesFixed() {
        return timesFixed;
    }

    @Override
    public boolean isBroken() {
        return super.createdOrbs >= ovenBrokenAt;
    }

    @Override
    public Optional<Orb> craftOrb() {
        Optional<Orb> orbOptional = Optional.empty();


        if (!this.isBroken() && resourceStorage.getResourceAmount("gold") >= 1
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
