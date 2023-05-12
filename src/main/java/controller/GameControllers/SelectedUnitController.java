package controller.GameControllers;

import model.Game.Game;
import model.MapAsset.MobileUnit.MobileUnit;;
import utils.Vector2D;
import view.GameMenus.SelectedUnitMenu;
import view.enums.messages.GameMessage.SelectedUnitMessage;

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

    public SelectedUnitMessage moveUnit(int x, int y){
        Vector2D coordinate = new Vector2D(x, y);
        if (!game.getMap().isInMap(coordinate))
            return SelectedUnitMessage.INVALID_COORDINATE;
        for (MobileUnit selectedUnit : selectedUnits)
            selectedUnit.selectMoveDestination(coordinate);
        return SelectedUnitMessage.MOVE_SUCCESS;
    }

    public SelectedUnitMessage petrolUnit(int x1, int y1, int x2, int y2){
        Vector2D v1 = new Vector2D(x1, y1);
        Vector2D v2 = new Vector2D(x2, y2);
        if (!game.getMap().isInMap(v1) || !game.getMap().isInMap(v2))
            return SelectedUnitMessage.INVALID_COORDINATE;
        for (MobileUnit selectedUnit : selectedUnits)
            selectedUnit.selectPatrolPath(v1, v2);
        return SelectedUnitMessage.PATROL_SUCCESS;
    }
}


