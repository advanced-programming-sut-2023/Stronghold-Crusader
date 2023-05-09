package model.enums;

public enum Direction {
    SOUTH("s"),
    NORTH("n"),
    EAST("e"),
    WEST("w"),
    RANDOM("r");
    private final String name;

    Direction(String name) {
        this.name = name;
    }

    public static Direction getDirection(String direction) {
        for (Direction d : Direction.values()) {
            if (d.name.equals(direction)) return d;
        }
        return null;
    }

    public String getName() {
        return name;
    }
}
