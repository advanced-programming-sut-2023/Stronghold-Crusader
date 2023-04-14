package model;

import utils.Vector2D;

import java.util.ArrayList;
import java.util.Collection;

public class Map {
    private Cell[][] map;
    private Vector2D size;
    private int playerCount;

    public Map(String fileName) {
        // initialize map
        // load file
    }

    private void loadMap(String fileName) {
    }

    public void addMapObject() {
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
}
