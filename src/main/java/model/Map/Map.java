package model.Map;

import model.MapAsset.MapAsset;
import model.MapAsset.MobileUnit.MobileUnit;
import model.enums.CellType;
import utils.Vector2D;

import java.util.ArrayList;
import java.util.Collections;

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

    public void setPlayerCount(int playerCount) {
        this.playerCount = playerCount;
    }

    public Vector2D getSize() {
        return size;
    }

    public void addMapObject(Vector2D coordinate, MapAsset obj) {
        getCell(coordinate).addMapAsset(obj);
    }

    public void removeMapObject(Vector2D coordinate, MapAsset obj) {
        getCell(coordinate).removeMapAsset(obj);
    }

    public void moveMapObject(Vector2D source, Vector2D dest, MapAsset obj) {
        addMapObject(dest, obj);
        removeMapObject(source, obj);
    }

    //only gets called at the end of the game to prepare Map for saving.
    public void removeNonSavableAssets() {
        for (Cell[] cells : map) {
            for (Cell cell : cells) {
                cell.removeNonSavableAssets();
            }
        }
    }

    public ArrayList<Cell> getNearbyCells(Vector2D center, int radius) {
        ArrayList<Cell> nearbyCells = new ArrayList<>();
        nearbyCells.add(getCell(center));
        for (int r = 1; r < radius; r++) {
            for (int j = -r; j <= r; j++) {
                Vector2D cellCoord = new Vector2D(center.x + j, center.y + r);
                if (isInMap(cellCoord))
                    nearbyCells.add(getCell(cellCoord));
                cellCoord = new Vector2D(center.x + j, center.y - r);
                if (isInMap(cellCoord))
                    nearbyCells.add(getCell(cellCoord));
            }
            for (int j = -r + 1; j <= r - 1; j++) {
                Vector2D cellCoord = new Vector2D(center.x + r, center.y + j);
                if (isInMap(cellCoord))
                    nearbyCells.add(getCell(cellCoord));
                cellCoord = new Vector2D(center.x - r, center.y + j);
                if (isInMap(cellCoord))
                    nearbyCells.add(getCell(cellCoord));
            }
        }
        return nearbyCells;
    }

    public ArrayList<Vector2D> getTraversePath(MobileUnit currentUnit, Vector2D destination) {
        //TODO change the logic of searching
        int numberOfVertices = map.length;
        int[] dist = new int[numberOfVertices];
        int[] prev = new int[numberOfVertices];
        boolean[] visited = new boolean[numberOfVertices];
        for (int i = 0; i < numberOfVertices; i++) {
            dist[i] = Integer.MAX_VALUE;
            prev[i] = -1;
            visited[i] = false;
        }
        dist[Vector2D.translateVector2DToInt(currentUnit.getCoordinate(), size.x)] = 0;
        for (int i = 0; i < numberOfVertices - 1; i++) {
            int minDist = Integer.MAX_VALUE;
            int minIndex = -1;
            for (int j = 0; j < numberOfVertices; j++) {
                if (!visited[j] && dist[j] < minDist) {
                    minDist = dist[j];
                    minIndex = j;
                }
            }
            visited[minIndex] = true;
            if (minIndex == Vector2D.translateVector2DToInt(destination, size.x)) {
                break;
            }
            for (int j = 0; j < numberOfVertices; j++) {
                Vector2D v2 = Vector2D.translateIntToVector2D(j, size.x);
                Cell cell = getCell(v2);
                if (!visited[j] && dist[minIndex] != Integer.MAX_VALUE && cell.isTraversable(currentUnit)) {
                    dist[j] = dist[minIndex] + 1;
                    prev[j] = minIndex;
                }
            }
        }
        ArrayList<Vector2D> path = new ArrayList<>();
        int curr = Vector2D.translateVector2DToInt(destination, size.x);
        while (curr != -1) {
            path.add(Vector2D.translateIntToVector2D(curr, size.x));
            curr = prev[curr];
        }
        Collections.reverse(path);
        System.out.println("Shortest path from " + currentUnit.getCoordinate() + " to " + destination + ": " + path);
        return path;
    }


    public void changeCellTypeTo(Vector2D coordinate, CellType type) {
        map[coordinate.y][coordinate.x].setType(type);
    }

    public void clearCell(Vector2D coordinate) {
        map[coordinate.y][coordinate.x].clear();
    }

    public Cell getCell(Vector2D coordinate) {
        return map[coordinate.y][coordinate.x];
    }

    public boolean isInMap(Vector2D coordinate) {
        return coordinate.x >= 0 && coordinate.y >= 0 && coordinate.y < size.y && coordinate.x < size.x;
    }
}
