package ee.ttu.iti0202.cars.car;

import ee.ttu.iti0202.cars.driver.Driver;

public abstract class Car {
    Driver driver;
    private long distanceDriven = 0;

    Car(){}

    Car(Driver driver) {
        this.driver = driver;
    }

    void drive(long distance) {
        this.distanceDriven += distance;
    }

    long getDistanceDriven() {
        return distanceDriven;
    }

    Driver getDriver() {
        return driver;
    }

    void setDriver(Driver driver) {
        this.driver = driver;
    }


}
