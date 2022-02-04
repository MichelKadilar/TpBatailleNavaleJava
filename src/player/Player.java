package player;

import board.Board;
import board.Hit;
import board.ShipStruckAtSamePosError;
import ships.AbstractShip;
import ships.Direction;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Player implements Serializable {
    /* **
     * Attributs
     * Ils sont en visibilité protected car un joueur doit pouvoir prévenir l'adversaire qu'il l'attaque à certaines coordonnées.
     */
    public Board board; // Public pour qu'il soit accessible dans la classe Game qui n'est pas dans le même package
    protected Board opponentBoard;
    public int destroyedCount;
    protected AbstractShip[] ships;
    public boolean lose; // Public pour qu'il soit accessible dans la classe Game

    /* **
     * Constructeur
     */
    public Player(Board board, Board opponentBoard, List<AbstractShip> ships) {
        this.board = board;
        this.ships = ships.toArray(new AbstractShip[0]);
        this.opponentBoard = opponentBoard;
    }

    /* **
     * Méthodes
     */

    /**
     * Read keyboard input to get ships coordinates. Place ships on given coodrinates.
     */
    public void putShips() {
        boolean done;
        int i = 0;

        do {
            int inputOk = 0;
            AbstractShip s = ships[i];
            String msg = String.format("placer %d : %s(%d)", i + 1, s.getShipName(), s.getLongueurShip());
            System.out.println(msg);
            InputHelper.ShipInput res;
            // TODO set ship orientation
            // TODO put ship at given position
            while (inputOk != 1) { // Tant qu'on n'arrive pas à placer ce navire, on redemande au joueur de placer ce navire
                try {
                    res = InputHelper.readShipInput();
                    s.setOrientation(Direction.orientationStringToDirection(res.orientation));
                    this.board.putShip(s, res.x, res.y);
                    inputOk = 1;
                } catch (Exception e) {
                    System.out.println("Erreur dans les coordonnées du navire : " + s.getShipName());
                }
            }
            // TODO when ship placement successful
            ++i;
            done = i == 5;

            this.board.print();
        } while (!done);
    }

    public Hit sendHit(int[] coord) throws ShipStruckAtSamePosError {
        boolean done;
        Hit hit = null;
        do {
            System.out.println("où frapper?");
            InputHelper.CoordInput hitInput = InputHelper.readCoordInput();
            // TODO call sendHit on this.opponentBoard
            hit = this.opponentBoard.sendHit(hitInput.x, hitInput.y);
            // TODO : Game expects sendHit to return BOTH hit result & hit coords.
            coord[0] = hitInput.x;
            coord[1] = hitInput.y;
            done = hit != null;
            // return hit is obvious. But how to return coords at the same time ?
        } while (!done);


        return hit;
    }

    public AbstractShip[] getShips() {
        return ships;
    }

    public void setShips(AbstractShip[] ships) {
        this.ships = ships;
    }
}
