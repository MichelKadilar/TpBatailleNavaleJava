package board;

public class ShipStruckTooManyTimesError extends Exception {

    public ShipStruckTooManyTimesError(String errorMessage) {
        super(errorMessage);
    }
}
