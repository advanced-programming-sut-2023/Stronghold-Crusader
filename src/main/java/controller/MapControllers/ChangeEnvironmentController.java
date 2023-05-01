package controller.MapControllers;

import model.ConstantManager;
import model.Map.Map;
import model.MapAsset.Building.Headquarters;
import model.MapAsset.Cliff;
import model.MapAsset.Tree;
import model.enums.*;
import utils.Vector2D;
import view.MapMenus.ChangeEnvironmentMenu;
import view.enums.messages.MapMessage.MapMakerMessage;

public class ChangeEnvironmentController {
    private Map map;
    public ChangeEnvironmentController(Map map){
        this.map = map;
    }

    public void run(){
        ChangeEnvironmentMenu menu = new ChangeEnvironmentMenu(this);
        while (true){
            if(menu.run().equals("back")) return;
        }
    }

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


    public MapMakerMessage clearCell(int x, int y) {
        Vector2D coordinate = new Vector2D(x, y);
        if (!map.isCoordinateValid(coordinate)) return MapMakerMessage.INVALID_COORDINATE;
        map.clearCell(coordinate);
        return MapMakerMessage.CLEAR_CELL_SUCCESS;
    }


    public MapMakerMessage dropRock(int x, int y, String directionName) {
        Vector2D coordinate = new Vector2D(x, y);
        CliffDirection cliffDirection = CliffDirection.getDirection(directionName);

        if (!map.isCoordinateValid(coordinate)) return MapMakerMessage.INVALID_COORDINATE;
        if (cliffDirection == null) return MapMakerMessage.INVALID_DIRECTION;
        if (!map.getCell(coordinate).isEmpty()) return MapMakerMessage.NOT_EMPTY;

        Cliff cliff = new Cliff(ConstantManager.getInstance().getCliff(), coordinate, null, cliffDirection);
        map.addMapObject(coordinate, cliff);
        return MapMakerMessage.DROP_ROCK_SUCCESS;
    }

    public MapMakerMessage dropTree(int x, int y, String typeName) {
        Vector2D coordinate = new Vector2D(x, y);
        TreeType type = TreeType.getType(typeName);
        if (type == null) return MapMakerMessage.INVALID_TREE_TYPE;
        if (!map.isCoordinateValid(coordinate)) return MapMakerMessage.INVALID_COORDINATE;
        // TODO : invalid TargetCellType error
//        if (map.getCell(coordinate).getType() )
        Tree tree = new Tree(ConstantManager.getInstance().getTree(), coordinate, null, type);
        map.addMapObject(coordinate, tree);
        return MapMakerMessage.DROP_TREE_SUCCESS;
    }

}
