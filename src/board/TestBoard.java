package board;

import player.Player;
import ships.Battleship;
import ships.Destroyer;
import ships.Direction;
import ships.ShipState;

public class TestBoard {

    public static void main(String[] args) throws ShipPosOutOfBoardError, ShipSuperpositionError, ShipStruckAtSamePosError {
        Board board = new Board("Bataille Navale");
        Board board2 = new Board("Bataille Navale2");
        //System.out.println(board.getSize());
        ShipState s = new ShipState(new Destroyer(), false);
        ShipState s2 = new ShipState(new Battleship(Direction.WEST), false);
        // Il faut faire un try catch pour putship ici, mais je ne sais pas encore quoi faire dans le catch
        //s.getShip().setOrientation(Direction.NORTH);
        board.putShip(s.getShip(), 1 - 1, 1 - 1);
        board.putShip(s2.getShip(), 9 - 1, 9 - 1);
        //System.out.println(board.hasShip(1 - 1, 1 - 1)); // le destroyer : "s.getShip()"
        //System.out.println(board.hasShip(5 - 1, 4 - 1)); // le destroyer : "s.getShip()"
        Hit h;
        TestBoard.printIfHitSunkAShip(h = (board.sendHit(0, 0)), s.isSunk());
        try {
            board.setHit(h != Hit.MISS, 0, 0);
        } catch (ShipStruckAtSamePosError e) {
            System.out.println("Votre tir aux coordonnées x = " + 0 + " et y = " + 0 + " n'est pas valide car vous l'avez déjà réalisé auparavant");
        }
        TestBoard.printIfHitSunkAShip(h = (board.sendHit(1, 0)), s.isSunk());
        try {
            board.setHit(h != Hit.MISS, 1, 0);
        } catch (ShipStruckAtSamePosError e) {
            System.out.println("Votre tir aux coordonnées x = " + 1 + " et y = " + 0 + " n'est pas valide car vous l'avez déjà réalisé auparavant");
        }
        //System.out.println(h);
        /*
        TestBoard.printIfHitSunkAShip(h = (board.sendHit(4 - 1, 4 - 1)), s.isSunk());
        try {
            board.setHit(h != Hit.MISS, 4 - 1, 4 - 1);
        } catch (ShipStruckAtSamePosError e) {
            System.out.println("Votre tir aux coordonnées x = " + 3 + " et y = " + 3 + " n'est pas valide car vous l'avez déjà réalisé auparavant");
        }
        TestBoard.printIfHitSunkAShip(h = (board.sendHit(4, 3)), s.isSunk());
        try {
            board.setHit(h != Hit.MISS, 4, 3);
        } catch (ShipStruckAtSamePosError e) {
            System.out.println("Votre tir aux coordonnées x = " + 4 + " et y = " + 3 + " n'est pas valide car vous l'avez déjà réalisé auparavant");
        }
        TestBoard.printIfHitSunkAShip(h = (board.sendHit(5, 4)), s.isSunk());
        try {
            board.setHit(h != Hit.MISS, 5, 4);
        } catch (ShipStruckAtSamePosError e) {
            System.out.println("Votre tir aux coordonnées x = " + 5 + " et y = " + 4 + " n'est pas valide car vous l'avez déjà réalisé auparavant");
        }
        //System.out.println(board.hasShip(4, 4));
        TestBoard.printIfHitSunkAShip(h = (board.sendHit(6, 4)), s.isSunk());
        try {
            board.setHit(h != Hit.MISS, 6, 4);
        } catch (ShipStruckAtSamePosError e) {
            System.out.println("Votre tir aux coordonnées x = " + 6 + " et y = " + 4 + " n'est pas valide car vous l'avez déjà réalisé auparavant");
        }

         */

        board.print();
        System.out.println(s.getShip().getShipName() + " est coulé ?" + s.isSunk());
        //System.out.println(hit.toString());
    }

    private static void printIfHitSunkAShip(Hit sendHit, boolean isSunk) {
        if (!sendHit.toString().equals("touché") && !sendHit.toString().equals("manqué") && isSunk) {
            System.out.println(sendHit.toString() + " est coulé");
        }
    }
}
