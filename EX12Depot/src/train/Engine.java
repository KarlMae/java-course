package train;

public class Engine {

    private int allowedRisk;
    public enum conflictCargo { FIRE, FUEL, HUMAN, WATER, WOOD}
    private conflictCargo conflictCargo;

    public Engine(int allowedRisk) {
        this.allowedRisk = allowedRisk;
    }


    public void setConflictCargo(Engine.conflictCargo conflictCargo) {
        this.conflictCargo = conflictCargo;
    }

    public Engine.conflictCargo getConflictCargo() {
        return conflictCargo;
    }

    public int getAllowedRisk() {
        return allowedRisk;
    }
}
