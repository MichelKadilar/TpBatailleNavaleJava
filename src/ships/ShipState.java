package ships;

import board.ShipStruckTooManyTimesError;
import color.ColorUtil;

public class ShipState {

    private AbstractShip abstractShip;
    private boolean struck;

    public ShipState(AbstractShip abstractShip, boolean struck) {
        this.abstractShip = abstractShip;
        this.struck = struck;
    }

    public void addStrike() { // On ajoute un tir effectué sur un bateau
        this.struck = true; // Si on a tiré une fois sur un bateau, il est touché
        try {
            this.abstractShip.addStrike(); // On ajoute un tir effectué sur un navire
        } catch (ShipStruckTooManyTimesError e) {
            System.out.println("Navire touché trop de fois : " + this.abstractShip.getShipName());
        }
    }

    public boolean isStruck() {
        return this.struck;
    }

    public String toString() { // On affiche le label d'un navire en rouge s'il est touché à tel endroit, sinon on l'affiche en blanc.
        if (isStruck()) { // Le navire est-il touché ?
            return ColorUtil.colorize(this.abstractShip.getLabel(), ColorUtil.Color.RED);
        } else {
            return String.valueOf(this.abstractShip.getLabel());
        }
    }

    public boolean isSunk() {
        return this.abstractShip.isSunk();
    }

    public AbstractShip getShip() {
        return this.abstractShip;
    }
}
