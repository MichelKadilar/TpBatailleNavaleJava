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

    public static Direction orientationStringToDirection(String orientation) {
        if (orientation == null || orientation.equals("e")) {
            return EAST;
        } else if (orientation.equals("w")) {
            return WEST;
        } else if (orientation.equals("n")) {
            return NORTH;
        } else if (orientation.equals("s")) {
            return SOUTH;
        } else {
            return EAST;
        }
    }
}
