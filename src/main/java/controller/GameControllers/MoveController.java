package controller.GameControllers;

import model.Map.Cell;
import model.MapAsset.MobileUnit.MobileUnit;
import utils.Vector2D;

import java.util.*;

public class MoveController {
    private final model.Map.Map map;

    public MoveController(model.Map.Map map) {
        this.map = map;
    }

    public LinkedList<Vector2D> findShortestPath(MobileUnit mobileUnit, Vector2D start, Vector2D goal) {
        LinkedList<Vector2D> openSet = new LinkedList<>();
        Set<Vector2D> closedSet = new HashSet<>();
        Map<Vector2D, Vector2D> cameFrom = new HashMap<>();
        Map<Vector2D, Double> gScore = new HashMap<>();
        Map<Vector2D, Double> fScore = new HashMap<>();

        gScore.put(start, 0.0);
        fScore.put(start, heuristic(start, goal));
        openSet.add(start);

        while (!openSet.isEmpty()) {
            Vector2D current = openSet.poll();
            if (current.equals(goal))
                return reconstructPath(cameFrom, current);
            closedSet.add(current);
            for (Cell neighbor : map.getNeighbors(current)) {
                if (!map.isTraversable(mobileUnit, map.getCell(current), neighbor)) continue;
                if (closedSet.contains(neighbor.getCoordinate())) {
                    continue;
                }
                double tentativeGScore = gScore.get(current) + distance(current, neighbor.getCoordinate());
                if (!openSet.contains(neighbor.getCoordinate()) || tentativeGScore < gScore.get(neighbor.getCoordinate())) {
                    cameFrom.put(neighbor.getCoordinate(), current);
                    gScore.put(neighbor.getCoordinate(), tentativeGScore);
                    fScore.put(neighbor.getCoordinate(), tentativeGScore + heuristic(neighbor.getCoordinate(), goal));
                    if (!openSet.contains(neighbor.getCoordinate())) {
                        openSet.add(neighbor.getCoordinate());
                    }
                }
            }
        }
        return new LinkedList<>(); // no path found
    }

    private LinkedList<Vector2D> reconstructPath(Map<Vector2D, Vector2D> cameFrom, Vector2D current) {
        LinkedList<Vector2D> path = new LinkedList<>();
        path.add(current);
        while (cameFrom.containsKey(current)) {
            current = cameFrom.get(current);
            path.add(0, current);
        }
        return path;
    }

    private double heuristic(Vector2D a, Vector2D b) {
        double dx = a.x - b.x;
        double dy = a.y - b.y;
        return Math.sqrt(dx * dx + dy * dy);
    }

    private double distance(Vector2D a, Vector2D b) {
        return (Math.sqrt((a.x - b.x) ^ 2 + (a.y - b.y) ^ 2));
    }

}
