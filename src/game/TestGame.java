package game;

import board.*;
import player.BattleShipsAI;
import ships.*;

public class TestGame {

    public static void main(String[] args) throws ShipPosOutOfBoardError, ShipSuperpositionError, ShipStruckAtSamePosError {
        int compteurNavireDetruits = 0;
        Board board = new Board("Mon board");
        AbstractShip[] ships = {new Destroyer(), new Submarine(), new Submarine(), new Battleship(), new Carrier()};
        BattleShipsAI ai = new BattleShipsAI(board, board);
        ai.putShips(ships);
        int[] tab = new int[2];
        Hit hit;
        board.print();
        while (compteurNavireDetruits < ships.length) {
            hit = ai.sendHit(tab);
            if (hit.toString().equals("touché")) {
                System.out.println(hit + " aux coordonnées : x = " + (tab[0] + 1) + " et y = " + (tab[1] + 1));
            } else if (hit.toString().equals("manqué")) {
                System.out.println(hit + " aux coordonnées : x = " + (tab[0] + 1) + " et y = " + (tab[1] + 1));
            } else {
                System.out.println("Touché aux coordonnées : x = " + (tab[0] + 1) + " et y = " + (tab[1] + 1));
                System.out.println(hit + " coulé");
                compteurNavireDetruits++;
            }
            board.print();
            System.out.println();
        }
        System.out.println("Tous les navires ont été coulés");

    }
}
