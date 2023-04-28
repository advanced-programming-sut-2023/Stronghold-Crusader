package model.MapAsset;

import model.Player;
import utils.Vector2D;

public class Cliff extends MapAsset {

    public Cliff(Vector2D coordinate, Player owner) {
        super(coordinate, owner, null);
    }

    @Override
    public void getDamageFrom(MapAsset attacker) {

    }
}
