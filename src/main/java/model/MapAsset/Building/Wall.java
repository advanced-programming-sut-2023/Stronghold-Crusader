package model.MapAsset.Building;

import model.User.Player;
import model.enums.AssetType.MapAssetType;
import model.enums.AssetType.Material;
import model.enums.CellType;
import model.enums.Direction;
import utils.Vector2D;

import java.util.ArrayList;
import java.util.HashMap;

public class Wall extends Building {
    private final HashMap<Direction, Boolean> ladderState;

    public Wall(Wall reference, Vector2D coordinate, Player owner) {
        super(reference, coordinate, owner);
        ladderState = new HashMap<>();
        ladderState.put(Direction.NORTH, false);
        ladderState.put(Direction.SOUTH, false);
        ladderState.put(Direction.EAST, false);
        ladderState.put(Direction.WEST, false);
    }

    public Wall(double maxHitPoint, MapAssetType type, int cost, int populationCapacity, Material neededMaterial, int workerCount, ArrayList<CellType> buildingGroundType, int neededMaterialAmount) {
        super(maxHitPoint, type, cost, populationCapacity, neededMaterial, workerCount, buildingGroundType, neededMaterialAmount);
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
}
