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

    public void addStrike() {
        this.struck = true;
        try {
            this.abstractShip.addStrike();
        } catch (ShipStruckTooManyTimesError e) {
            System.out.println("Navire touché trop de fois : " + this.abstractShip.getShipName());
        }
    }

    public boolean isStruck() {
        return this.struck;
    }

    public String toString() {
        if (isStruck()) {
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
