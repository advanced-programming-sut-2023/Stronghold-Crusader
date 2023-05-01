package model.MapAsset.Building;

import model.MapAsset.MapAsset;
import model.MapAsset.MobileUnit.AttackingUnit;
import model.Player;
import model.enums.Material;
import utils.Pair;
import utils.Vector2D;

//TODO complete this class
public class Building extends MapAsset {
    private Pair neededMaterial;
    private final int cost;

    public Building(Building reference, Vector2D coordinate, Player owner) {
        super(reference, coordinate, owner);
        this.cost = reference.cost;
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

}
