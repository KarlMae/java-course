package train;

public class Engine {

    private int allowedRisk;
    public enum ConflictCargo { FIRE, FUEL, HUMAN, WATER, WOOD }
    private ConflictCargo conflictCargo;

    public Engine(int allowedRisk) {
        this.allowedRisk = allowedRisk;
    }


    public void setConflictCargo(ConflictCargo conflictCargo) {
        this.conflictCargo = conflictCargo;
    }

    public ConflictCargo getConflictCargo() {
        return conflictCargo;
    }

    public int getAllowedRisk() {
        return allowedRisk;
    }
}
