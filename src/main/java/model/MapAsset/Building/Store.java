package model.MapAsset.Building;

import model.Player;
import model.enums.MapAssetType;
import utils.Vector2D;

public class Store extends Building{
    public Store(Vector2D coordinate, Player owner, MapAssetType type) {
        super(coordinate, owner, type);
    }
}
