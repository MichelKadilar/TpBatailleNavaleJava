package board;

public class ShipPosOutOfBoardError extends Exception {

    public ShipPosOutOfBoardError(String errorMessage) {
        super(errorMessage);
    }
}
