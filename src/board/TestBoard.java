package board;

import ships.Battleship;
import ships.Destroyer;
import ships.Direction;

public class TestBoard {

    public static void main(String[] args) throws ShipPosOutOfBoardError, ShipSuperpositionError {
        Board board = new Board("Bataille Navale");
        System.out.println(board.getSize());
        Destroyer destroyer = new Destroyer();
        Battleship battleship = new Battleship(Direction.WEST);
        board.putShip(destroyer, 4,4);
        board.putShip(battleship, 4,4);
        board.setHit(false, 4,4);
        board.print();
    }
}
