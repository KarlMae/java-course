package ee.ttu.iti0202.orb;

public class MagicOrb extends Orb {

    public MagicOrb(String creator) {
        this.creator = creator;
        this.energy = 0;
    }

    @Override
    public void charge(String resource, int amount) {
        if (resource.toLowerCase().equals("dust") || resource.matches(" +") || amount < 0) return;

        super.energy += resource.length() * amount * 2;
    }

    @Override
    public String toString() {
        return "MagicOrb by " + super.creator;
    }
}
