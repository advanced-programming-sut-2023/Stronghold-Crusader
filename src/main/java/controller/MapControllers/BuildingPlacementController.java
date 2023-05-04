package controller.MapControllers;

import model.ConstantManager;
import model.Map.Map;
import model.MapAsset.Building.*;
import model.User.Player;
import model.enums.AssetType.BuildingType;
import model.enums.CellType;
import model.enums.AssetType.MapAssetType;
import utils.FormatValidation;
import utils.Vector2D;
import view.MapMenus.BuildingPlacementMenu.BuildingPlacementMenu;
import view.enums.messages.MapMessage.BuildingPlacementMessage;

public class BuildingPlacementController {
    private Player currentPlayer;
    private Map map;
    private String buildingCategory;

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

    public BuildingPlacementMessage setBuildingCategory(String buildingCategory) {
        if (!FormatValidation.isFormatValid(buildingCategory, FormatValidation.BUILDING_CATEGORY))
            return BuildingPlacementMessage.INVALID_BUILDING_CATEGORY;
        this.buildingCategory = buildingCategory;
        return BuildingPlacementMessage.BUILDING_CATEGORY_SUCCESS;
    }

    public BuildingPlacementMessage dropBuilding(String buildingTypeName, int x, int y) {
        Vector2D coordinate = new Vector2D(x, y);
        BuildingType buildingType = BuildingType.getType(buildingTypeName);
        if (!map.isCoordinateValid(coordinate)) return BuildingPlacementMessage.INVALID_COORDINATE;
        if (buildingType == null) return BuildingPlacementMessage.INVALID_BUILDING_TYPE;

        CellType targetCellType = map.getCell(coordinate).getType();
        MapAssetType assetType = MapAssetType.getMapAssetType(buildingTypeName);
        Building reference = (Building) ConstantManager.getInstance().getAsset(assetType);

        if (!reference.isCellTypeValid(targetCellType)) return BuildingPlacementMessage.INVALID_CELL_TYPE;
        if (!currentPlayer.getGovernance().hasEnoughInStock(reference.getNeededMaterial(), reference.getNumberOfMaterialNeeded()))
            return BuildingPlacementMessage.NOT_ENOUGH_RESOURCE;

        // TODO : Handel workers
        Building newBuilding = createBuilding(currentPlayer, coordinate, reference);
        map.addMapObject(coordinate, newBuilding);
        return BuildingPlacementMessage.BUILDING_DROP_SUCCESS;
    }

    private Building createBuilding(Player owner, Vector2D coordinate, Building reference){
        Building building = null;
        switch (buildingCategory) {
            case "DandA":
                building = new DefenseAndAttackBuilding((DefenseAndAttackBuilding) reference, coordinate, owner);
                break;
            case "Production":
                building = new ProductionBuilding((ProductionBuilding) reference, coordinate, owner);
                break;
            case "TAndA":
                building = new TrainingAndEmploymentBuilding((TrainingAndEmploymentBuilding) reference, coordinate, owner);
                break;
            case "Symbolic":
                building = new SymbolicBuilding((SymbolicBuilding) reference, coordinate, owner);
                break;
            case "Store":
                building = new Store((Store) reference, coordinate, owner);
                break;
            case "OxTether":
                building = new OxTether((OxTether) reference, coordinate, owner);
                break;
        }
        return building;
    }
}

