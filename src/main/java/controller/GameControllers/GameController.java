package controller.GameControllers;

import controller.MapControllers.BuildingPlacementController;
import controller.MapControllers.ChangeEnvironmentController;
import controller.MapControllers.ShowMapController;
import javafx.beans.property.DoubleProperty;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.TilePane;
import javafx.beans.property.IntegerProperty;
import model.Game.Game;
import model.Game.Governance;
import model.Map.Cell;
import model.Map.Map;
import model.MapAsset.Building.Building;
import model.MapAsset.Building.EntranceBuilding;
import model.MapAsset.Building.ProductionBuilding;
import model.MapAsset.MapAsset;
import model.MapAsset.MobileUnit.AttackingUnit;
import model.MapAsset.MobileUnit.MobileUnit;
import model.User.Player;
import model.User.User;
import model.enums.AssetType.MapAssetType;
import model.enums.AssetType.Material;
import utils.Pair;
import utils.Vector2D;
import view.GameMenus.GameMenu;
import view.enums.messages.GameMessage.GameMenuMessage;

import java.util.ArrayList;
import java.util.HashMap;

public class GameController {
    private final User currentUser;
    private final Game game;
    private ShowMapController showMapController;
    private SelectedBuildingController selectedBuildingController;
    private final SelectedUnitController selectedUnitController;
    private GraphicsController graphicsController;

    public GameController(User currentUser, Game game) {
        this.currentUser = currentUser;
        this.game = game;
        selectedBuildingController = null;
        selectedUnitController = new SelectedUnitController(new ArrayList<>(), game);
        nextTurn();
    }

    public String nextRound() {
        processLogics();
        applyUnitDecisions();
        deleteDeadPlayers();
        if (game.getDeadPlayers().size() == game.getMap().getPlayerCount()) return "endGame";
        return "continue";
    }

    public String nextTurn() {
        game.nextTurn();
        String output = "continue";
        if (game.isNextRound()) output = nextRound();
        Governance governance = game.getCurrentPlayer().getGovernance();
        governance.processPopulation();
        governance.payTax();
        governance.distributeFoods();
        governance.calculatePopularity();
        produce();
        return output;
    }

    public void produce() {
        Governance governance = game.getCurrentPlayer().getGovernance();
        for (Building building : governance.getBuildings()) {
            if (!(building instanceof ProductionBuilding)) continue;
            ProductionBuilding productionBuilding = (ProductionBuilding) building;
            if (!productionBuilding.getProductionMode()) continue;
            if (productionBuilding.getType() == MapAssetType.QUARRY)
                if (!game.getMap().cellHasAsset(productionBuilding.getCoordinate(), MapAssetType.COW, game.getCurrentPlayer(), false))
                    continue;
            ArrayList<Material> usingMaterial = productionBuilding.getUsingMaterial();
            ArrayList<Material> producingMaterial = productionBuilding.getProducingMaterial();
            for (int i = 0; i < usingMaterial.size(); i++)
                governance.changeStorageStock(usingMaterial.get(i), (-1) * productionBuilding.getRateOfUsage().get(i));
            for (int i = 0; i < producingMaterial.size(); i++)
                governance.changeStorageStock(producingMaterial.get(i), productionBuilding.getRateOfProduction().get(i));
        }
    }

    private void processLogics() {
        Map map = game.getMap();
        Vector2D currentCoord = new Vector2D(0, 0);
        for (int y = 0; y < map.getSize().y; y++) {
            for (int x = 0; x < map.getSize().x; x++) {
                currentCoord.x = x;
                currentCoord.y = y;
                Cell cell = map.getCell(currentCoord);
                processTunnel(cell);
                processDrawBridge(map, cell);
                processGateHouseOpening(cell);
                processMobileShield(map, cell);
                processUnitDecisions(map, cell);
            }
        }
    }

    private void processMobileShield(Map map, Cell cell) {
        Player mobileShieldOwner = null;
        for (MapAsset asset : cell.getAllAssets())
            if (asset.getType().equals(MapAssetType.MOBILE_SHIELD))
                mobileShieldOwner = asset.getOwner();
        if (mobileShieldOwner == null) return;
        for (Cell nearbyCell : map.getNearbyCells(cell.getCoordinate(), 2))
            for (MapAsset asset : nearbyCell.getAllAssets())
                if (asset instanceof MobileUnit && asset.getOwner().equals(mobileShieldOwner))
                    ((MobileUnit) asset).setNearMobileShield(true);
    }

    private void processGateHouseOpening(Cell cell) {
        EntranceBuilding gateHouse = null;
        for (MapAsset asset : cell.getAllAssets())
            if (asset.getType().equals(MapAssetType.BIG_GATEHOUSE) || asset.getType().equals(MapAssetType.SMALL_GATEHOUSE))
                gateHouse = (EntranceBuilding) asset;
        if (gateHouse == null) return;
        for (MapAsset asset : cell.getAllAssets()) {
            if (asset instanceof MobileUnit && !asset.getOwner().equals(gateHouse.getOwner())) {
                gateHouse.open();
                gateHouse.setFlag(true);
                return;
            }
        }
    }

    private void processTunnel(Cell cell) {
        cell.tunnelNextRound();
        if (cell.tunnelToBeDestroyed()) {
            for (MapAsset asset : cell.getAllAssets()) {
                if (!(asset instanceof Building)) continue;
                eraseAsset(asset);
            }
        }
    }

    private void processDrawBridge(Map map, Cell cell) {
        Player drawBridgeOwner = null;
        for (MapAsset asset : cell.getAllAssets())
            if (asset.getType().equals(MapAssetType.DRAW_BRIDGE))
                drawBridgeOwner = asset.getOwner();
        if (drawBridgeOwner == null) return;
        for (Cell nearbyCell : map.getNearbyCells(cell.getCoordinate(), 1)) {
            for (MapAsset asset : nearbyCell.getAllAssets()) {
                if (!(asset instanceof MobileUnit) || asset.getOwner().equals(drawBridgeOwner)) continue;
                ((MobileUnit) asset).reduceMoveSpeed();
            }
        }
    }

    private void processUnitDecisions(Map map, Cell cell) {
        for (MapAsset asset : cell.getAllAssets()) {
            if (asset instanceof AttackingUnit) ((AttackingUnit) asset).processNextRoundDecision(map);
            if (asset instanceof MobileUnit) ((MobileUnit) asset).findNextMoveDest(map);
        }
    }

    private void applyUnitDecisions() {
        Map map = game.getMap();
        Vector2D currentCoord = new Vector2D(0, 0);
        for (int y = 0; y < map.getSize().y; y++) {
            for (int x = 0; x < map.getSize().x; x++) {
                currentCoord.x = x;
                currentCoord.y = y;
                ArrayList<MapAsset> cellAssets = map.getCell(currentCoord).getAllAssets();
                for (int i = cellAssets.size() - 1; i >= 0; i--) {
                    MapAsset asset = cellAssets.get(i);
                    if (asset instanceof MobileUnit) {
                        processMovement(map, (MobileUnit) asset);
                        processSteppedOnKillingPit((MobileUnit) asset);
                    }
                    if (asset instanceof AttackingUnit) processAttack((AttackingUnit) asset);
                }
            }
        }
    }

    private void processSteppedOnKillingPit(MobileUnit asset) {
        MapAsset steppedOnKillingPit = asset.getSteppedOnKillingPit();
        if (steppedOnKillingPit != null) {
            eraseAsset(asset);
            eraseAsset(steppedOnKillingPit);
        }
    }

    private void processMovement(Map map, MobileUnit mobileUnit) {
        Vector2D pastCoordinate = mobileUnit.getCoordinate();
        if (mobileUnit.hasNextMoveDestination())
            mobileUnit.move();
        Vector2D newCoordinate = mobileUnit.getCoordinate();
        map.removeMapObject(pastCoordinate, mobileUnit);
        graphicsController.addTransition(mobileUnit, pastCoordinate, newCoordinate);
        map.addMapObject(newCoordinate, mobileUnit);
    }

    private void processAttack(AttackingUnit attackingAsset) {
        MapAsset targetUnit = attackingAsset.getNextRoundAttackTarget();
        if (targetUnit != null) {
            targetUnit.takeDamageFrom(attackingAsset);
            if (targetUnit.getHitPoint() <= 0)
                eraseAsset(targetUnit);
        }
    }

    private void eraseAsset(MapAsset asset) {
        game.getMap().removeMapObject(asset.getCoordinate(), asset);
        Player owner = asset.getOwner();
        if (owner != null)
            owner.getGovernance().removeAsset(asset);
    }

    private void deleteDeadPlayers() {
        ArrayList<Player> players = game.getPlayers();
        for (int i = players.size() - 1; i >= 0; i--) {
            Player player = players.get(i);
            if (isPlayerDead(player)) deletePlayer(player);
        }
        if (game.getPlayers().size() == 1)
            deletePlayer(game.getPlayers().get(0));
    }

    public void deselectUnits() {
        selectedUnitController.deselectAll();
    }

    public GameMenuMessage selectUnit(int x, int y) {
        Vector2D coordinate = new Vector2D(x, y);
        if (!game.getMap().isInMap(coordinate))
            return GameMenuMessage.INVALID_COORDINATE;
        ArrayList<MapAsset> assets = game.getMap().getCell(coordinate).getAllAssets();
        ArrayList<MobileUnit> selectedUnits = new ArrayList<>();
        for (MapAsset asset : assets) {
            if (!(asset instanceof MobileUnit))
                continue;
            if (asset.getOwner().equals(game.getCurrentPlayer()))
                selectedUnits.add((MobileUnit) asset);
        }
        if (selectedUnits.size() == 0)
            return GameMenuMessage.NO_UNITS_IN_PLACE;
        for (MobileUnit unit : selectedUnits)
            selectedUnitController.addUnit(unit);
        return GameMenuMessage.UNIT_SELECTED;
    }

    public GameMenuMessage selectBuilding(int x, int y) {
        Vector2D coordinate = new Vector2D(x, y);
        selectedBuildingController = null;
        if (!game.getMap().isInMap(coordinate))
            return GameMenuMessage.INVALID_COORDINATE;
        ArrayList<MapAsset> assets = game.getMap().getCell(coordinate).getAllAssets();
        for (MapAsset asset : assets) {
            if (!(asset instanceof Building))
                continue;
            if (asset.getOwner() != null && !asset.getOwner().equals(game.getCurrentPlayer()))
                return GameMenuMessage.WRONG_OWNER;
            selectedBuildingController = new SelectedBuildingController((Building) asset, game);
            return GameMenuMessage.BUILDING_SELECTED;
        }
        return GameMenuMessage.NO_BUILDING_IN_PLACE;
    }

    public SelectedBuildingController getSelectedBuildingController() {
        return selectedBuildingController;
    }

    public SelectedUnitController getSelectedUnitController() {
        return selectedUnitController;
    }

    public String getCurrentPlayerName() {
        return game.getCurrentPlayer().getNickname();
    }

    public int getRoundNum() {
        return game.getRound();
    }

    public GameMenuMessage showMap(int x, int y) {
        Vector2D coordinate = new Vector2D(x, y);
        if (!ShowMapController.isCenterValid(coordinate, game.getMap()))
            return GameMenuMessage.INVALID_COORDINATE;
        showMapController = new ShowMapController(game.getMap(), coordinate);
        return GameMenuMessage.ENTER_SHOW_MAP;
    }

    public String showPopularityFactors() {
        Governance currentGov = game.getCurrentPlayer().getGovernance();
        return "Popularity factors:" +
                "\n-Food: " + currentGov.getFoodPopularity() +
                "\n-Tax: " + currentGov.getTaxPopularity() +
                "\n-Religion: " + currentGov.getReligionPopularity() +
                "\n-Fear rate: " + currentGov.getFearPopularity() +
                "\n-Inn: " + currentGov.getInnPopularity();
    }

    public int getPopularity() {
        return game.getCurrentPlayer().getGovernance().getTotalPopularity();
    }

    public int getFoodRate() {
        return game.getCurrentPlayer().getGovernance().getFoodRate();
    }

    public String showFoodList() {
        StringBuilder output = new StringBuilder();
        HashMap<Material, Integer> list = game.getCurrentPlayer().getGovernance().getFoodList();
        for (java.util.Map.Entry<Material, Integer> entry : list.entrySet())
            output.append(entry.getKey().name().toLowerCase()).append(": ").append(entry.getValue()).append('\n');
        output.deleteCharAt(output.length() - 1);
        return output.toString();
    }

    public int getTaxRate() {
        return game.getCurrentPlayer().getGovernance().getTaxRate();
    }

    public int getFearRate() {
        return game.getCurrentPlayer().getGovernance().getFearRate();
    }

    public DoubleProperty getGold() {
        return game.getCurrentPlayer().getGovernance().getGold();
    }

    public IntegerProperty getPeasentPopulation() {
        return game.getCurrentPlayer().getGovernance().getPeasantPopulation();
    }

    public void setFearRate(int fearRate) {
        game.getCurrentPlayer().getGovernance().setFearRate(fearRate);
    }

    public void setTaxRate(int taxRate) {
        game.getCurrentPlayer().getGovernance().setTaxRate(taxRate);

    }

    public void setFoodRate(int foodRate) {
        game.getCurrentPlayer().getGovernance().setFoodRate(foodRate);
    }

    public boolean isModifiable() {
        return game.isEditableMode();
    }

    private void deletePlayer(Player player) {
        int highScore = 0;
        highScore += player.getGovernance().getGold().get() / 100;
        highScore += player.getGovernance().getTotalPopularity() * 10;
        highScore += player.getGovernance().getBuildings().size() / 10;
        highScore += player.getGovernance().getUnits().size() / 10;
        highScore += (game.getDeadPlayers().size()) * 200;
        if (game.getPlayers().size() == 1) highScore += 3000;
        deleteAllAsset(player.getGovernance());
        game.removePlayer(player);
        game.getDeadPlayers().add(new Pair(player.getUsername(), highScore, player.getGovernance().toString()));
    }

    private void deleteAllAsset(Governance governance) {
        for (MapAsset mapAsset : governance.getBuildings()) {
            game.getMap().removeMapObject(mapAsset.getCoordinate(), mapAsset);
        }
        governance.getBuildings().clear();

        for (MapAsset mapAsset : governance.getUnits()) {
            game.getMap().removeMapObject(mapAsset.getCoordinate(), mapAsset);
        }
        governance.getUnits().clear();
    }

    private boolean isPlayerDead(Player player) {
        return player.getGovernance().getBuildings().size() == 0 ||
                !player.getGovernance().getBuildings().get(0).getType().equals(MapAssetType.HEADQUARTER);
    }

    public Player getCurrentPlayer() {
        return game.getCurrentPlayer();
    }

    public Game getGame() {
        return game;
    }

    public void setGraphicsController(GraphicsController graphicsController) {
        this.graphicsController = graphicsController;
    }
     public boolean isBuildingSelected(){
        return selectedBuildingController != null;
     }
}
