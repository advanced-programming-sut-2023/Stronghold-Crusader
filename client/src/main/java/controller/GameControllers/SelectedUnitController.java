package controller.GameControllers;

import model.Game.Game;
import model.Map.Cell;
import model.MapAsset.Building.DefenseAndAttackBuilding;
import model.MapAsset.MapAsset;
import model.MapAsset.MobileUnit.AttackingUnit;
import model.MapAsset.MobileUnit.MobileUnit;
import model.enums.AssetType.MapAssetType;
import model.enums.AssetType.UnitState;
import model.enums.AttackTarget;
import utils.Vector2D;
import view.enums.messages.GameMessage.SelectedUnitMessage;

import java.util.ArrayList;
import java.util.LinkedList;

public class SelectedUnitController {
    private final Game game;
    private final ArrayList<MobileUnit> selectedUnits;

    public SelectedUnitController(ArrayList<MobileUnit> selectedUnits, Game game) {
        this.game = game;
        this.selectedUnits = selectedUnits;
    }

    public SelectedUnitMessage moveUnit(int x, int y) {
        Vector2D coordinate = new Vector2D(x, y);
        if (!game.getMap().isInMap(coordinate))
            return SelectedUnitMessage.INVALID_COORDINATE;
        if (!game.getMap().getCell(coordinate).isTraversable(selectedUnits.get(0)))
            return SelectedUnitMessage.INVALID_DESTINATION;
        LinkedList<Vector2D> sample = game.getMap().getTraversePath(selectedUnits.get(0), coordinate);
        if (sample.size() == 0) return SelectedUnitMessage.NO_PATH;
        for (MobileUnit selectedUnit : selectedUnits)
            selectedUnit.selectMoveDestination(coordinate);
        return SelectedUnitMessage.MOVE_SUCCESS;
    }

    public SelectedUnitMessage petrolUnit(int x1, int y1, int x2, int y2) {
        Vector2D v1 = new Vector2D(x1, y1);
        Vector2D v2 = new Vector2D(x2, y2);
        if (!game.getMap().isInMap(v1) || !game.getMap().isInMap(v2))
            return SelectedUnitMessage.INVALID_COORDINATE;
        for (MobileUnit selectedUnit : selectedUnits)
            selectedUnit.selectPatrolPath(v1, v2);
        return SelectedUnitMessage.PATROL_SUCCESS;
    }

    public SelectedUnitMessage setState(String stateName) {
        UnitState unitState = UnitState.getState(stateName);
        if (unitState == null) return SelectedUnitMessage.INVALID_STATE;
        boolean hasSet = false;
        for (MobileUnit selectedUnit : selectedUnits) {
            if (selectedUnit instanceof AttackingUnit) {
                ((AttackingUnit) selectedUnit).setState(unitState);
                hasSet = true;
            }
        }
        if (hasSet) return SelectedUnitMessage.STATE_SELECT_SUCCESS;
        return SelectedUnitMessage.NO_ATTACKING_UNIT;
    }

    public SelectedUnitMessage setAttackTarget(int x, int y) {
        Vector2D coordinate = new Vector2D(x, y);
        if (!game.getMap().isInMap(coordinate)) return SelectedUnitMessage.INVALID_COORDINATE;
        ArrayList<MapAsset> cellAssets = game.getMap().getCell(coordinate).getAllAssets();
        boolean hasSet = false;
        for (MobileUnit selectedUnit : selectedUnits) {
            if (!(selectedUnit instanceof AttackingUnit))
                continue;
            AttackingUnit attackingUnit = ((AttackingUnit) selectedUnit);
            outer:
            for (MapAsset asset : cellAssets) {
                if (asset.getOwner().equals(game.getCurrentPlayer()))
                    continue;
                for (AttackTarget target : attackingUnit.getTargets()) {
                    if (target.contains(asset.getType())) {
                        attackingUnit.selectAttackTarget(asset);
                        hasSet = true;
                        break outer;
                    }
                }
            }
        }
        if (hasSet) return SelectedUnitMessage.TARGET_SELECT_SUCCESS;
        return SelectedUnitMessage.NO_TARGET;
    }

    public boolean isAnythingSelected() {
        return !selectedUnits.isEmpty();
    }

    public void addUnit(MobileUnit mobileUnit) {
        selectedUnits.add(mobileUnit);
    }

    public void deselectAll() {
        selectedUnits.clear();
    }

    public void removeUnit(MobileUnit mobileUnit) {
        selectedUnits.remove(mobileUnit);
    }

    public SelectedUnitMessage digTunnel(int x, int y) {
        Vector2D coordinate = new Vector2D(x, y);
        if (!game.getMap().isInMap(coordinate)) return SelectedUnitMessage.INVALID_COORDINATE;
        Cell selectedCell = game.getMap().getCell(coordinate);
        boolean isCellNeighbour = false;
        for (Cell nearbyCell : game.getMap().getNearbyCells(coordinate, 1)) {
            if (nearbyCell.equals(selectedCell)) {
                isCellNeighbour = true;
                break;
            }
        }
        if (!isCellNeighbour) return SelectedUnitMessage.TUNNEL_NOT_NEARBY;
        for (MapAsset asset : selectedCell.getAllAssets()) {
            if (asset.getType() == MapAssetType.WALL || asset instanceof DefenseAndAttackBuilding) {
                selectedCell.deployTunnel();
                return SelectedUnitMessage.TUNNEL_PLACEMENT_SUCCESS;
            }
        }
        return SelectedUnitMessage.NO_WALLS_OR_TOWERS;
    }

    public ArrayList<MobileUnit> getSelectedUnits() {
        return selectedUnits;
    }
}


