package controller.GameControllers;

import model.Game.Game;
import model.Map.Cell;
import model.MapAsset.MobileUnit.MobileUnit;
import utils.Vector2D;

import java.util.ArrayList;
import java.util.Collections;

public class SelectedUnitController {
    private final Game game;
    private final MobileUnit currentUnit;

    public SelectedUnitController(MobileUnit currentUnit, Game game) {
        this.game = game;
        this.currentUnit = currentUnit;
    }



}
