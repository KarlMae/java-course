package ee.ttu.iti0202.oven;

import ee.ttu.iti0202.orb.Orb;
import ee.ttu.iti0202.storage.ResourceStorage;

import java.util.Optional;

public class Oven {

    String name;
    ResourceStorage resourceStorage;
    int createdOrbs;

    Oven(){}

    public Oven(String name, ResourceStorage resourceStorage){
        this.name = name;
        this.resourceStorage = resourceStorage;
        this.createdOrbs = 0;
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

    public Optional<Orb> craftOrb() {
        Optional<Orb> orbOptional = Optional.empty();


        if (!this.isBroken()  && resourceStorage.getResourceAmount("pearl") >= 1
                && resourceStorage.getResourceAmount("silver") >= 1) {

            Orb orb = new Orb(name);

            orb.charge("pearl", 1);
            orb.charge("silver", 1);

            orbOptional = Optional.of(orb);

            resourceStorage.takeResource("pearl", 1);
            resourceStorage.takeResource("silver", 1);
        }

        return orbOptional;
    }

}