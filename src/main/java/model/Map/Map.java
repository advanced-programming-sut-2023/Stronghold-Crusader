package model.Map;

import model.MapAsset.MapAsset;
import model.MapAsset.MobileUnit.MobileUnit;
import model.enums.AssetType.MapAssetType;
import model.enums.CellType;
import utils.Vector2D;

import java.util.*;


public class Map {
    private final String name;
    private final Vector2D size;
    private final Vector<Vector2D> headQuarters;
    private final Vector<Vector2D> storeHouses;
    private int playerCount;
    private Cell[][] map;

    public Map(Vector2D size, String name) {
        this.name = name;
        this.size = size;
        initializeMap();
        playerCount = 0;
        headQuarters = new Vector<>();
        storeHouses = new Vector<>();
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
        if (obj.getType() == MapAssetType.HEADQUARTER)
            headQuarters.add(obj.getCoordinate());
        if (obj.getType() == MapAssetType.STORE_HOUSE)
            storeHouses.add(obj.getCoordinate());
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

    public List<Vector2D> getTraversePath(MobileUnit currentUnit, Vector2D destination) {
        java.util.Map<Vector2D, Integer> distances = new HashMap<>();
        java.util.Map<Vector2D, Vector2D> parents = new HashMap<>();

        PriorityQueue<Vector2D> queue = new PriorityQueue<>((n1, n2) -> distances.get(n1) - distances.get(n2));
        distances.put(currentUnit.getCoordinate(), 0);
        queue.offer(currentUnit.getCoordinate());
        while (!queue.isEmpty()) {
            Vector2D current = queue.poll();
            if (current == destination) {
                break;
            }
            ArrayList<Cell> neighbors = getNearbyCells(current, 1);
            neighbors.remove(this.getCell(current));

            for (Cell neighbor : neighbors) {
                if (!isTraversable(this.getCell(current), neighbor)) break;
                int distance = distances.get(current) + 1;
                if ((!distances.containsKey(neighbor.getCoordinate()) || distance < distances.get(neighbor.getCoordinate()))
                ) {
                    distances.put(neighbor.getCoordinate(), distance);
                    parents.put(neighbor.getCoordinate(), current);
                    queue.offer(neighbor.getCoordinate());
                }
            }
        }

        List<Vector2D> path = new ArrayList<>();
        Vector2D current = destination;

        while (current != null) {
            path.add(current);
            current = parents.get(current);
        }

        Collections.reverse(path);

        return path;
    }

    private boolean isTraversable(Cell current, Cell destination) {
        if (current.hasWall()) return destination.isTraversableInWall();
        else if (current.hasGateHouse()) return destination.isTraversableInGateHouse();
        return destination.isTraversable();
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

    public Vector2D getStoreHouseCoordinate(int index) {
        return storeHouses.get(index);
    }

    public Vector2D getHeadQuarterCoordinate(int index) {
        return headQuarters.get(index);
    }

    public boolean isInMap(Vector2D coordinate) {
        return coordinate.x >= 0 && coordinate.y >= 0 && coordinate.y < size.y && coordinate.x < size.x;
    }
}
