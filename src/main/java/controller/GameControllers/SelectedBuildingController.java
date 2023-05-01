package controller.GameControllers;

import model.ConstantManager;
import model.Map.Map;
import model.MapAsset.Building.Building;
import model.MapAsset.Building.ProductionBuilding;
import model.MapAsset.Building.TrainingAndEmploymentBuilding;
import model.MapAsset.MapAsset;
import model.MapAsset.MobileUnit.AttackingUnit;
import model.Player;
import model.enums.MapAssetType;
import model.enums.People;
import model.enums.Weapon;
import utils.SignupAndLoginUtils;
import utils.Vector2D;
import view.GameMenus.SelectedAssetMenu;
import view.enums.messages.SelectedBuildingMessage;

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
        if (player.governance.getStorage().getMaterials().get(building.getNeededMaterial()) < materialNeededForRepair())
            return SelectedBuildingMessage.MATERIAL_NEEDED;
        if (isThereEnemy())
            return SelectedBuildingMessage.ENEMY_EXIST;
        player.governance.getStorage().reduceMaterial(building.getNeededMaterial(), materialNeededForRepair());
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
        player.governance.removeBuilding(building);
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
        //TODO If MOBILE UNIT ?
        AttackingUnit sampleAttackingUnit = new AttackingUnit(ConstantManager.getInstance().getAttackingUnit(
                MapAssetType.valueOf(inputs.get("type").toUpperCase())), new Vector2D(coordinate.x, coordinate.y + 1), player);
        if (!isWeaponEnough(sampleAttackingUnit, count))
            return SelectedBuildingMessage.WEAPON_NEEDED;
        if (!isGoldEnough(sampleAttackingUnit.getCost(), count))
            return SelectedBuildingMessage.GOLD_NEEDED;

        player.governance.setGold(player.governance.getGold() - sampleAttackingUnit.getCost() * count);
        player.governance.getStorage().reduceWeapon(sampleAttackingUnit.getWeapon(), count);

        for (int i = 0; i < count; i++) {
            sampleAttackingUnit = new AttackingUnit(ConstantManager.getInstance().getAttackingUnit(
                    MapAssetType.valueOf(inputs.get("type").toUpperCase())), new Vector2D(coordinate.x, coordinate.y + 1), player);
            map.getCell(sampleAttackingUnit.getCoordinate()).addMapAsset(sampleAttackingUnit);
            //TODO if units in governance gone ...
            player.governance.addPeople(sampleAttackingUnit);
        }
        return SelectedBuildingMessage.SUCCESS_CREATING_UNIT;
    }

    private boolean isUnitMatchWithBuilding(String type) {
        if (!People.isContains(type.toUpperCase())) return false;
        People people = People.valueOf(type.toUpperCase());
        if (building.getType().equals(MapAssetType.CHURCH) && people.checkType("churchMan")) return true;
        if (building.getType().equals(MapAssetType.BARRACK) && people.checkType("European")) return true;
        if (building.getType().equals(MapAssetType.MERCENARY_POST) && people.checkType("Arabian")) return true;
        if (building.getType().equals(MapAssetType.ENGINEER_GUILD) && people.checkType("Engineer")) return true;
        return false;
    }

    private boolean isWeaponEnough(AttackingUnit attackingUnit, int count) {
        //TODO if enums change ...
        for (Weapon weapon : attackingUnit.getWeapon()) {
            if (player.governance.getStorage().getWeapons().get(weapon) < count) return false;
        }
        return true;
    }

    private boolean isGoldEnough(int gold, int count) {
        return (player.governance.getGold() >= gold * count);
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
