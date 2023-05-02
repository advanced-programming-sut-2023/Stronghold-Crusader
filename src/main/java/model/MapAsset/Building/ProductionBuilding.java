package model.MapAsset.Building;

import model.User.Player;
import model.enums.AssetType.Material;
import utils.Vector2D;

import java.util.ArrayList;

public class ProductionBuilding extends Building {
    private final int productionCapacity;
    private final ArrayList<Material> usingMaterial;
    private final ArrayList<Material> producingMaterial;
    private final ArrayList<Integer> rateOfUsage;
    private final ArrayList<Integer> rateOfProduction;
    private final int rateOfProcess;
    private final int inventory;
    private boolean productionMode;

    public ProductionBuilding(ProductionBuilding reference, Vector2D coordinate, Player owner) {
        super(reference, coordinate, owner);
        this.productionCapacity = reference.productionCapacity;
        this.usingMaterial = reference.usingMaterial;
        this.producingMaterial = reference.producingMaterial;
        this.rateOfUsage = reference.rateOfUsage;
        this.rateOfProduction = reference.rateOfProduction;
        this.rateOfProcess = reference.rateOfProcess;
        this.inventory = reference.inventory;
        this.productionMode = true;
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
                ", inventory=" + inventory +
                ", productionMode=" + productionMode;
    }
}
