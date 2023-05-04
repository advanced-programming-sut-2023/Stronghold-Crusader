package controller.GameControllers;

import model.ConstantManager;
import model.Map.Map;
import model.MapAsset.Building.Building;
import model.MapAsset.Building.ProductionBuilding;
import model.MapAsset.Building.TrainingAndEmploymentBuilding;
import model.MapAsset.MapAsset;
import model.MapAsset.MobileUnit.AttackingUnit;
import model.MapAsset.MobileUnit.MobileUnit;
import model.User.Player;
import model.enums.AssetType.MapAssetType;
import model.enums.AssetType.Material;
import model.enums.AssetType.People;
import utils.SignupAndLoginUtils;
import utils.Vector2D;
import view.GameMenus.SelectedAssetMenu;
import view.enums.messages.GameMessage.SelectedBuildingMessage;

import java.util.HashMap;

public class SelectedBuildingController {
    private final Building building;
    private final Player player;
    private final Map map;
    private final Vector2D coordinate;

    public SelectedBuildingController(Building building, Player player, Map map, int x, int y) {
        this.building = building;
        this.player = player;
        this.map = map;
        coordinate = new Vector2D(x, y);
    }

    public void run() {
        SelectedAssetMenu selectedAssetMenu = new SelectedAssetMenu(this);
        selectedAssetMenu.run();
    }

    public SelectedBuildingMessage repair() {
        if (player.getGovernance().getStorageStock(building.getNeededMaterial()) < materialNeededForRepair())
            return SelectedBuildingMessage.MATERIAL_NEEDED;
        if (isThereEnemy())
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
        //TODO if Storage ...
        map.getCell(coordinate).removeMapAsset(building);
        player.getGovernance().removeBuilding(building);
        return SelectedBuildingMessage.DELETED_BUILDING;
    }

    public SelectedBuildingMessage changeProductionMode() {
        if (!(building instanceof ProductionBuilding))
            return SelectedBuildingMessage.INVALID_COMMAND_FOR_BUILDING;

        ((ProductionBuilding) building).changeProductionMode();
        if (((ProductionBuilding) building).isProduce())
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

        AttackingUnit sampleAttackingUnit = (sampleMobileUnit instanceof AttackingUnit) ?
                (AttackingUnit) sampleMobileUnit : null;
        if (sampleAttackingUnit != null && !isWeaponEnough(sampleAttackingUnit, count))
            return SelectedBuildingMessage.WEAPON_NEEDED;
        if (!isGoldEnough(sampleMobileUnit.getCost(), count))
            return SelectedBuildingMessage.GOLD_NEEDED;

        player.getGovernance().changeGold(-1 * sampleMobileUnit.getCost() * count);
        if (sampleAttackingUnit != null) {
            for (Material weapon : sampleAttackingUnit.getWeapons())
                player.getGovernance().changeStorageStock(weapon, -1 * count);
        }
        for (int i = 0; i < count; i++) {
            MobileUnit mobileUnit = new AttackingUnit(sampleAttackingUnit, new Vector2D(coordinate.x, coordinate.y + 1), player);
            map.getCell(mobileUnit.getCoordinate()).addMapAsset(sampleMobileUnit);
            player.getGovernance().addPeople(mobileUnit);
        }
        return SelectedBuildingMessage.SUCCESS_CREATING_UNIT;
    }

    private boolean isUnitMatchWithBuilding(String type) {
        if (!People.isContains(type.toUpperCase())) return false;
        People people = People.valueOf(type.toUpperCase());
        switch (building.getType()) {
            case CHURCH:
                return people.checkType("churchMan");
            case BARRACK:
                return people.checkType("European");
            case MERCENARY_POST:
                return people.checkType("Arabian");
            case ENGINEER_GUILD:
                return people.checkType("Engineer");
        }
        return false;
    }

    private boolean isWeaponEnough(AttackingUnit attackingUnit, int count) {
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

    //TODO Find nearby Cells
    private boolean isThereEnemy() {
        for (MapAsset mapAsset : map.getCell(coordinate).getAllAssets()) {
            if (!mapAsset.getOwner().equals(player)) return true;
        }
        return false;
    }
}
