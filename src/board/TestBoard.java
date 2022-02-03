package board;

import ships.Battleship;
import ships.Destroyer;
import ships.Direction;
import ships.ShipState;

public class TestBoard {

    public static void main(String[] args) throws ShipPosOutOfBoardError, ShipSuperpositionError {
        Board board = new Board("Bataille Navale");
        System.out.println(board.getSize());
        Destroyer destroyer = new Destroyer();
        Battleship battleship = new Battleship(Direction.WEST);
        // Il faut faire un try catch pour putship ici, mais je ne sais pas encore quoi faire dans le catch
        board.putShip(destroyer, 4,4);
        board.putShip(battleship, 8,8);
        board.setHit(false, 4,4);
        board.setHit(false, 5,4);
        ShipState s = new ShipState(destroyer, false);
        board.setHit(false, 4,4);
        board.print();
        System.out.println(s.isSunk());
    }
}
