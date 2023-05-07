package controller.GameControllers;

import model.Game.Game;
import model.MapAsset.MobileUnit.MobileUnit;
import utils.Vector2D;

import java.util.ArrayList;

public class SelectedUnitController {
    private final Game game;
    private final MobileUnit currentUnit;

    public SelectedUnitController(MobileUnit currentUnit, Game game) {
        this.game = game;
        this.currentUnit = currentUnit;
    }

    public ArrayList<Vector2D> findPath(Vector2D destination) {
        return null;
    }

}
