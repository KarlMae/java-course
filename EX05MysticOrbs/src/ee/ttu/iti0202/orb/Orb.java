package ee.ttu.iti0202.orb;

public class Orb {

    String creator;
    int energy;

    Orb() {}

    public Orb(String creator){
        this.creator = creator;
        this.energy = 0;
    }

    public void charge(String resource, int amount){
        if (resource.equals("dust") || resource.matches(" +")) return;

        energy += resource.length() * amount;
    }

    public int getEnergy() {
        return energy;
    }


    @Override
    public String toString() {
        return "Orb by " + creator;
    }

}
