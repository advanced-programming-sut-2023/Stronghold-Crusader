package model.Map;

import model.MapAsset.MobileUnit.MobileUnit;
import model.MapAsset.MapAsset;
import model.Player;
import model.enums.CellType;
import utils.Vector2D;

import java.util.ArrayList;
import java.util.Collection;

public class Map {
    private Cell[][] map;
    private Vector2D size;
    private int playerCount;

    public Map(String fileName, Vector2D size) {
        this.size = size;
        // initialize map
    }

    public void addMapObject(Vector2D coordinate) {
    }

    public void removeMapObject() {
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
    public void changeCellTypeTo(int x, int y, CellType type){
        map[y][x].setType(type);
    }
}
