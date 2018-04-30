package depot;

import cargo.Cargo;
import exceptions.NoEnginesException;
import exceptions.UnfittingCargoException;
import train.Car;
import train.Engine;
import train.Train;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class Depot {

    private HashMap<Cargo, Car> cars;
    private List<Car> emptyCars = new ArrayList<>();
    private List<Engine> engines = new ArrayList<>();
    private List<Train> trains = new ArrayList<>();

    public Depot() {
    }

    public void addCar() {
        emptyCars.add(new Car());
    }


    public void addEngine(Engine engine) {
        engines.add(engine);
    }

    public Car getCar(Cargo cargo) {
        if (cars.containsKey(cargo)) return cars.remove(cargo);
        return null;
    }

    public Car getCar() {
        if (!emptyCars.isEmpty()) return emptyCars.get(0);
        return null;
    }

    private boolean incompatibleCargo(Cargo... cargo) {
        for (Cargo c : cargo) {
            if (Arrays.asList(c.getIncompatible()).contains(Arrays.asList(cargo).getClass())) {
                return true;
            }
        }
        return false;
    }

    public Train createTrain(Cargo... cargo) throws Exception{

        if (engines.isEmpty()) throw new NoEnginesException();
        if (incompatibleCargo(cargo)) throw new UnfittingCargoException();

        Train newTrain = new Train(engines.remove(0));

        for (Cargo c : cargo) {

            if (newTrain.getRisk() + c.getRisk() > newTrain.getEngine().getAllowedRisk()) continue;

            if (!emptyCars.isEmpty()) {
                Car processCar = emptyCars.remove(0);
                processCar.setCargo(c);
                newTrain.addCar(processCar);
            } else {
                trains.add(newTrain);
                return newTrain;
            }
        }
        trains.add(newTrain);
        return newTrain;
    }
}
