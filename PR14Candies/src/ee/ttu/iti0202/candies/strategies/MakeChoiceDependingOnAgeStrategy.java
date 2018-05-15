package ee.ttu.iti0202.candies.strategies;

import ee.ttu.iti0202.candies.candy.Candy;

import java.util.List;
import java.util.Optional;

public class MakeChoiceDependingOnAgeStrategy implements ChoosingCandyStrategy {

    @Override
    public Optional<Candy> chooseCandy(List<Candy> candies, int age) {
        return candies.stream()
                .filter(candy -> !(age < 5 && candy.getCandyType() == Candy.CandyType.GUM))
                .filter(candy -> !(age < 10 && candy.getCandyType() == Candy.CandyType.CHOCOLATE_CANDY))
                .findFirst();
    }
}
