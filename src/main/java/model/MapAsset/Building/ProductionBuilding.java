package model.MapAsset.Building;

import model.User.Player;
import model.enums.AssetType.MapAssetType;
import model.enums.AssetType.Material;
import model.enums.CellType;
import utils.Vector2D;

import java.util.ArrayList;

public class ProductionBuilding extends Building {
    private final int productionCapacity;
    private final ArrayList<Material> usingMaterial;
    private final ArrayList<Material> producingMaterial;
    private final ArrayList<Integer> rateOfUsage;
    private final ArrayList<Integer> rateOfProduction;
    private final int rateOfProcess;
    private boolean productionMode;

    public ProductionBuilding(ProductionBuilding reference, Vector2D coordinate, Player owner) {
        super(reference, coordinate, owner);
        this.productionCapacity = reference.productionCapacity;
        this.usingMaterial = reference.usingMaterial;
        this.producingMaterial = reference.producingMaterial;
        this.rateOfUsage = reference.rateOfUsage;
        this.rateOfProduction = reference.rateOfProduction;
        this.rateOfProcess = reference.rateOfProcess;
        this.productionMode = true;
    }

    public ProductionBuilding(double maxHitPoint, MapAssetType type, int cost, int populationCapacity, Material neededMaterial, int workerCount, ArrayList<CellType> buildingGroundType, int neededMaterialAmount, int productionCapacity, ArrayList<Material> usingMaterial, ArrayList<Material> producingMaterial, ArrayList<Integer> rateOfUsage, ArrayList<Integer> rateOfProduction, int rateOfProcess) {
        super(maxHitPoint, type, cost, populationCapacity, neededMaterial, workerCount, buildingGroundType, neededMaterialAmount);
        this.productionCapacity = productionCapacity;
        this.usingMaterial = usingMaterial;
        this.producingMaterial = producingMaterial;
        this.rateOfUsage = rateOfUsage;
        this.rateOfProduction = rateOfProduction;
        this.rateOfProcess = rateOfProcess;
    }

    public void changeProductionMode() {
        productionMode = !productionMode;
    }

    public boolean isProduce() {
        return productionMode;
    }

    public void produce() {

    }

    public void move() {

    }

    @Override
    public String toString() {
        return super.toString() +
                ", productionCapacity=" + productionCapacity +
                ", usingMaterial=" + usingMaterial +
                ", producingMaterial=" + producingMaterial +
                ", rateOfUsage=" + rateOfUsage +
                ", rateOfProduction=" + rateOfProduction +
                ", rateOfProcess=" + rateOfProcess +
                ", productionMode=" + productionMode;
    }
}
