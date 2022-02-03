package board;

import ships.Battleship;
import ships.Destroyer;
import ships.Direction;
import ships.ShipState;

public class TestBoard {

    public static void main(String[] args) throws ShipPosOutOfBoardError, ShipSuperpositionError, ShipStruckAtSamePosError {
        Board board = new Board("Bataille Navale");
        System.out.println(board.getSize());
        ShipState s = new ShipState(new Destroyer(), false);
        ShipState s2 = new ShipState(new Battleship(Direction.WEST), false);
        // Il faut faire un try catch pour putship ici, mais je ne sais pas encore quoi faire dans le catch
        board.putShip(s.getShip(), 4, 4);
        board.putShip(s2.getShip(), 8, 8);
        System.out.println(board.hasShip(4 - 1, 4 - 1)); // le destroyer : "s.getShip()"
        System.out.println(board.hasShip(4, 4 - 1)); // le destroyer : "s.getShip()"
        TestBoard.printIfHitSunkAShip(board.sendHit(4, 4), s.isSunk());
        TestBoard.printIfHitSunkAShip(board.sendHit(4, 4), s.isSunk());
        TestBoard.printIfHitSunkAShip(board.sendHit(5, 4), s.isSunk());
        System.out.println(board.hasShip(4, 4));
        //board.sendHit(4, 4);
        board.sendHit(6, 4);
        board.print();
        System.out.println(s.isSunk());
        //System.out.println(hit.toString());
    }

    private static void printIfHitSunkAShip(Hit sendHit, boolean isSunk) {
        if (isSunk) {
            System.out.println(sendHit.toString() + " est coulé");
        }
    }
}
