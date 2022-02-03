package board;

public class ShipStruckAtSamePosError extends Exception{

    public ShipStruckAtSamePosError(String errorMessage) {
        super(errorMessage);
    }
}
