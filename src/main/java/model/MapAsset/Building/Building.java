package model.MapAsset.Building;

import model.MapAsset.MapAsset;
import model.MapAsset.MobileUnit.AttackingUnit;
import model.Player;
import model.enums.CellType;
import model.enums.MapAssetType;
import model.enums.Material;
import utils.Pair;
import utils.Vector2D;

import java.util.ArrayList;

//TODO complete this class
public class Building extends MapAsset {
    // TODO
    private Pair neededMaterial;
    private final int cost;
    private int workerNumber;
    private ArrayList<CellType> buildingGroundType;

    public Building(Building reference, Vector2D coordinate, Player owner) {
        super(reference, coordinate, owner);
        this.cost = reference.cost;
        this.buildingGroundType = reference.buildingGroundType;
    }

    @Override
    public void getDamageFrom(AttackingUnit attacker) {

    }

    public int getNumberOfMaterialNeeded() {
        return Integer.parseInt(neededMaterial.y);
    }

    public Material getNeededMaterial() {
        return Material.valueOf(neededMaterial.x);
    }

    public void setNeededMaterial(Pair neededMaterial) {
        this.neededMaterial = neededMaterial;
    }

    public void repair() {
        hitPoint = maxHitPoint;
    }
    public boolean isTypeValid(CellType celltype){
        return buildingGroundType.contains(celltype);
    }
}
