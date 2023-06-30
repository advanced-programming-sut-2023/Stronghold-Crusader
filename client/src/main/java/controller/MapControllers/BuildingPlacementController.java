package controller.MapControllers;

import model.ConstantManager;
import model.Map.Cell;
import model.Map.Map;
import model.MapAsset.Building.*;
import model.MapAsset.MapAsset;
import model.MapAsset.MobileUnit.AttackingUnit;
import model.MapAsset.MobileUnit.MobileUnit;
import model.User.Player;
import model.User.User;
import model.enums.AssetType.BuildingCategory;
import model.enums.AssetType.BuildingType;
import model.enums.AssetType.MapAssetType;
import model.enums.AssetType.UnitState;
import model.enums.CellType;
import utils.Vector2D;
import view.enums.messages.MapMessage.BuildingPlacementMessage;

import java.util.ArrayList;

import static view.enums.messages.MapMessage.BuildingPlacementMessage.INVALID_BUILDING_TYPE;

public class BuildingPlacementController {
    private final Player currentPlayer;
    private final Map map;
    private BuildingCategory buildingCategory;
    private final boolean isModifiable;

    public BuildingPlacementController(Player currentPlayer, Map map, boolean isModifiable) {
        this.isModifiable = isModifiable;
        this.currentPlayer = currentPlayer;
        this.map = map;
    }

    public BuildingPlacementController(Map map, boolean isModifiable){
        this.isModifiable = isModifiable;
        this.currentPlayer = new Player(new User("", "", "", "", ""));
        this.map = map;
    }

    public void setBuildingCategory(String categoryName) {
        BuildingCategory category = BuildingCategory.getCategory(categoryName);
        if (category == null) return;
        this.buildingCategory = category;
    }

    public BuildingPlacementMessage dropBuilding(String buildingTypeName, int x, int y, boolean copyPasteMode) {
        if (!copyPasteMode) {
            if (buildingCategory == null) return BuildingPlacementMessage.SELECT_CATEGORY;
            BuildingType buildingType = BuildingType.getType(buildingTypeName);
            if (!BuildingType.getCategory(buildingTypeName).equals(buildingCategory)) return INVALID_BUILDING_TYPE;
            if (buildingType == null) return INVALID_BUILDING_TYPE;
        }
        else
            buildingCategory = BuildingType.getCategory(buildingTypeName);
        Vector2D coordinate = new Vector2D(x, y);
        if (!map.isInMap(coordinate)) return BuildingPlacementMessage.INVALID_COORDINATE;
        MapAssetType assetType = MapAssetType.getMapAssetType(buildingTypeName);
        Building reference = (Building) ConstantManager.getInstance().getAsset(assetType);
        BuildingPlacementMessage msg = isDropSightValid(assetType, reference, coordinate);

        if (msg != BuildingPlacementMessage.PLACEMENT_SIGHT_VALID) return msg;

        if (!isModifiable) {
            if (!currentPlayer.getGovernance().hasEnoughInStock(reference.getNeededMaterial(),
                    reference.getNumberOfMaterialNeeded()))
                return BuildingPlacementMessage.NOT_ENOUGH_RESOURCE;
            if (!enoughWorkers(reference)) return BuildingPlacementMessage.NOT_ENOUGH_WORKERS;
        }

        if (reference.getType().equals(MapAssetType.OX_TETHER)) {
            oxtetherOperations(coordinate);
        }
        if (reference.getType().equals(MapAssetType.CAGED_WARDOG)) {
            cagedWarDogOperations(coordinate);
        }
        createBuildingFinal(reference, coordinate);
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
            case STORAGE:
                building = new StorageBuilding((StorageBuilding) reference, coordinate, owner);
                break;
            case NORMAL:
                building = new Building(reference, coordinate, owner);
                break;
            case ENTRANCE:
                building = new EntranceBuilding((EntranceBuilding) reference, coordinate, owner);
                break;
        }
        return building;
    }

    private void createBuildingFinal(Building reference, Vector2D coordinate) {
        if (!isModifiable) {
            currentPlayer.getGovernance().changePeasantPopulation((-1) * reference.getWorkerCount());
            if (reference.getNeededMaterial() != null)
                currentPlayer.getGovernance().changeStorageStock(reference.getNeededMaterial(),
                        (-1) * reference.getNumberOfMaterialNeeded());
        }
        Building newBuilding = createBuilding(currentPlayer, coordinate, reference);
        map.addMapObject(coordinate, newBuilding);
        currentPlayer.getGovernance().addAsset(newBuilding);
    }

    private void oxtetherOperations(Vector2D coordinate) {
        MobileUnit cow = new MobileUnit((MobileUnit) ConstantManager.getInstance().getAsset(MapAssetType.COW),
                coordinate, currentPlayer);
        map.addMapObject(coordinate, cow);
        currentPlayer.getGovernance().addAsset(cow);
        Vector2D[] cowPatrolPath = map.findCowPatrolPath(currentPlayer);
        if (cowPatrolPath != null)
            cow.selectPatrolPath(cowPatrolPath[0], cowPatrolPath[1]);
    }

    private void cagedWarDogOperations(Vector2D coordinate) {
        AttackingUnit dog1 = new AttackingUnit((AttackingUnit) ConstantManager.getInstance().getAsset(MapAssetType.DOG),
                coordinate, currentPlayer);
        AttackingUnit dog2 = new AttackingUnit((AttackingUnit) ConstantManager.getInstance().getAsset(MapAssetType.DOG),
                coordinate, currentPlayer);
        dog1.setState(UnitState.OFFENSIVE);
        dog2.setState(UnitState.OFFENSIVE);
        map.addMapObject(coordinate, dog1);
        currentPlayer.getGovernance().addAsset(dog1);
        map.addMapObject(coordinate, dog2);
        currentPlayer.getGovernance().addAsset(dog2);
    }

    public BuildingPlacementMessage isDropSightValid(MapAssetType buildingType, Building reference,
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
                if (!currentPlayer.getGovernance().containsType(MapAssetType.STORE_HOUSE)) break;
                if (!hasTypeNearby(coordinate, MapAssetType.STORE_HOUSE))
                    return BuildingPlacementMessage.NO_STOREHOUSE_NEARBY;
                break;
            case FOOD_STORAGE:
                if (!currentPlayer.getGovernance().containsType(MapAssetType.FOOD_STORAGE)) break;
                if (!hasTypeNearby(coordinate, MapAssetType.FOOD_STORAGE))
                    return BuildingPlacementMessage.NO_STOREHOUSE_NEARBY;
                break;
        }
        if (!map.getCell(coordinate).isEmpty()) return BuildingPlacementMessage.NOT_EMPTY;
        CellType targetCellType = map.getCell(coordinate).getType();
        if (!reference.isCellTypeValid(targetCellType))
            return BuildingPlacementMessage.INVALID_CELL_TYPE;
        return BuildingPlacementMessage.PLACEMENT_SIGHT_VALID;
    }

    private boolean enoughWorkers(Building reference) {
        int neededNumber = reference.getWorkerCount();
        int playerWorkerCount = currentPlayer.getGovernance().getPeasantPopulation().get();
        return playerWorkerCount >= neededNumber;
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

    public boolean isModifiable() {
        return isModifiable;
    }
}

