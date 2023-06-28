package model.MapAsset.Building;

import model.User.Player;
import model.enums.Direction;
import utils.Vector2D;

import java.util.HashMap;

public class Wall extends Building {
    private final HashMap<Direction, Boolean> ladderState;

    public Wall(Building reference, Vector2D coordinate, Player owner) {
        super(reference, coordinate, owner);
        ladderState = new HashMap<>();
        ladderState.put(Direction.NORTH, false);
        ladderState.put(Direction.SOUTH, false);
        ladderState.put(Direction.EAST, false);
        ladderState.put(Direction.WEST, false);
    }

    public void putLadder(Direction direction) {
        ladderState.put(direction, true);
    }

    public boolean hasLadder(Direction direction) {
        return ladderState.get(direction);
    }

    @Override
    public String toString() {
        return super.toString() +
                "ladderState=" + ladderState;
    }
}
