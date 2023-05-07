package controller.MapControllers;

import model.ConstantManager;
import model.Map.Cell;
import model.Map.Map;
import model.MapAsset.Building.*;
import model.User.Player;
import model.enums.AssetType.BuildingCategory;
import model.enums.AssetType.BuildingType;
import model.enums.CellType;
import model.enums.AssetType.MapAssetType;
import utils.FormatValidation;
import utils.Vector2D;
import view.MapMenus.BuildingPlacementMenu;
import view.enums.messages.MapMessage.BuildingPlacementMessage;

public class BuildingPlacementController {
    private Player currentPlayer;
    private Map map;
    private BuildingCategory buildingCategory;

    public BuildingPlacementController(Player currentPlayer, Map map) {
        this.currentPlayer = currentPlayer;
        this.map = map;
    }

    public void run() {
        BuildingPlacementMenu menu = new BuildingPlacementMenu(this);
        while (true) {
            if (menu.run().equals("back")) return;
        }
    }

    public BuildingPlacementMessage setBuildingCategory(String categoryName) {
        if (!FormatValidation.isFormatValid(categoryName, FormatValidation.BUILDING_CATEGORY))
            return BuildingPlacementMessage.INVALID_BUILDING_CATEGORY;
        this.buildingCategory = BuildingCategory.getCategory(categoryName);
        return BuildingPlacementMessage.BUILDING_CATEGORY_SUCCESS;
    }

    public BuildingPlacementMessage dropBuilding(String buildingTypeName, int x, int y) {
        Vector2D coordinate = new Vector2D(x, y);
        BuildingType buildingType = BuildingType.getType(buildingTypeName);
        if (!map.isInMap(coordinate)) return BuildingPlacementMessage.INVALID_COORDINATE;
        if (buildingType == null) return BuildingPlacementMessage.INVALID_BUILDING_TYPE;


        MapAssetType assetType = MapAssetType.getMapAssetType(buildingTypeName);
        Building reference = (Building) ConstantManager.getInstance().getAsset(assetType);
        BuildingPlacementMessage msg = isDropSightValid(assetType, reference, coordinate);
        if (msg != BuildingPlacementMessage.PLACEMENT_SIGHT_VALID) return msg;

        if (!currentPlayer.getGovernance().hasEnoughInStock(reference.getNeededMaterial(), reference.getNumberOfMaterialNeeded()))
            return BuildingPlacementMessage.NOT_ENOUGH_RESOURCE;

        // TODO : Handel workers
        Building newBuilding = createBuilding(currentPlayer, coordinate, reference);
        map.addMapObject(coordinate, newBuilding);
        return BuildingPlacementMessage.BUILDING_DROP_SUCCESS;
    }

    private Building createBuilding(Player owner, Vector2D coordinate, Building reference) {
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
            case SYMBOLIC:
                building = new SymbolicBuilding((SymbolicBuilding) reference, coordinate, owner);
                break;
        }
        return building;
    }

    private BuildingPlacementMessage isDropSightValid(MapAssetType buildingType, Building reference,
                                                      Vector2D coordinate) {
        switch (buildingType) {
            case SIEGE_TENT:
                if (!map.getCell(coordinate).isEmpty() &&
                        !(map.getCell(coordinate).containsType(MapAssetType.SQUARE_TOWER) ||
                                map.getCell(coordinate).containsType(MapAssetType.CIRCULAR_TOWER))) {
                    return BuildingPlacementMessage.NOT_EMPTY;
                }
                break;
            case DRAW_BRIDGE:
                if (!hasTypeNearby(coordinate, MapAssetType.BIG_GATEHOUSE) &&
                        !hasTypeNearby(coordinate, MapAssetType.SMALL_GATEHOUSE))
                    return BuildingPlacementMessage.NO_GATEHOUSE_NEARBY;
                break;
            case STORE_HOUSE:
                if (!hasTypeNearby(coordinate, MapAssetType.STORE_HOUSE))
                    return BuildingPlacementMessage.NO_STOREHOUSE_NEARBY;
                break;
            default:
                if (!map.getCell(coordinate).isEmpty()) return BuildingPlacementMessage.NOT_EMPTY;
                CellType targetCellType = map.getCell(coordinate).getType();
                if (!reference.isCellTypeValid(targetCellType))
                    return BuildingPlacementMessage.INVALID_CELL_TYPE;

        }
        return BuildingPlacementMessage.PLACEMENT_SIGHT_VALID;
    }

    private boolean hasTypeNearby(Vector2D coordinate, MapAssetType type) {
        int[] a = {1, -1};
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++) {
                Vector2D newCoordinate = new Vector2D(coordinate.x + a[i], coordinate.y + a[j]);
                if (map.isInMap(newCoordinate)) {
                    Cell cell = map.getCell(coordinate);
                    if (cell.containsType(type)) return true;
                }
            }
        }
        return false;
    }
}

