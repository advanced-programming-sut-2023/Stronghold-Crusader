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
    private boolean productionMode;

    public ProductionBuilding(ProductionBuilding reference, Vector2D coordinate, Player owner) {
        super(reference, coordinate, owner);
        this.productionCapacity = reference.productionCapacity;
        if (reference.usingMaterial == null) this.usingMaterial = new ArrayList<>();
        else this.usingMaterial = reference.usingMaterial;
        this.producingMaterial = reference.producingMaterial;
        this.rateOfUsage = reference.rateOfUsage;
        this.rateOfProduction = reference.rateOfProduction;
        this.rateOfProcess = reference.rateOfProcess;
        this.productionMode = true;
    }

    public void changeProductionMode() {
        productionMode = !productionMode;
    }

    public boolean getProductionMode() {
        return productionMode;
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

    public ArrayList<Material> getProducingMaterial() {
        return producingMaterial;
    }

    public ArrayList<Material> getUsingMaterial() {
        return usingMaterial;
    }

    public ArrayList<Integer> getRateOfUsage() {
        return rateOfUsage;
    }

    public ArrayList<Integer> getRateOfProduction() {
        return rateOfProduction;
    }
}
