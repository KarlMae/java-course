package ee.ttu.iti0202.candies.strategies;

import ee.ttu.iti0202.candies.candy.Candy;

import java.util.List;
import java.util.Optional;

public class DummyStrategy implements ChoosingCandyStrategy {

    @Override
    public Optional<Candy> chooseCandy(List<Candy> candies, int age) {
        if (candies.size() < 2) {
            return Optional.empty();
        }

        if (age > 14) {
            return Optional.of(candies.get(0));
        } else {
            return Optional.of(candies.get(1));
        }
    }
}