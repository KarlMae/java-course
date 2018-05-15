package ee.ttu.iti0202.candies.strategies;

import ee.ttu.iti0202.candies.candy.Candy;

import java.util.List;
import java.util.Optional;

public class ChooseOnlySugarFreeCandiesStrategy implements ChoosingCandyStrategy {

    @Override
    public Optional<Candy> chooseCandy(List<Candy> candies, int age) {
        return candies.stream().filter(candy -> candy.getCandyType() == Candy.CandyType.SUGAR_FREE).findFirst();
    }
}
