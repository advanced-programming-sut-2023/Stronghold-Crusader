package controller.GameControllers;

import model.ConstantManager;
import model.Game.Game;
import model.Map.Cell;
import model.Map.Map;
import model.MapAsset.Building.Building;
import model.MapAsset.Building.EntranceBuilding;
import model.MapAsset.Building.ProductionBuilding;
import model.MapAsset.Building.TrainingAndEmploymentBuilding;
import model.MapAsset.MapAsset;
import model.MapAsset.MobileUnit.AttackingUnit;
import model.MapAsset.MobileUnit.MobileUnit;
import model.User.Player;
import model.enums.AssetType.MapAssetType;
import model.enums.AssetType.Material;
import utils.SignupAndLoginUtils;
import utils.Vector2D;
import view.GameMenus.SelectedBuildingMenu;
import view.enums.messages.GameMessage.SelectedBuildingMessage;

import java.util.ArrayList;
import java.util.HashMap;

public class SelectedBuildingController {
    private final Building building;
    private final Player player;
    private final Map map;
    private final Vector2D coordinate;

    public SelectedBuildingController(Building building, Game game) {
        this.building = building;
        this.player = game.getCurrentPlayer();
        this.map = game.getMap();
        coordinate = building.getCoordinate();
    }

    public void run() {
        if (building.getType().equals(MapAssetType.STORE)) {
            MarketController marketController = new MarketController(player);
            marketController.run();
        } else {
            SelectedBuildingMenu selectedBuildingMenu = new SelectedBuildingMenu(this);
            selectedBuildingMenu.run();
        }
    }

    public SelectedBuildingMessage repair() {
        if (player.getGovernance().getStorageStock(building.getNeededMaterial()) < materialNeededForRepair())
            return SelectedBuildingMessage.MATERIAL_NEEDED;
        if (isThereEnemy(1))
            return SelectedBuildingMessage.ENEMY_EXIST;
        player.getGovernance().changeStorageStock(building.getNeededMaterial(), -1 * materialNeededForRepair());
        building.repair();
        return SelectedBuildingMessage.SUCCESS_REPAIR;
    }

    public String showInfo() {
        return building.toString();
    }

    public SelectedBuildingMessage deleteBuilding() {
        if (map.getCell(coordinate).isThereUnit())
            return SelectedBuildingMessage.NOT_ALLOWED_TO_DELETE;
        map.getCell(coordinate).removeMapAsset(building);
        player.getGovernance().removeAsset(building);
        return SelectedBuildingMessage.DELETED_BUILDING;
    }

    public SelectedBuildingMessage changeProductionMode() {
        if (!(building instanceof ProductionBuilding))
            return SelectedBuildingMessage.INVALID_COMMAND_FOR_BUILDING;

        ((ProductionBuilding) building).changeProductionMode();
        if (((ProductionBuilding) building).getProductionMode())
            return SelectedBuildingMessage.RESUME_PRODUCTION;
        else return SelectedBuildingMessage.STOP_PRODUCTION;
    }

    public SelectedBuildingMessage createUnit(HashMap<String, String> inputs) {
        if (inputs.get("type") == null || inputs.get("count") == null) return SelectedBuildingMessage.EMPTY_FIELD;
        if (!SignupAndLoginUtils.isNumberValid(inputs.get("count")))
            return SelectedBuildingMessage.INVALID_COUNT;
        if (!(building instanceof TrainingAndEmploymentBuilding))
            return SelectedBuildingMessage.INVALID_COMMAND_FOR_BUILDING;
        if (!isUnitMatchWithBuilding(inputs.get("type")))
            return SelectedBuildingMessage.INVALID_UNIT_FOR_CREATING;

        int count = Integer.parseInt(inputs.get("count"));
        MapAssetType type = MapAssetType.valueOf(inputs.get("type").toUpperCase());

        MobileUnit sampleMobileUnit = (MobileUnit) ConstantManager.getInstance().getAsset(type);

        AttackingUnit sampleAttackingUnit = null;
        if (sampleMobileUnit instanceof AttackingUnit)
            sampleAttackingUnit = (AttackingUnit) sampleMobileUnit;
        if (sampleAttackingUnit != null && !isWeaponEnough(sampleAttackingUnit, count))
            return SelectedBuildingMessage.WEAPON_NEEDED;
        if (!isGoldEnough(sampleMobileUnit.getCost(), count))
            return SelectedBuildingMessage.GOLD_NEEDED;

        player.getGovernance().changeGold(-1 * sampleMobileUnit.getCost() * count);
        if (sampleAttackingUnit != null) {
            if (sampleAttackingUnit.getWeapons() != null) {
                for (Material weapon : sampleAttackingUnit.getWeapons())
                    player.getGovernance().changeStorageStock(weapon, -1 * count);
            }
        }
        for (int i = 0; i < count; i++) {
            MobileUnit mobileUnit;
            if(sampleAttackingUnit == null)
                mobileUnit = new MobileUnit(sampleMobileUnit, new Vector2D(coordinate.x, coordinate.y), player);
            else
                mobileUnit = new AttackingUnit(sampleAttackingUnit, new Vector2D(coordinate.x, coordinate.y), player);
            map.getCell(mobileUnit.getCoordinate()).addMapAsset(mobileUnit);
            player.getGovernance().addAsset(mobileUnit);
        }
        return SelectedBuildingMessage.SUCCESS_CREATING_UNIT;
    }

    public SelectedBuildingMessage changeGate() {
        if (building instanceof EntranceBuilding) {
            if (isThereEnemy(0)) return SelectedBuildingMessage.ENEMY_IN_GATE;
            if (((EntranceBuilding) building).isOpen())
                ((EntranceBuilding) building).close();
            else ((EntranceBuilding) building).open();
            return SelectedBuildingMessage.SUCCESS;
        }
        return SelectedBuildingMessage.INVALID_COMMAND_FOR_BUILDING;
    }

    public SelectedBuildingMessage setFoodRate(int foodRate) {
        if (!building.getType().equals(MapAssetType.FOOD_STORAGE))
            return SelectedBuildingMessage.INVALID_COMMAND_FOR_BUILDING;
        if (foodRate > 2 || foodRate < -2)
            return SelectedBuildingMessage.INVALID_FOOD_RATE;
        player.getGovernance().setFoodRate(foodRate);
        return SelectedBuildingMessage.FOOD_RATE_CHANGE_SUCCESS;
    }

    public SelectedBuildingMessage setTaxRate(int taxRate) {
        if (!building.getType().equals(MapAssetType.HEADQUARTER))
            return SelectedBuildingMessage.INVALID_COMMAND_FOR_BUILDING;
        if (taxRate > 8 || taxRate < -3)
            return SelectedBuildingMessage.INVALID_TAX_RATE;
        player.getGovernance().setTaxRate(taxRate);
        return SelectedBuildingMessage.TAX_RATE_CHANGE_SUCCESS;
    }


    private boolean isUnitMatchWithBuilding(String type) {
        MapAssetType person = MapAssetType.getPerson(type);
        if (person == null)
            return false;
        ArrayList<MapAssetType> persons = ((TrainingAndEmploymentBuilding) building).getPeopleType();
        for (MapAssetType unit : persons) {
            if (unit.equals(person)) return true;
        }
        return false;
    }

    private boolean isWeaponEnough(AttackingUnit attackingUnit, int count) {
        if (attackingUnit.getWeapons() == null) return true;
        for (Material weapon : attackingUnit.getWeapons()) {
            if (player.getGovernance().getStorageStock(weapon) < count) return false;
        }
        return true;
    }

    private boolean isGoldEnough(int gold, int count) {
        return (player.getGovernance().getGold() >= gold * count);
    }

    private int materialNeededForRepair() {
        return (int) ((building.getMaxHitPoint() - building.getHitPoint()) / building.getMaxHitPoint()
                * building.getNumberOfMaterialNeeded());
    }

    private boolean isThereEnemy(int number) {
        ArrayList<Cell> cells = map.getNearbyCells(building.getCoordinate(), number);
        for (Cell cell : cells) {
            for (MapAsset mapAsset : cell.getAllAssets()) {
                if (!mapAsset.getOwner().equals(player)) return true;
            }
        }
        return false;
    }


}
