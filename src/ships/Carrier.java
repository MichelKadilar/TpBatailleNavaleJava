package ships;

public class Carrier extends AbstractShip {

    public Carrier() {
        super("Carrier", 'C', 5, Direction.EAST);
    }

    public Carrier(Direction orientation) {
        super("Carrier", 'C', 5, orientation);
    }
}
