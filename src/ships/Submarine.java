package ships;

public class Submarine extends AbstractShip {

    public Submarine(){
        super("Submarine", 'S', 3, Direction.EAST);
    }

    public Submarine(Direction orientation) {
        super("Submarine", 'S', 3, orientation);
    }
}
