package ee.ttu.iti0202.factory;

import ee.ttu.iti0202.exceptions.CannotFixException;
import ee.ttu.iti0202.orb.Orb;
import ee.ttu.iti0202.oven.Oven;
import ee.ttu.iti0202.storage.ResourceStorage;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class OrbFactory {

    private ResourceStorage resourceStorage;
    private List<Oven> ovens = new ArrayList<>();
    private List<Oven> unfixableOvens = new ArrayList<>();
    private List<Orb> orbs = new ArrayList<>();

    public OrbFactory(ResourceStorage resourceStorage) {
        this.resourceStorage = resourceStorage;
    }

    public void addOven(Oven oven) {
        if (oven.getResourceStorage() == resourceStorage && !ovens.contains(oven)) ovens.add(oven);
    }

    public List<Oven> getOvens() {
        return ovens;
    }

    public List<Oven> getOvensThatCannotBeFixed() {
        return unfixableOvens;
    }

    public void getRidOfOvensThatCannotBeFixed() {
        for (Oven oven : getOvensThatCannotBeFixed()) {
            ovens.remove(oven);
        }
    }

    public void optimizeOvensOrder() {

    }

    public List<Orb> getAndClearProducedOrbsList() {
        List<Orb> producedOrbs = new ArrayList<>(orbs);
        orbs.clear();
        return producedOrbs;
    }

    public int produceOrbs() {
        int amount = 0;

        for (Oven oven : ovens) {

            if (oven.isBroken()) {
                try {
                    oven.fix();
                } catch (CannotFixException ex) {
                    if (ex.getReason() == CannotFixException.Reason.FIXED_MAXIMUM_TIMES) {
                        this.getOvensThatCannotBeFixed().add(ex.getOven());
                    }
                }
            }

            Optional<Orb> newOrb = oven.craftOrb();
            if (newOrb.isPresent()) {
                orbs.add(newOrb.get());
                amount++;
            }
        }
        return amount;
    }

    public int produceOrbs(int cycles) {
        int amount = 0;

        for (int i = 0; i < cycles - 1; i++){
            amount += produceOrbs();
        }

        return amount;
    }
}
