package ee.ttu.iti0202.coffee.main;

import ee.ttu.iti0202.coffee.coffeemaker.AutomaticCoffeeMaker;
import ee.ttu.iti0202.coffee.coffeemaker.CapsuleCoffeeMaker;
import ee.ttu.iti0202.coffee.coffemakercontainer.Capsule;
import ee.ttu.iti0202.coffee.drink.Drink;
import ee.ttu.iti0202.coffee.drink.Latte;
import ee.ttu.iti0202.coffee.drink.Coffee;
import ee.ttu.iti0202.coffee.drink.Cocoa;
import ee.ttu.iti0202.coffee.drink.Cappuccino;
import ee.ttu.iti0202.coffee.kitchen.Kitchen;

public class Main {

    public static final int randomLoopNumber = 50;

    public static void main(String[] args) {
        Kitchen kitchen = new Kitchen();
        CapsuleCoffeeMaker capsuleCoffeeMaker = new CapsuleCoffeeMaker();
        AutomaticCoffeeMaker automaticCoffeeMaker = new AutomaticCoffeeMaker();
        kitchen.addCoffeeMachine(capsuleCoffeeMaker);
        kitchen.addCoffeeMachine(automaticCoffeeMaker);
        System.out.println("-----------------------Testing kitchen------------------------");
        System.out.println(kitchen.getCoffeeMakers());

        System.out.println("-----------------Testing capsule coffee maker-----------------");

        Drink latte = new Latte();
        Capsule latteCapsule = new Capsule(latte);
        System.out.println("Drink in capsule: " + latteCapsule.getDrink());        // Latte
        System.out.println(latteCapsule.getIngredients());  // {beans=100, milk=250, water=250}
        System.out.println("Water left in capsule coffee machine: " + capsuleCoffeeMaker.getWaterContainer()
                .waterLeft());

        // Make coffee using capsule
        capsuleCoffeeMaker.insertCapsule(latteCapsule);
        System.out.println("I made one " + capsuleCoffeeMaker.makeCoffee()); // I made one Latte
        System.out.println(latteCapsule.getIngredients());  // {}
        System.out.println("Water left in capsule coffee machine: " + capsuleCoffeeMaker.getWaterContainer()
                .waterLeft()); // 4750

        // Make coffee with machine self selecting capsule
        System.out.println("I made one " + capsuleCoffeeMaker.makeCoffee(new Coffee()));
        System.out.println("Water left in capsule coffee machine: " + capsuleCoffeeMaker.getWaterContainer()
                .waterLeft()); // 4250

        for (int i = 0; i < 10; i++) {
            kitchen.makeCapsuleCoffee(capsuleCoffeeMaker, new Coffee());
        }

        // 4250 - 8 coffee's -> Refill -> 5000 - 2 coffee's
        System.out.println("Water left in capsule coffee machine: " + capsuleCoffeeMaker.getWaterContainer()
                .waterLeft()); // 4000
        Drink cocoa = new Cocoa();
        Capsule cocoaCapsule = new Capsule(cocoa);
        capsuleCoffeeMaker.changeCapsule(cocoaCapsule);
        System.out.println("I made one " + capsuleCoffeeMaker.makeCoffee()); // I made one Cocoa
        System.out.println("Water left in capsule coffee machine: " + capsuleCoffeeMaker.getWaterContainer()
                .waterLeft()); // 4000
        System.out.println(capsuleCoffeeMaker.getCapsuleHolder().getIngredients()); // Capsule empty
        capsuleCoffeeMaker.takeOutCapsule();
        System.out.println(capsuleCoffeeMaker.getCapsuleHolder()); // null - No capsule


        System.out.println("-----------------Testing automatic coffee maker-----------------");

        System.out.println("Milk left: " + automaticCoffeeMaker.getMilkContainer().getIngredientLeft());
        System.out.println("Coffee beans left: " + automaticCoffeeMaker.getBeanContainer().getIngredientLeft());
        System.out.println("Cocoa beans left: " + automaticCoffeeMaker.getCocoaContainer().getIngredientLeft());
        System.out.println("Water left: " + automaticCoffeeMaker.getWaterContainer().waterLeft());
        System.out.println("Garbage full: " + automaticCoffeeMaker.getGarbageContainer().isFull());

        Drink cappuccino = new Cappuccino();
        System.out.println(automaticCoffeeMaker.makeCoffee(cappuccino));

        System.out.println("Coffee beans left: " + automaticCoffeeMaker.getBeanContainer().getIngredientLeft());
        System.out.println("Water left: " + automaticCoffeeMaker.getWaterContainer().waterLeft());
        System.out.println(kitchen.getCoffeeMakers());

        for (int i = 0; i < randomLoopNumber; i++) {
            kitchen.makeCoffee(automaticCoffeeMaker, cappuccino);
        }
    }
}
