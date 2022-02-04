package ships;

import board.ShipStruckTooManyTimesError;

import java.io.Serializable;

public abstract class AbstractShip implements Serializable {
    private char label;
    private String shipName;
    private int longueurShip;
    private Direction orientation;
    private int strikeCount;

    protected AbstractShip(String shipName, char label, int longueurShip, Direction orientation) {
        // Le constructeur est protected car il ne sera utilisé qu'à partir des constructeurs des classes filles qui se trouvent dans le même package.
        this.shipName = shipName;
        this.label = label;
        this.longueurShip = longueurShip;
        this.orientation = orientation;
    }

    public char getLabel() {
        return this.label;
    }

    public String getShipName() {
        return this.shipName;
    }

    public int getLongueurShip() {
        return this.longueurShip;
    }

    public Direction getOrientation() {
        return this.orientation;
    }

    public void setOrientation(Direction orientation) {
        this.orientation = orientation;
    }

    public void addStrike() throws ShipStruckTooManyTimesError { // On incrémente un compteur de tir sur un navire
        if (!isSunk()) { // Si le navire n'a pas coulé
            this.strikeCount++; // Tir supplémentaire effectué sur le navire
        } else {
            throw new ShipStruckTooManyTimesError("Le navire : " + this.getShipName() + " a été touché plus que ne le permet sa longueur");
        }
    }

    public boolean isSunk() { // Si le navire est touché autant qu'il est long, alors il est entièrement touché, donc coulé.
        return this.strikeCount == this.longueurShip; // Renvoie vrai si le navire est coulé.
    }
}
