package model.MapAsset.Building;

import model.Player;
import model.enums.MapAssetType;
import utils.Vector2D;

public class Headquarters extends Building {
    public Headquarters(Headquarters reference, Vector2D coordinate, Player owner) {
        super(reference, coordinate, owner);
    }
}
