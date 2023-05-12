package controller.GameControllers;

import model.Game.Game;
import model.MapAsset.MobileUnit.MobileUnit;

public class SelectedUnitController {
    private final Game game;
    private final MobileUnit currentUnit;

    public SelectedUnitController(MobileUnit currentUnit, Game game) {
        this.game = game;
        this.currentUnit = currentUnit;
    }



}


