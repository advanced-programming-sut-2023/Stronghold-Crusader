package model.MapAsset.Building;

import model.User.Player;
import utils.Vector2D;

public class OxTether extends Building {
    public OxTether(OxTether reference, Vector2D coordinate, Player owner) {
        super(reference, coordinate, owner);
    }
}
