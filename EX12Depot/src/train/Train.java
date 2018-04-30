package train;

import java.util.ArrayList;
import java.util.List;

public class Train {

    private Engine engine;
    private List<Car> cars = new ArrayList<>();

    public Train(Engine engine) {
        this.engine = engine;
    }

    public int getRisk() {
        int risk = 0;

        for (Car car: cars) {
            risk += car.getCargo().get().getRisk();
        }

        return risk;
    }

    public void addCar(Car car) {
        cars.add(car);
    }

    public Engine getEngine() {
        return engine;
    }
}
