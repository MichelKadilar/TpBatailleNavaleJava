package ships;

public abstract class AbstractShip {
    private char label;
    private String shipName;
    private int longueurShip;
    private Direction orientation;

    protected AbstractShip(String shipName, char label, int longueurShip, Direction orientation) {
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
}
