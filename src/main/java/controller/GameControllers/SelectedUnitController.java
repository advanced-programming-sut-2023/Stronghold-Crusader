package controller.GameControllers;

import model.Game.Game;
import model.MapAsset.MobileUnit.MobileUnit;;
import view.GameMenus.SelectedUnitMenu;

import java.util.ArrayList;

public class SelectedUnitController {
    private final Game game;
    private final ArrayList<MobileUnit> selectedUnits;

    public SelectedUnitController(ArrayList<MobileUnit> selectedUnits, Game game) {
        this.game = game;
        this.selectedUnits = selectedUnits;
    }

    public void run(){
        SelectedUnitMenu selectedUnitMenu = new SelectedUnitMenu(this);
        selectedUnitMenu.run();
    }


}


