package model.MapAsset.Building;

import model.MapAsset.MapAsset;
import model.Player;
import model.enums.Material;
import utils.Vector2D;

import java.util.ArrayList;

public class ProductionBuilding extends Building{
    private int productionCapacity;
    private ArrayList<Material> usingMaterial;
    private ArrayList<Material> producingMaterial;
    private ArrayList<Integer> rateOfUsage;
    private ArrayList<Integer> rateOfProduction;
    private int rateOfProcess;
    private int inventory;

    public ProductionBuilding(Vector2D coordinate, Player owner, MapAsset type) {
        super(coordinate, owner, type);
    }

    public void produce(){

    }

    public void move(){

    }

    @Override
    public void setMaxHitPoint(double maxHitPoint) {
        super.setMaxHitPoint(maxHitPoint);
    }

    public void setInventory(int inventory) {
        this.inventory = inventory;
    }

    public void setProducingMaterial(ArrayList<Material> producingMaterial) {
        this.producingMaterial = producingMaterial;
    }

    public void setProductionCapacity(int productionCapacity) {
        this.productionCapacity = productionCapacity;
    }

    public void setRateOfProcess(int rateOfProcess) {
        this.rateOfProcess = rateOfProcess;
    }

    public void setRateOfProduction(ArrayList<Integer> rateOfProduction) {
        this.rateOfProduction = rateOfProduction;
    }

    public void setRateOfUsage(ArrayList<Integer> rateOfUsage) {
        this.rateOfUsage = rateOfUsage;
    }

    public void setUsingMaterial(ArrayList<Material> usingMaterial) {
        this.usingMaterial = usingMaterial;
    }
}
