package model;

import utils.Vector2D;

import java.util.Collection;
import java.util.HashMap;

public class Cell {
    private final Vector2D coordinate;
    private CellType type;
    private HashMap<Integer, MapAsset> objects;

    public Cell(Vector2D coordinate, CellType type) {
        this.coordinate = coordinate;
        this.type = type;
    }

    public Collection<MapAsset> getAllObjects() {
        return null;
    }

    public Collection<MapAsset> getPlayersObjects(Player player) {
        return null;
    }

    public CellType getType() {
        return type;
    }

    public void setType(CellType type) {
        this.type = type;
    }

    public Vector2D getCoordinate() {
        return coordinate;
    }

    public boolean isTraversable(MobileUnit unit) {
        return false;
    }
}
