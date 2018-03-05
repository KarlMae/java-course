package ee.ttu.iti0202.exceptions;

import ee.ttu.iti0202.oven.Oven;

public class CannotFixException extends RuntimeException {

    public enum Reason {IS_NOT_BROKEN, FIXED_MAXIMUM_TIMES, NOT_ENOUGH_RESOURCES}

    private Oven oven;
    private Reason reason;


    public CannotFixException(Oven oven, Reason reason) {
        super(oven + "cannot be fixed, because " + reason);
        this.oven = oven;
        this.reason = reason;
    }

    public Oven getOven() {
        return oven;
    }

    public Reason getReason() {
        return reason;
    }

}
