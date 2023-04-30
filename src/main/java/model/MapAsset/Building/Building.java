package model.MapAsset.Building;

import model.MapAsset.MapAsset;
import model.MapAsset.MobileUnit.AttackingUnit;
import model.Player;
import model.enums.Material;
import utils.Pair;
import utils.Vector2D;

public class Building extends MapAsset {
    private Pair neededMaterial;

    public Building(Building reference, Vector2D coordinate, Player owner) {
        super(reference, coordinate, owner);
    }

    @Override
    public void getDamageFrom(AttackingUnit attacker) {

    }

    public int getNumberOfMaterialNeeded(){
        return Integer.parseInt(neededMaterial.y);
    }
    public Material getNeededMaterial(){
        return Material.valueOf(neededMaterial.x);
    }
    public void repair(){
        hitPoint = maxHitPoint;
    }

    public void setNeededMaterial(Pair neededMaterial) {
        this.neededMaterial = neededMaterial;
    }

    @Override
    public String toString() {
        return "Building{" +
                "neededMaterial=" + neededMaterial +
                ", coordinate=" + coordinate +
                ", maxHitPoint=" + maxHitPoint +
                ", type=" + type +
                ", owner=" + owner +
                ", hitPoint=" + hitPoint +
                '}';
    }
}
