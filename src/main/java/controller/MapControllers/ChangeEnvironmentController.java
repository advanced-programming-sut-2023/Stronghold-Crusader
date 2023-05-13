package controller.MapControllers;

import model.ConstantManager;
import model.Game.Game;
import model.Map.Cell;
import model.Map.Map;
import model.MapAsset.Building.*;
import model.MapAsset.Cliff;
import model.MapAsset.MapAsset;
import model.MapAsset.MobileUnit.AttackingUnit;
import model.MapAsset.MobileUnit.MobileUnit;
import model.MapAsset.Tree;
import model.User.Player;
import model.enums.*;
import model.enums.AssetType.BuildingCategory;
import model.enums.AssetType.MapAssetType;
import utils.Vector2D;
import view.MapMenus.ChangeEnvironmentMenu;
import view.enums.messages.MapMessage.BuildingPlacementMessage;
import view.enums.messages.MapMessage.MapMakerMessage;

import java.util.ArrayList;

public class ChangeEnvironmentController {
    private Map map;
    private Game game;

    public ChangeEnvironmentController(Map map, Game game) {
        this.map = map;
        this.game = game;
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
        map.getCell(coordinate).clear();
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

    public MapMakerMessage dropUnit(int x, int y, String typeName){
        Vector2D coordinate = new Vector2D(x, y);
        MapAssetType type = MapAssetType.getMapAssetType(typeName);
        if (!map.isInMap(coordinate)) return MapMakerMessage.INVALID_COORDINATE;
        if (type == null) return MapMakerMessage.INVALID_UNIT;
        if (!MapAssetType.getPeople().contains(type)) return MapMakerMessage.INVALID_UNIT;
        MobileUnit reference = (MobileUnit) ConstantManager.getInstance().getAsset(type);
        if (reference instanceof AttackingUnit){
            AttackingUnit unit = new AttackingUnit((AttackingUnit) reference, coordinate, game.getCurrentPlayer());
            map.addMapObject(coordinate, unit);
            game.getCurrentPlayer().getGovernance().addAsset(unit);
        }else {
            MobileUnit unit = new MobileUnit(reference, coordinate, game.getCurrentPlayer());
            map.addMapObject(coordinate, unit);
            game.getCurrentPlayer().getGovernance().addAsset(unit);
        }
        return MapMakerMessage.UNIT_DROP_SUCCESS;
    }

    public MapMakerMessage dropBuilding(int x, int y, String typeName){
        Vector2D coordinate = new Vector2D(x, y);
        MapAssetType type = MapAssetType.getMapAssetType(typeName);
        if (!map.isInMap(coordinate)) return MapMakerMessage.INVALID_COORDINATE;
        if (type == null) return MapMakerMessage.INVALID_BUILDING;

        Building reference = (Building) ConstantManager.getInstance().getAsset(type);
        MapMakerMessage msg = isDropSightValid(type, reference, coordinate);
        if (msg != MapMakerMessage.PLACEMENT_SIGHT_VALID) return msg;

        BuildingCategory category = BuildingCategory.getCategory(typeName);
        Building newBuilding = createBuilding(game.getCurrentPlayer(), coordinate, reference, category);
        map.addMapObject(coordinate, newBuilding);
        game.getCurrentPlayer().getGovernance().addAsset(newBuilding);
        if (reference.getType().equals(MapAssetType.OX_TETHER)) {
            MobileUnit cow = new MobileUnit((MobileUnit) ConstantManager.getInstance().getAsset(MapAssetType.COW),
                    coordinate, game.getCurrentPlayer());
            map.addMapObject(coordinate, cow);
            game.getCurrentPlayer().getGovernance().addAsset(cow);
        }
        return MapMakerMessage.BUILDING_DROP_SUCCESS;
    }

    public MapMakerMessage isDropSightValid(MapAssetType buildingType, Building reference,
                                                     Vector2D coordinate) {
        switch (buildingType) {
            case SIEGE_TENT:
                if (!map.getCell(coordinate).isEmpty() &&
                        !(map.getCell(coordinate).containsType(MapAssetType.SQUARE_TOWER) ||
                                map.getCell(coordinate).containsType(MapAssetType.CIRCULAR_TOWER))) {
                    return MapMakerMessage.NOT_EMPTY;
                }
                break;
            case DRAW_BRIDGE:
                if (!hasTypeNearby(coordinate, MapAssetType.BIG_GATEHOUSE) &&
                        !hasTypeNearby(coordinate, MapAssetType.SMALL_GATEHOUSE))
                    return MapMakerMessage.NO_GATEHOUSE_NEARBY;
                break;
            case STORE_HOUSE:
                if (!game.getCurrentPlayer().getGovernance().containsType(MapAssetType.STORE_HOUSE)) break;
                if (!hasTypeNearby(coordinate, MapAssetType.STORE_HOUSE))
                    return MapMakerMessage.NO_STOREHOUSE_NEARBY;
                break;
            default:
                if (!map.getCell(coordinate).isEmpty()) return MapMakerMessage.NOT_EMPTY;
                CellType targetCellType = map.getCell(coordinate).getType();
                if (!reference.isCellTypeValid(targetCellType))
                    return MapMakerMessage.INVALID_CELL_TYPE;

        }
        return MapMakerMessage.PLACEMENT_SIGHT_VALID;
    }

    private boolean hasTypeNearby(Vector2D coordinate, MapAssetType type) {
        ArrayList<Cell> neighbors = map.getNeighbors(coordinate);
        for (Cell neighbor : neighbors) {
            for (MapAsset mapAsset : neighbor.getAllAssets()) {
                if (mapAsset.getType().equals(type)) return true;
            }
        }
        return false;
    }

    private Building createBuilding(Player owner, Vector2D coordinate, Building reference,
                                    BuildingCategory buildingCategory) {
        Building building = null;
        switch (buildingCategory) {
            case DEFENSE_AND_ATTACK:
                building = new DefenseAndAttackBuilding((DefenseAndAttackBuilding) reference, coordinate, owner);
                break;
            case PRODUCTION:
                building = new ProductionBuilding((ProductionBuilding) reference, coordinate, owner);
                break;
            case TRAINING_AND_EMPLOYMENT:
                building = new TrainingAndEmploymentBuilding((TrainingAndEmploymentBuilding) reference, coordinate, owner);
                break;
            case STORAGE:
                building = new StorageBuilding((StorageBuilding) reference, coordinate, owner);
                break;
            case NORMAL:
                building = new Building(reference, coordinate, owner);
                break;
            case ENTRANCE:
                building = new EntranceBuilding(reference, coordinate, owner);
                break;
        }
        return building;
    }


}
