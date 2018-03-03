package ee.ttu.iti0202.oven;

import ee.ttu.iti0202.orb.Orb;
import ee.ttu.iti0202.storage.ResourceStorage;

import java.util.Optional;

public class Oven implements Comparable<Oven>, Fixable {

    String name;
    ResourceStorage resourceStorage;
    int createdOrbs;
    int timesFixed;

    Oven() {
    }

    public Oven(String name, ResourceStorage resourceStorage) {
        this.name = name;
        this.resourceStorage = resourceStorage;
        this.createdOrbs = 0;
        this.timesFixed = 0;
    }

    public String getName() {
        return name;
    }

    public ResourceStorage getResourceStorage() {
        return resourceStorage;
    }

    public int getCreatedOrbsAmount() {
        return createdOrbs;
    }

    public boolean isBroken() {
        return createdOrbs >= 15;
    }

    public void fix() {
    }

    public int getTimesFixed() {
        return timesFixed;
    }

    Optional<Orb> createNormalOrb() {
        Orb orb = new Orb(name);
        createdOrbs++;

        orb.charge("pearl", 1);
        orb.charge("silver", 1);

        Optional<Orb> orbOptional = Optional.of(orb);

        resourceStorage.takeResource("pearl", 1);
        resourceStorage.takeResource("silver", 1);

        return orbOptional;
    }

    public Optional<Orb> craftOrb() {
        Optional<Orb> orbOptional = Optional.empty();


        if (!this.isBroken() && resourceStorage.getResourceAmount("pearl") >= 1
                && resourceStorage.getResourceAmount("silver") >= 1) {

            orbOptional = createNormalOrb();
        }

        return orbOptional;
    }

    public int compareTo(Oven o) {
        Oven thisOven = this;
        Oven otherOven = o;

        if (thisOven.isBroken() && !otherOven.isBroken()) return -1;
        if (otherOven.isBroken() && !thisOven.isBroken()) return 1;

        if (thisOven.getClass() == SpaceOven.class && otherOven.getClass() != SpaceOven.class) return 1;
        if (otherOven.getClass() == SpaceOven.class && thisOven.getClass() != SpaceOven.class) return -1;
        if (thisOven.getClass() == otherOven.getClass()) {
            if (thisOven.getCreatedOrbsAmount() > otherOven.getCreatedOrbsAmount()) return 1;
            if (otherOven.getCreatedOrbsAmount() > otherOven.getCreatedOrbsAmount()) return -1;

            if (thisOven.getClass() == MagicOven.class) {
                if (thisOven.getCreatedOrbsAmount() % 2 == 0 && otherOven.getCreatedOrbsAmount() % 2 != 0) return 1;
                if (otherOven.getCreatedOrbsAmount() % 2 == 0 && thisOven.getCreatedOrbsAmount() % 2 != 0) return -1;

                if (thisOven.getClass() == InfinityMagicOven.class && otherOven.getClass() != InfinityMagicOven.class)
                    return 1;
                if (otherOven.getClass() == InfinityMagicOven.class && thisOven.getClass() != InfinityMagicOven.class)
                    return -1;
            }
        }

        int thisOvenNameLength = thisOven.name.length();
        int otherOvenNameLength = otherOven.name.length();

        return Integer.compare(thisOvenNameLength, otherOvenNameLength);

    }
}