package model;

import Settings.Settings;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import model.MapAsset.Building.*;
import model.MapAsset.MobileUnit.*;
import model.MapAsset.*;
import model.enums.MapAssetType;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.HashMap;

public class ConstantManager {
    private static ConstantManager instance;
    //Buildings
    private HashMap<MapAssetType, DefenseAndAttackBuilding> defenseAndAttackBuildings;
    private HashMap<MapAssetType, ProductionBuilding> productionBuildings;
    private HashMap<MapAssetType, SymbolicBuilding> symbolicBuildings;
    private HashMap<MapAssetType, TrainingAndEmploymentBuilding> trainingAndEmploymentBuildings;
    private Store store;
    private OxTether oxTether;
    private Headquarters headquarters;
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
        fillMobileUnits(data);
        fillProductionBuildings(data);
        fillAttackingUnits(data);
        fillSymbolicBuildings(data);
        fillTrainingAndEmploymentBuildings(data);
        fillDefenceAndAttackBuildings(data);
        fillRemainingAssets(data);
    }

    public DefenseAndAttackBuilding getDefenseAndAttackBuilding(MapAssetType type) {
        return defenseAndAttackBuildings.get(type);
    }

    public ProductionBuilding getProductionBuilding(MapAssetType type) {
        return productionBuildings.get(type);
    }

    public SymbolicBuilding getSymbolicBuilding(MapAssetType type) {
        return symbolicBuildings.get(type);
    }

    public TrainingAndEmploymentBuilding getTrainingAndEmploymentBuilding(MapAssetType type) {
        return trainingAndEmploymentBuildings.get(type);
    }

    public MobileUnit getMobileUnit(MapAssetType type) {
        return mobileUnits.get(type);
    }

    public AttackingUnit getAttackingUnit(MapAssetType type) {
        return attackingUnits.get(type);
    }

    public Tree getTree() {
        return tree;
    }

    public OxTether getOxTether() {
        return oxTether;
    }

    public Store getStore() {
        return store;
    }

    public Cliff getCliff() {
        return cliff;
    }

    public Headquarters getHeadquarters() {
        return headquarters;
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

    private void fillSymbolicBuildings(JsonObject data) {
        symbolicBuildings = new HashMap<>();
        JsonArray unitsArray = data.getAsJsonArray(SymbolicBuilding.class.getName());
        for (JsonElement unitJson : unitsArray) {
            SymbolicBuilding unit = new Gson().fromJson(unitJson, SymbolicBuilding.class);
            symbolicBuildings.put(unit.getType(), unit);
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
        store = gson.fromJson(data.get(Store.class.getName()), Store.class);
        oxTether = gson.fromJson(data.get(OxTether.class.getName()), OxTether.class);
        headquarters = gson.fromJson(data.get(Headquarters.class.getName()), Headquarters.class);
        tree = gson.fromJson(data.get(Tree.class.getName()), Tree.class);
        cliff = gson.fromJson(data.get(Cliff.class.getName()), Cliff.class);
    }
}
