package model.Map;

import model.MapAsset.MobileUnit.MobileUnit;
import model.MapAsset.MapAsset;
import model.Player;
import model.enums.CellType;
import utils.Vector2D;

import java.util.ArrayList;
import java.util.Collection;

public class Map {
    private final String name;
    private final Vector2D size;
    private int playerCount;
    private Cell[][] map;

    public Map(Vector2D size, String name) {
        this.name = name;
        this.size = size;
        initializeMap();
    }

    private void initializeMap() {
        map = new Cell[size.y][size.x];
        for (int y = 0; y < size.y; y++) {
            map[y] = new Cell[size.x];
            for (int x = 0; x < size.x; x++) {
                map[y][x] = new Cell(new Vector2D(x, y), CellType.FIELD);
            }
        }
    }

    public String getName() {
        return name;
    }

    public int getPlayerCount() {
        return playerCount;
    }

    public void addMapObject(Vector2D coordinate) {
    }

    public void removeMapObject() {
    }

    //only gets called at the end of the game to prepare Map for saving.
    public void removeNonSavableAssets() {
        for (Cell[] cells : map) {
            for (Cell cell : cells) {
                cell.removeNonSavableAssets();
            }
        }
    }

    public Collection<MapAsset> getObjectsAt(Vector2D coordinate) {
        return null;
    }

    public Collection<MapAsset> getObjectsAt(Vector2D coordinate, Player player) {
        return null;
    }

    public int getTraverseDistance(MobileUnit unit, Vector2D dest) {
        return 0;
    }

    public ArrayList<Cell> getTraversePath(MobileUnit unit, Vector2D dest) {
        return null;
    }

    public void changeCellTypeTo(int x, int y, CellType type) {
        map[y][x].setType(type);
    }
}
