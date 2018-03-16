package ee.ttu.iti0202.coffee.kitchen;

import ee.ttu.iti0202.coffee.coffeemaker.AutomaticCoffeeMaker;
import ee.ttu.iti0202.coffee.coffeemaker.CapsuleCoffeeMaker;
import ee.ttu.iti0202.coffee.coffeemaker.CoffeeMaker;
import ee.ttu.iti0202.coffee.coffemakercontainer.Capsule;
import ee.ttu.iti0202.coffee.drink.Drink;
import ee.ttu.iti0202.coffee.drink.Water;
import ee.ttu.iti0202.coffee.exceptions.CannotMakeCoffeeException;
import ee.ttu.iti0202.coffee.exceptions.StockEmptyException;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class Kitchen {

    private List<CoffeeMaker> coffeeMakers = new ArrayList<>();
    private HashMap<String, Integer> stock = new HashMap<>();
    private Logger kitchenLogger = Logger.getLogger("kitchen.logger");
    private FileHandler fh;

    public Kitchen() {
        try {

            // This block configure the logger with handler and formatter
            kitchenLogger.setUseParentHandlers(false);

            List<String> lines = Arrays.asList("The first line", "The second line");
            Path file = Paths.get("kitchenlog.txt");
            Files.write(file, lines, Charset.forName("UTF-8"));

            fh = new FileHandler("kitchenlog.txt");
            kitchenLogger.addHandler(fh);
            SimpleFormatter formatter = new SimpleFormatter();
            fh.setFormatter(formatter);

        } catch (SecurityException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /* If something is wrong with the coffee maker i.e. water/ingredient is missing or garbage is full
     * barista will deal with the problem. This method is the barista.*/
    private boolean handleException(CannotMakeCoffeeException ex) {
        // Take out the garbage
        if (ex.getReason() == CannotMakeCoffeeException.Reason.GARBAGE_FULL) {
            kitchenLogger.log(Level.INFO, "Took out the garbage!");
            ex.getCoffeeMaker().getGarbageContainer().emptyGarbage();
        }

        // There is always enough water
        if (ex.getReason() == CannotMakeCoffeeException.Reason.OUT_OF_WATER) {
            kitchenLogger.log(Level.INFO, "Filled the water tank!");
            ex.getCoffeeMaker().getWaterContainer().fillTank();
        }

        // Try to refill the container.
        if (ex.getReason() == CannotMakeCoffeeException.Reason.OUT_OF_INGREDIENT) {
            try {
                ex.getContainer().fillContainer();
                kitchenLogger.log(Level.INFO, "Filled" + ex.getCoffeeMaker() + " " + ex.getContainer() + "!");
            } catch (StockEmptyException empty) {
                kitchenLogger.log(Level.INFO, "Not enough supplies to fill " + ex.getContainer() + "!");
                return false;
            }
        }
        return true;
    }

    /*Make coffee with an automatic coffee maker. If the containers are not fillable, returns water*/
    public Drink makeCoffee(AutomaticCoffeeMaker coffeeMaker, Drink drink) {
        try {
            drink = coffeeMaker.makeCoffee(drink);
        } catch (CannotMakeCoffeeException ex) {
            // Coffeemaker gives water, if there isn't enough resources to fill the container
            if (!handleException(ex)) return new Water();
        }
        return drink;
    }

    /* Make capsule coffee without giving in a specific capsule. Currently there's always enough capsules. */
    public Drink makeCapsuleCoffee(CapsuleCoffeeMaker coffeeMaker, Drink drink) {
        return makeCapsuleCoffee(coffeeMaker, new Capsule(drink));
    }

    /* Inserts capsule of choice into a coffeemaker instance and makes coffee */
    public Drink makeCapsuleCoffee(CapsuleCoffeeMaker coffeeMaker, Capsule capsule) {
        Drink drink;

        if (coffeeMaker.getCapsuleHolder() == null) {
            coffeeMaker.insertCapsule(capsule);
        } else {
            coffeeMaker.takeOutCapsule();
            coffeeMaker.insertCapsule(capsule);
        }

        // Since only problems that can arise (out of water, garbage full), it's always repairable.
        // While true is for cases, when garbage is full and water is empty at the same time.
        while (true) {
            try {
                drink = coffeeMaker.makeCoffee(capsule.getDrink());
                break;
            } catch (CannotMakeCoffeeException ex) {
                handleException(ex);
            }
        }
        return drink;

    }

    public void addCoffeeMachine(CoffeeMaker coffeeMaker) {
        coffeeMakers.add(coffeeMaker);
    }

    public List<CoffeeMaker> getCoffeeMakers() {
        return coffeeMakers;
    }

    public void refillStock(String resource, int amount) {
        if (amount <= 0) return;
        resource = resource.toLowerCase();

        if (stock.containsKey(resource)) {
            stock.put(resource, stock.get(resource) + amount);
        } else {
            stock.put(resource, amount);
        }
    }

    
}
