import cargo.Fire;
import depot.Depot;
import train.Engine;

public class Main {

    public static void main(String[] args) {
        Depot depot = new Depot();
        depot.addEngine(new Engine(20));
        System.out.println(depot.createTrain());
        depot.addCar();
        System.out.println(depot.createTrain());
        depot.addCar();
        System.out.println(depot.createTrain());
        depot.addEngine(new Engine(20));
        depot.addCar();
        depot.addCar();
        depot.addCar();
        System.out.println(depot.createTrain(new Fire(), new Fire(), new Fire(), new Fire()));
        System.out.println(depot.createTrain());
    }
}
