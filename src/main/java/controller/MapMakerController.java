package controller;

import model.ConstantManager;
import model.Map.Map;
import model.Map.MapLoader;
import model.MapAsset.Cliff;
import model.MapAsset.MapAsset;
import model.MapAsset.Tree;
import model.User;
import model.enums.CellType;
import model.enums.Color;
import model.enums.CliffDirection;
import model.enums.TreeType;
import utils.Vector2D;
import view.MapMakingMenus.AssetPlacementMenu;
import view.MapMakingMenus.ChangeEnvironmentMenu;
import view.MapMakingMenus.MapMakerMenu;
import view.enums.messages.MapMakerMessage;

public class MapMakerController {
    private User currentUser;
    private Map map;
    private MapMakerMenu mapMakerMenu;

    public MapMakerController(User currentUser, Map map) {
        this.currentUser = currentUser;
        this.map = map;
        mapMakerMenu = new MapMakerMenu(this);
    }

    public void run() {
        while (true) {
            switch (mapMakerMenu.run()) {
                case "back":
                    MapLoader.addMap(map);
                    return;
                case "environment":
                    runChangeEnvironment();
                    break;
                case "asset":
                    runAssetPlacement();
                    break;
            }
        }

    }

    private void runAssetPlacement() {
        AssetPlacementMenu menu = new AssetPlacementMenu(this);
        while (true) {
            if (menu.run().equals("back")) return;
        }
    }

    private void runChangeEnvironment() {
        ChangeEnvironmentMenu menu = new ChangeEnvironmentMenu(this);
        while (true) {
            if (menu.run().equals("back")) return;
        }
    }


    // TODO : complete the functions : diba
    // TODO : test for this section : diba

    //errors for this section : invalid coordinates/existing building beforehand
    public MapMakerMessage setTexture(int x, int y, String textureName) {
        CellType texture = CellType.getType(textureName);
        Vector2D coordinate = new Vector2D(x, y);

        if (texture == null) return MapMakerMessage.INVALID_TEXTURE_TYPE;
        if (!map.isCoordinateValid(coordinate)) return MapMakerMessage.INVALID_COORDINATE;

        map.changeCellTypeTo(coordinate, texture);
        return MapMakerMessage.SET_TEXTURE_SUCCESS;
    }


    public MapMakerMessage setTexture(int x1, int y1, int x2, int y2, String textureName) {
        CellType texture = CellType.getType(textureName);
        if (texture == null) return MapMakerMessage.INVALID_TEXTURE_TYPE;
        if (!map.isCoordinateValid(new Vector2D(x1, y1))) return MapMakerMessage.INVALID_COORDINATE;
        if (!map.isCoordinateValid(new Vector2D(x2, y2))) return MapMakerMessage.INVALID_COORDINATE;

        for (int i = x1; i <= x2; i++) {
            for (int j = y1; j <= y2; j++) {
                map.changeCellTypeTo(new Vector2D(i, j), texture);
            }
        }
        return MapMakerMessage.SET_TEXTURE_SUCCESS;
    }


    //errors for this section : invalid coordinates
    public MapMakerMessage clearCell(int x, int y) {
        Vector2D coordinate = new Vector2D(x, y);
        if (!map.isCoordinateValid(coordinate)) return MapMakerMessage.INVALID_COORDINATE;
        map.clearCell(coordinate);
        return MapMakerMessage.CLEAR_CELL_SUCCESS;
    }


    //errors for this section : invalid coordinates/invalid direction/Sth already there
    public MapMakerMessage dropRock(int x, int y, String directionName) {
        Vector2D coordinate = new Vector2D(x, y);
        CliffDirection cliffDirection = CliffDirection.getDirection(directionName);

        if (!map.isCoordinateValid(coordinate)) return MapMakerMessage.INVALID_COORDINATE;
        if (cliffDirection == null) return MapMakerMessage.INVALID_DIRECTION;
        Cliff cliff = new Cliff(ConstantManager.getInstance().getCliff(), coordinate, null, cliffDirection);
        map.addMapObject(coordinate, cliff);
        return MapMakerMessage.DROP_ROCK_SUCCESS;
    }

    //errors for this section : invalid coordinates/invalid type/inappropriate TargetCellType/
    public MapMakerMessage dropTree(int x, int y, String typeName) {
        Vector2D coordinate = new Vector2D(x, y);
        TreeType type = TreeType.getType(typeName);
        if (type == null) return MapMakerMessage.INVALID_TREE_TYPE;
        if (!map.isCoordinateValid(coordinate)) return MapMakerMessage.INVALID_COORDINATE;

        Tree tree = new Tree(ConstantManager.getInstance().getTree(), coordinate, null, type);
        map.addMapObject(coordinate, tree);
        return MapMakerMessage.DROP_TREE_SUCCESS;
    }

    public MapMakerMessage dropHeadquarters(int x, int y, Color color) {
        // increase map player number
        return null;
    }

    //errors for this section : invalid coordinates/invalid type/inappropriate TargetCellType
    public MapMakerMessage dropBuilding(int x, int y, MapAsset type) {
        return null;
    }

    //errors for this section : invalid coordinates/invalid type/inappropriate TargetCellType
    public MapMakerMessage dropUnit(int x, int y, MapAsset type) {
        return null;
    }
}
