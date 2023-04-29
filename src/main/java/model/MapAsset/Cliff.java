package model.MapAsset;

import model.Player;
import model.enums.CliffDirection;
import model.enums.MapAssetType;
import utils.Vector2D;

public class Cliff extends MapAsset {
    CliffDirection direction;

    public Cliff(Vector2D coordinate, Player owner, CliffDirection direction) {
        super(coordinate, owner, MapAssetType.CLIFF);
        this.direction = direction;
    }

    public Cliff(Cliff reference, Vector2D coordinate, Player owner, CliffDirection direction){
        super(reference, coordinate, owner);
        this.direction = direction;
    }

    @Override
    public void getDamageFrom(MapAsset attacker) {

    }
}
