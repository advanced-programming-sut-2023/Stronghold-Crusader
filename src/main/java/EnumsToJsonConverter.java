import Settings.Settings;
import Settings.UnitConstants.*;
import Settings.BuildingConstants.*;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import model.MapAsset.Cliff;
import model.MapAsset.MobileUnit.*;
import model.MapAsset.Building.*;
import model.MapAsset.Tree;

import java.io.FileWriter;
import java.io.IOException;

public class EnumsToJsonConverter {
    private static final Gson gson = new Gson();

    public static void convert() {
        JsonObject objectsData = new JsonObject();
        addAttackingUnits(objectsData);
        addMobileUnits(objectsData);
        addDefenceAndAttackBuilding(objectsData);
        addProductionBuilding(objectsData);
        addSymbolicBuilding(objectsData);
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
            AttackingUnit toBeAdded = AttackingUnitBuilder.creatingAttackingUnit(null, null, value);
            units.add(gson.toJsonTree(toBeAdded));
        }
        data.add(AttackingUnit.class.getName(), units);
    }

    private static void addMobileUnits(JsonObject data) {
        JsonArray units = new JsonArray();
        for (MobileUnitConstants value : MobileUnitConstants.values()) {
            MobileUnit toBeAdded = MobileUnitBuilder.creatingMobileUnit(null, null, value);
            units.add(gson.toJsonTree(toBeAdded));
        }
        data.add(MobileUnit.class.getName(), gson.toJsonTree(units));
    }

    private static void addDefenceAndAttackBuilding(JsonObject data) {
        JsonArray units = new JsonArray();
        for (DefenseAndAttackConstants value : DefenseAndAttackConstants.values()) {
            DefenseAndAttackBuilding toBeAdded = DefenseAndAttackBuilder.createBuilding(null, null, value);
            units.add(gson.toJsonTree(toBeAdded));
        }
        data.add(DefenseAndAttackBuilding.class.getName(), gson.toJsonTree(units));
    }

    private static void addProductionBuilding(JsonObject data) {
        JsonArray units = new JsonArray();
        for (ProductionConstants value : ProductionConstants.values()) {
            ProductionBuilding toBeAdded = ProductionBuilder.createBuilding(null, null, value);
            units.add(gson.toJsonTree(toBeAdded));
        }
        data.add(ProductionBuilding.class.getName(), gson.toJsonTree(units));
    }

    private static void addSymbolicBuilding(JsonObject data) {
        JsonArray units = new JsonArray();
        for (SymbolicConstants value : SymbolicConstants.values()) {
            SymbolicBuilding toBeAdded = SymbolicBuilder.createBuilding(null, null, value);
            units.add(gson.toJsonTree(toBeAdded));
        }
        data.add(SymbolicBuilding.class.getName(), gson.toJsonTree(units));
    }

    private static void addTrainingAndEmploymentBuilding(JsonObject data) {
        JsonArray units = new JsonArray();
        for (TrainingAndEmploymentConstants value : TrainingAndEmploymentConstants.values()) {
            TrainingAndEmploymentBuilding toBeAdded = TrainingAndEmploymentBuilder.createBuilding(null, null, value);
            units.add(gson.toJsonTree(toBeAdded));
        }
        data.add(TrainingAndEmploymentBuilding.class.getName(), gson.toJsonTree(units));
    }

    private static void addRemainingAssets(JsonObject data){
        //no source for the constants!
        Store store = new Store(null, null);
        store.setMaxHitPoint(0);
        data.add(Store.class.getName(), gson.toJsonTree(store));
        OxTether oxTether = new OxTether(null, null);
        oxTether.setMaxHitPoint(0);
        data.add(OxTether.class.getName(), gson.toJsonTree(oxTether));
        Headquarters headquarters = new Headquarters(null, null);
        headquarters.setMaxHitPoint(0);
        data.add(Headquarters.class.getName(), gson.toJsonTree(headquarters));
        Tree tree = new Tree(null, null, null);
        tree.setMaxHitPoint(0);
        data.add(Tree.class.getName(), gson.toJsonTree(tree));
        Cliff cliff = new Cliff(null,null, null);
        cliff.setMaxHitPoint(0);
        data.add(Cliff.class.getName(), gson.toJsonTree(cliff));
    }
}
