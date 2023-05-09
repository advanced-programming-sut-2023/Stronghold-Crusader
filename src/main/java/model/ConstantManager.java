package model;

import Settings.Settings;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import model.Game.Store.StoreMaterial;
import model.MapAsset.Building.*;
import model.MapAsset.MobileUnit.*;
import model.MapAsset.*;
import model.enums.AssetType.MapAssetType;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class ConstantManager {
    private static ConstantManager instance;

    //Buildings
    private HashMap<MapAssetType, DefenseAndAttackBuilding> defenseAndAttackBuildings;
    private HashMap<MapAssetType, ProductionBuilding> productionBuildings;
    private HashMap<MapAssetType, StorageBuilding> storageBuildings;
    private HashMap<MapAssetType, TrainingAndEmploymentBuilding> trainingAndEmploymentBuildings;
    private HashMap<MapAssetType, Building> normalBuildings;
    private Tree tree;
    private Cliff cliff;

    //Mobile units
    private HashMap<MapAssetType, MobileUnit> mobileUnits;
    private HashMap<MapAssetType, AttackingUnit> attackingUnits;

    public static ConstantManager getInstance() {
        return instance;
    }

    public static void load() {
        if (instance == null)
            instance = new ConstantManager();
        instance.loadConstants();
    }

    private void loadConstants() {
        JsonObject data;
        try {
            Gson gson = new Gson();
            Reader reader = new FileReader(Settings.GAME_CONSTANTS_PATH);
            data = gson.fromJson(reader, JsonObject.class);
            reader.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        fillAttackingUnits(data);
        fillMobileUnits(data);
        fillNormalBuildings(data);
        fillProductionBuildings(data);
        fillStorageBuildings(data);
        fillTrainingAndEmploymentBuildings(data);
        fillDefenceAndAttackBuildings(data);
        fillRemainingAssets(data);
        fillMarketConstants();
    }


    public MapAsset getAsset(MapAssetType type) {
        MapAsset asset;
        if (type == MapAssetType.CLIFF)
            return cliff;
        if (type == MapAssetType.TREE)
            return tree;
        if ((asset = normalBuildings.get(type)) != null)
            return asset;
        if ((asset = attackingUnits.get(type)) != null)
            return asset;
        if ((asset = defenseAndAttackBuildings.get(type)) != null)
            return asset;
        if ((asset = productionBuildings.get(type)) != null)
            return asset;
        if ((asset = trainingAndEmploymentBuildings.get(type)) != null)
            return asset;
        if ((asset = mobileUnits.get(type)) != null)
            return asset;
        if ((asset = storageBuildings.get(type)) != null)
            return asset;
        return null;
    }

    private void fillNormalBuildings(JsonObject data) {
        normalBuildings = new HashMap<>();
        JsonArray unitsArray = data.getAsJsonArray(Building.class.getName());
        for (JsonElement unitJson : unitsArray) {
            Building building = new Gson().fromJson(unitJson, Building.class);
            normalBuildings.put(building.getType(), building);
        }
    }

    private void fillMobileUnits(JsonObject data) {
        mobileUnits = new HashMap<>();
        JsonArray unitsArray = data.getAsJsonArray(MobileUnit.class.getName());
        for (JsonElement unitJson : unitsArray) {
            MobileUnit unit = new Gson().fromJson(unitJson, MobileUnit.class);
            mobileUnits.put(unit.getType(), unit);
        }
    }

    private void fillAttackingUnits(JsonObject data) {
        attackingUnits = new HashMap<>();
        JsonArray unitsArray = data.getAsJsonArray(AttackingUnit.class.getName());
        for (JsonElement unitJson : unitsArray) {
            AttackingUnit unit = new Gson().fromJson(unitJson, AttackingUnit.class);
            attackingUnits.put(unit.getType(), unit);
        }
    }

    private void fillDefenceAndAttackBuildings(JsonObject data) {
        defenseAndAttackBuildings = new HashMap<>();
        JsonArray unitsArray = data.getAsJsonArray(DefenseAndAttackBuilding.class.getName());
        for (JsonElement unitJson : unitsArray) {
            DefenseAndAttackBuilding unit = new Gson().fromJson(unitJson, DefenseAndAttackBuilding.class);
            defenseAndAttackBuildings.put(unit.getType(), unit);
        }
    }

    private void fillProductionBuildings(JsonObject data) {
        productionBuildings = new HashMap<>();
        JsonArray unitsArray = data.getAsJsonArray(ProductionBuilding.class.getName());
        for (JsonElement unitJson : unitsArray) {
            ProductionBuilding unit = new Gson().fromJson(unitJson, ProductionBuilding.class);
            productionBuildings.put(unit.getType(), unit);
        }
    }

    private void fillStorageBuildings(JsonObject data) {
        storageBuildings = new HashMap<>();
        JsonArray unitsArray = data.getAsJsonArray(StorageBuilding.class.getName());
        for (JsonElement unitJson : unitsArray) {
            StorageBuilding unit = new Gson().fromJson(unitJson, StorageBuilding.class);
            storageBuildings.put(unit.getType(), unit);
        }
    }

    private void fillTrainingAndEmploymentBuildings(JsonObject data) {
        trainingAndEmploymentBuildings = new HashMap<>();
        JsonArray unitsArray = data.getAsJsonArray(TrainingAndEmploymentBuilding.class.getName());
        for (JsonElement unitJson : unitsArray) {
            TrainingAndEmploymentBuilding unit = new Gson().fromJson(unitJson, TrainingAndEmploymentBuilding.class);
            trainingAndEmploymentBuildings.put(unit.getType(), unit);
        }
    }

    private void fillRemainingAssets(JsonObject data) {
        Gson gson = new Gson();
        tree = gson.fromJson(data.get(Tree.class.getName()), Tree.class);
        cliff = gson.fromJson(data.get(Cliff.class.getName()), Cliff.class);
    }

    private void fillMarketConstants() {
        try {
            Gson gson = new Gson();
            Reader reader = new FileReader(Settings.MARKET_CONSTANTS_PATH);
            JsonObject json = gson.fromJson(reader, JsonObject.class);
            reader.close();

            JsonArray materialArray = json.getAsJsonArray("materials");
            StoreMaterial[] materials = gson.fromJson(materialArray, StoreMaterial[].class);
            ArrayList<StoreMaterial> materialList = new ArrayList<>(Arrays.asList(materials));
            StoreMaterial.setMaterialList(materialList);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
