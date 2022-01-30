package ships;

public enum Direction {
    WEST(0), NORTH(1), EAST(2), SOUTH(3);

    private final int orientation;

    Direction(int orientation) {
        this.orientation = orientation;
    }

    public int getOrientation() {
        return this.orientation;
    }
}
