package ships;

public class Destroyer extends AbstractShip {

    public Destroyer() {
        super("Destroyer", 'D', 2, Direction.EAST);
    }

    public Destroyer(Direction orientation) {
        super("Destroyer", 'D', 2, orientation);
    }

}
