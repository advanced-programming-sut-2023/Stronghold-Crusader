package model.MapAsset.Building;

import model.Player;
import model.enums.MapAssetType;
import utils.Vector2D;

public class Headquarters extends Building{
    public Headquarters(Vector2D coordinate, Player owner, MapAssetType type) {
        super(coordinate, owner, type);
    }
}
