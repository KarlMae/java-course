package train;

import cargo.Cargo;

import java.util.Optional;

public class Car {

    private Cargo cargo;

    public Car() {
    }

    public Optional<Cargo> getCargo() {
        return Optional.ofNullable(cargo);
    }

    public void setCargo(Cargo cargo) {
        this.cargo = cargo;
    }
}
