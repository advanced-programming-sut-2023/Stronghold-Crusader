package model.MapAsset.Building;

import model.MapAsset.MapAsset;
import model.Player;
import utils.Vector2D;

public class Building extends MapAsset {
    public Building(Vector2D coordinate, Player owner, MapAsset type) {
        super(coordinate, owner, type);
    }

    @Override
    public void getDamageFrom(MapAsset attacker) {

    }
}
