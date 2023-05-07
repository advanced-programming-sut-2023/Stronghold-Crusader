package controller.GameControllers;

import model.Game.Game;
import model.Map.Cell;
import model.MapAsset.MobileUnit.MobileUnit;
import utils.Vector2D;

import java.util.ArrayList;
import java.util.Collections;

public class SelectedUnitController {
    private final Game game;
    private final MobileUnit currentUnit;

    public SelectedUnitController(MobileUnit currentUnit, Game game) {
        this.game = game;
        this.currentUnit = currentUnit;
    }

    public ArrayList<Vector2D> findPath(Vector2D destination) {
        int size = game.getMap().getSize().x;
        int numberOfVertices = game.getMap().getMap().length;
        int[] dist = new int[numberOfVertices];
        int[] prev = new int[numberOfVertices];
        boolean[] visited = new boolean[numberOfVertices];
        for (int i = 0; i < numberOfVertices; i++) {
            dist[i] = Integer.MAX_VALUE;
            prev[i] = -1;
            visited[i] = false;
        }
        dist[Vector2D.translateVector2DToInt(currentUnit.getCoordinate(), size)] = 0;
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
            if (minIndex == Vector2D.translateVector2DToInt(destination, size)) {
                break;
            }
            for (int j = 0; j < numberOfVertices; j++) {
                Vector2D v2 = Vector2D.translateIntToVector2D(j, size);
                Cell cell = game.getMap().getCell(v2);
                if (!visited[j] && dist[minIndex] != Integer.MAX_VALUE && dist[minIndex] + cell.getTravelWorth(currentUnit) < dist[j]) {
                    dist[j] = dist[minIndex] + cell.getTravelWorth(currentUnit);
                    prev[j] = minIndex;
                }
            }
        }
        ArrayList<Vector2D> path = new ArrayList<>();
        int curr = Vector2D.translateVector2DToInt(destination, size);
        while (curr != -1) {
            path.add(Vector2D.translateIntToVector2D(curr, size));
            curr = prev[curr];
        }
        Collections.reverse(path);
        System.out.println("Shortest path from " + currentUnit.getCoordinate() + " to " + destination + ": " + path);
        return path;
    }

}
