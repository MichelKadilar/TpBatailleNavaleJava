package ships;

public class Battleship extends AbstractShip {

    public Battleship() {
        super("Battleship", 'B', 4, Direction.EAST);
    }

    public Battleship(Direction orientation) {
        super("Battleship", 'B', 4, orientation);
    }
}
