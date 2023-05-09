import Settings.Settings;
import Settings.BuildingConstants.*;
import Settings.UnitConstants.AttackingUnitConstants;
import Settings.UnitConstants.MobileUnitConstants;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import model.MapAsset.Building.*;
import model.MapAsset.Cliff;
import model.MapAsset.MobileUnit.*;
import model.MapAsset.Tree;
import model.enums.AssetType.MapAssetType;

import java.io.FileWriter;
import java.io.IOException;

public class EnumsToJsonConverter {
    private static final Gson gson = new Gson();

    public static void main(String[] args) {
        convert();
    }

    private static void convert() {
        JsonObject objectsData = new JsonObject();
        addNormalBuildings(objectsData);
        addStorageBuilding(objectsData);
        addAttackingUnits(objectsData);
        addMobileUnits(objectsData);
        addDefenceAndAttackBuilding(objectsData);
        addProductionBuilding(objectsData);
        addTrainingAndEmploymentBuilding(objectsData);
        addRemainingAssets(objectsData);
        try {
            FileWriter fileWriter = new FileWriter(Settings.GAME_CONSTANTS_PATH);
            fileWriter.write(new Gson().toJson(objectsData));
            fileWriter.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static void addAttackingUnits(JsonObject data) {
        JsonArray units = new JsonArray();
        for (AttackingUnitConstants value : AttackingUnitConstants.values()) {
            AttackingUnit toBeAdded = new AttackingUnit(value.maxHitPoint, value.type, value.cost, value.moveSpeed, value.defenceMultiplier, value.engineersCount, value.canClimbLadder, value.weapon, value.attackDamage, value.attackRange, value.isAreaSplash, value.targets);
            units.add(gson.toJsonTree(toBeAdded));
        }
        data.add(AttackingUnit.class.getName(), gson.toJsonTree(units));
    }

    private static void addMobileUnits(JsonObject data) {
        JsonArray units = new JsonArray();
        for (MobileUnitConstants value : MobileUnitConstants.values()) {
            MobileUnit toBeAdded = new MobileUnit(value.maxHitPoint, value.type, value.cost, value.moveSpeed, value.defenceMultiplier, value.engineersCount, value.canClimbLadder);
            units.add(gson.toJsonTree(toBeAdded));
        }
        data.add(MobileUnit.class.getName(), gson.toJsonTree(units));
    }

    public static void addNormalBuildings(JsonObject data) {
        JsonArray buildings = new JsonArray();
        for (BuildingConstants value : BuildingConstants.values()) {
            Building toBeAdded = new Building(value.maxHitPoint, value.type, value.cost, value.populationCapacity, value.neededMaterial, value.workerCount, value.buildingGroundType, value.neededMaterialAmount);
            buildings.add(gson.toJsonTree(toBeAdded));
        }
        data.add(Building.class.getName(), gson.toJsonTree(buildings));
    }

    private static void addStorageBuilding(JsonObject data) {
        JsonArray jsonArray = new JsonArray();
        for (StorageConstants value : StorageConstants.values()) {
            StorageBuilding toBeAdded = new StorageBuilding(value.maxHitPoint, value.type, value.cost, value.populationCapacity, value.neededMaterial, value.workerCount, value.buildingGroundType, value.neededMaterialAmount, value.totalCapacity, value.storage);
            jsonArray.add(gson.toJsonTree(toBeAdded));
        }
        data.add(StorageBuilding.class.getName(), gson.toJsonTree(jsonArray));
    }

    private static void addDefenceAndAttackBuilding(JsonObject data) {
        JsonArray units = new JsonArray();
        for (DefenseAndAttackConstants value : DefenseAndAttackConstants.values()) {
            DefenseAndAttackBuilding toBeAdded = new DefenseAndAttackBuilding(value.maxHitPoint, value.type, value.cost, value.populationCapacity, value.neededMaterial, value.workerCount, value.buildingGroundType, value.neededMaterialAmount, value.fireRange, value.defendRange, value.troopCapacity, value.siegeEquipmentAllowance);
            units.add(gson.toJsonTree(toBeAdded));
        }
        data.add(DefenseAndAttackBuilding.class.getName(), gson.toJsonTree(units));
    }

    private static void addProductionBuilding(JsonObject data) {
        JsonArray units = new JsonArray();
        for (ProductionConstants value : ProductionConstants.values()) {
            ProductionBuilding toBeAdded = new ProductionBuilding(value.maxHitPoint, value.type, value.cost, value.populationCapacity, value.neededMaterial, value.workerCount, value.buildingGroundType, value.neededMaterialAmount, value.productionCapacity, value.usingMaterial, value.producingMaterial, value.rateOfUsage, value.rateOfProduction, value.rateOfProcess);
            units.add(gson.toJsonTree(toBeAdded));
        }
        data.add(ProductionBuilding.class.getName(), gson.toJsonTree(units));
    }

    private static void addTrainingAndEmploymentBuilding(JsonObject data) {
        JsonArray units = new JsonArray();
        for (TrainingAndEmploymentConstants value : TrainingAndEmploymentConstants.values()) {
            TrainingAndEmploymentBuilding toBeAdded = new TrainingAndEmploymentBuilding(value.maxHitPoint, value.type, value.cost, value.populationCapacity, value.neededMaterial, value.workerCount, value.buildingGroundType, value.neededMaterialAmount, value.peopleType, value.popularityChangeRate);
            units.add(gson.toJsonTree(toBeAdded));
        }
        data.add(TrainingAndEmploymentBuilding.class.getName(), gson.toJsonTree(units));
    }

    private static void addRemainingAssets(JsonObject data) {
        Tree tree = new Tree(20, MapAssetType.TREE, 0);
        data.add(Tree.class.getName(), gson.toJsonTree(tree));
        Cliff cliff = new Cliff(1, MapAssetType.CLIFF, 0);
        data.add(Cliff.class.getName(), gson.toJsonTree(cliff));
    }
}
