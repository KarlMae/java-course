package ee.ttu.iti0202.orb;

public class SpaceOrb extends Orb {

    public SpaceOrb(String creator){
        this.creator = creator;
        this.energy = 100;
    }

    public boolean absorb(Orb orb) {
        if (orb.getEnergy() < super.energy) {
            super.energy += orb.energy;
            orb.energy = 0;
            return true;
        }

        return false;
    }

    @Override
    public void charge(String resource, int amount) {
    }

    @Override
    public String toString() {
        return "SpaceOrb by " + super.creator;
    }

}
