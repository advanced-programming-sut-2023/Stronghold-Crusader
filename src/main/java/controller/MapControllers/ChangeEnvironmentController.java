package controller.MapControllers;

import model.ConstantManager;
import model.Game.Game;
import model.Map.Map;
import model.MapAsset.Cliff;
import model.MapAsset.MapAsset;
import model.MapAsset.Tree;
import model.User.Player;
import model.enums.*;
import model.enums.AssetType.MapAssetType;
import utils.Vector2D;
import view.MapMenus.ChangeEnvironmentMenu;
import view.enums.messages.MapMessage.MapMakerMessage;

public class ChangeEnvironmentController {
    private Map map;
    private Game game;

    public ChangeEnvironmentController(Map map, Game game) {
        this.map = map;
    }

    public void run() {
        ChangeEnvironmentMenu menu = new ChangeEnvironmentMenu(this);
        while (true) {
            if (menu.run().equals("back")) return;
        }
    }

    public MapMakerMessage setTexture(int x, int y, String textureName) {
        CellType texture = CellType.getType(textureName);
        Vector2D coordinate = new Vector2D(x, y);

        if (texture == null) return MapMakerMessage.INVALID_TEXTURE_TYPE;
        if (!map.isInMap(coordinate)) return MapMakerMessage.INVALID_COORDINATE;

        map.changeCellTypeTo(coordinate, texture);
        return MapMakerMessage.SET_TEXTURE_SUCCESS;
    }


    public MapMakerMessage setTexture(int x1, int y1, int x2, int y2, String textureName) {
        CellType texture = CellType.getType(textureName);
        if (texture == null) return MapMakerMessage.INVALID_TEXTURE_TYPE;
        if (!map.isInMap(new Vector2D(x1, y1))) return MapMakerMessage.INVALID_COORDINATE;
        if (!map.isInMap(new Vector2D(x2, y2))) return MapMakerMessage.INVALID_COORDINATE;

        for (int i = x1; i <= x2; i++) {
            for (int j = y1; j <= y2; j++) {
                map.changeCellTypeTo(new Vector2D(i, j), texture);
            }
        }
        return MapMakerMessage.SET_TEXTURE_SUCCESS;
    }


    public MapMakerMessage clearCell(int x, int y) {
        Vector2D coordinate = new Vector2D(x, y);
        if (!map.isInMap(coordinate)) return MapMakerMessage.INVALID_COORDINATE;
        for (MapAsset asset : map.getCell(coordinate).getAllAssets()) {
            for (Player player : game.getPlayers()) {
                player.getGovernance().removeAsset(asset);
            }
        }
        map.getCell(coordinate).setType(CellType.PlAIN);
        return MapMakerMessage.CLEAR_CELL_SUCCESS;
    }


    public MapMakerMessage dropRock(int x, int y, String directionName) {
        Vector2D coordinate = new Vector2D(x, y);
        Direction cliffDirection = Direction.getDirection(directionName);

        if (!map.isInMap(coordinate)) return MapMakerMessage.INVALID_COORDINATE;
        if (cliffDirection == null) return MapMakerMessage.INVALID_DIRECTION;
        if (!map.getCell(coordinate).isEmpty()) return MapMakerMessage.NOT_EMPTY;
        Cliff cliff = new Cliff((Cliff) ConstantManager.getInstance().getAsset(MapAssetType.CLIFF),
                coordinate, null, cliffDirection);
        map.addMapObject(coordinate, cliff);
        return MapMakerMessage.DROP_ROCK_SUCCESS;
    }

    public MapMakerMessage dropTree(int x, int y, String typeName) {
        Vector2D coordinate = new Vector2D(x, y);
        TreeType type = TreeType.getType(typeName);
        if (type == null) return MapMakerMessage.INVALID_TREE_TYPE;
        if (!map.isInMap(coordinate)) return MapMakerMessage.INVALID_COORDINATE;
        if (!map.getCell(coordinate).isEmpty()) return MapMakerMessage.NOT_EMPTY;
        if (!(map.getCell(coordinate).getType().equals(CellType.GRASS) ||
                map.getCell(coordinate).getType().equals(CellType.PlAIN))) return MapMakerMessage.INVALID_CELL_TYPE;
        Tree tree = new Tree((Tree) ConstantManager.getInstance().getAsset(MapAssetType.TREE),
                coordinate, null, type);
        map.addMapObject(coordinate, tree);
        return MapMakerMessage.DROP_TREE_SUCCESS;
    }

}
