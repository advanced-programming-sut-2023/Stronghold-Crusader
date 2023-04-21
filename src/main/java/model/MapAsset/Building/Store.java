package model.MapAsset.Building;

import model.MapAsset.MapAsset;
import model.Player;
import utils.Vector2D;

public class Store extends Building{
    public Store(Vector2D coordinate, Player owner, MapAsset type) {
        super(coordinate, owner, type);
    }
}
