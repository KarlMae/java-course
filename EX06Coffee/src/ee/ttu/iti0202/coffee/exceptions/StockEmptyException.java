package ee.ttu.iti0202.coffee.exceptions;

public class StockEmptyException extends RuntimeException {

    public StockEmptyException(String ingredient) {
        super("There is no more " + ingredient + " left!");
    }
}