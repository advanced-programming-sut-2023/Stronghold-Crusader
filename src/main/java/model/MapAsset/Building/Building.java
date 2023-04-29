package model.MapAsset.Building;

import model.MapAsset.MapAsset;
import model.MapAsset.MobileUnit.AttackingUnit;
import model.Player;
import utils.Vector2D;

public class Building extends MapAsset {
    public Building(Building reference, Vector2D coordinate, Player owner) {
        super(reference, coordinate, owner);
    }

    @Override
    public void getDamageFrom(AttackingUnit attacker) {

    }
}
