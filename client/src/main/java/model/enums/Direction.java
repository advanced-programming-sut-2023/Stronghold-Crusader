package model.enums;

import java.util.Random;

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
        if (direction.equals("r")) {
            Direction[] directions = {SOUTH, NORTH, EAST, WEST};
            int randomInt = new Random().nextInt(4);
            return directions[randomInt];
        }
        for (Direction d : Direction.values()) {
            if (d.name.equals(direction)) return d;
        }
        return null;
    }

    public String getName() {
        return name;
    }
}
