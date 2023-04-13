package model;

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

    private void loadMap(String fileName){
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

    public Collection<Person> getPeopleAt(Vector2D coordinate) {
        return null;
    }

    public Collection<Person> getPeopleAt(Vector2D coordinate, Player player) {
        return null;
    }

    public int getTraverseDistance(Person person, Vector2D dest) {
        return 0;
    }

    public ArrayList<Cell> getTraversePath(Person person, Vector2D dest) {
        return null;
    }
}
