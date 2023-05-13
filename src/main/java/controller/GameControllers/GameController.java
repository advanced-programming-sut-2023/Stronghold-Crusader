package controller.GameControllers;

import controller.MapControllers.BuildingPlacementController;
import controller.MapControllers.ChangeEnvironmentController;
import controller.MapControllers.ShowMapController;
import model.Game.Game;
import model.Game.Governance;
import model.Map.Map;
import model.MapAsset.Building.Building;
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
    private SelectedUnitController selectedUnitController;

    public GameController(User currentUser, Game game) {
        this.currentUser = currentUser;
        this.game = game;
    }

    public String run() {
        nextTurn();
        GameMenu gameMenu = new GameMenu(this);
        while (true) {
            switch (gameMenu.run()) {
                case "changeEnvironment":
                    ChangeEnvironmentController environmentController = new
                            ChangeEnvironmentController(game.getMap(), game);
                    environmentController.run();
                    break;
                case "buildingPlacement":
                    BuildingPlacementController controller = new BuildingPlacementController(game.getCurrentPlayer(), game.getMap());
                    controller.run();
                    break;
                case "tradeMenu":
                    TradeController tradeController = new TradeController(game);
                    tradeController.run();
                    break;
                case "showMap":
                    showMapController.run();
                    break;
                case "marketMenu":
                    MarketController marketController = new MarketController(game.getCurrentPlayer());
                    marketController.run();
                    break;
                case "selectedBuilding":
                    selectedBuildingController.run();
                    selectedBuildingController = null;
                    break;
                case "selectedUnit":
                    selectedUnitController.run();
                    selectedUnitController = null;
                    break;
                case "endGame":
                    EndGameController endGameController = new EndGameController(game.getDeadPlayers());
                    endGameController.run();
                    break;
            }
        }
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
        for (Player player : game.getPlayers()) {
            Governance governance = player.getGovernance();
            for (Building building : player.getGovernance().getBuildings()) {
                if (building instanceof ProductionBuilding) {
                    ProductionBuilding productionBuilding = (ProductionBuilding) building;
                    if (!productionBuilding.getProductionMode()) continue;

                    ArrayList<Material> usingMaterial = productionBuilding.getProducingMaterial();
                    ArrayList<Material> producingMaterial = productionBuilding.getProducingMaterial();

                    for (int i = 0; i < usingMaterial.size(); i++) {
                        governance.changeStorageStock(usingMaterial.get(i),
                                (-1) * productionBuilding.getRateOfUsage().get(i));
                    }
                    for (int i = 0; i < producingMaterial.size(); i++) {
                        governance.changeStorageStock(producingMaterial.get(i),
                                productionBuilding.getRateOfProduction().get(i));
                    }
                }
            }
        }
    }


    public String nextRound() {
        processUnitDecisions();
        applyUnitDecisions();
        Player currentPlayer = null;
        //TODO handle two or more player
        for (Player player : game.getPlayers()) {
            if (isPlayerDead(player)) {
                deletePlayer(player);
            } else currentPlayer = player;
        }
        if (game.getPlayers().size() == 1) {
            assert currentPlayer != null;
            deletePlayer(currentPlayer);
        }
        if (game.getDeadPlayers().size() == game.getMap().getPlayerCount()) return "endGame";
        return "continue";
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
                    if (asset instanceof MobileUnit)
                        processMovement(map, (MobileUnit) asset);
                    if (asset instanceof AttackingUnit)
                        processAttack((AttackingUnit) asset);
                }
            }
        }
    }

    private void processMovement(Map map, MobileUnit mobileUnit) {
        Vector2D pastCoordinate = mobileUnit.getCoordinate();
        if (mobileUnit.hasNextMoveDestination())
            mobileUnit.move();
        Vector2D newCoordinate = mobileUnit.getCoordinate();
        map.moveMapObject(pastCoordinate, newCoordinate, mobileUnit);
    }

    private void processAttack(AttackingUnit attackingAsset) {
        MapAsset targetUnit = attackingAsset.getNextRoundAttackTarget();
        if (targetUnit != null) {
            targetUnit.takeDamageFrom(attackingAsset);
            if (targetUnit.getHitPoint() < 0)
                eraseAsset(attackingAsset);
        }
    }

    private void eraseAsset(MapAsset asset) {
        game.getMap().removeMapObject(asset.getCoordinate(), asset);
        Player owner = asset.getOwner();
        if (owner != null)
            owner.getGovernance().removeAsset(asset);
    }

    private void processUnitDecisions() {
        Map map = game.getMap();
        for (int y = 0; y < map.getSize().y; y++) {
            for (int x = 0; x < map.getSize().x; x++) {
                for (MapAsset asset : map.getCell(new Vector2D(x, y)).getAllAssets()) {
                    if (asset instanceof AttackingUnit)
                        ((AttackingUnit) asset).processNextRoundMove(map);
                    if (asset instanceof MobileUnit)
                        ((MobileUnit) asset).findNextMoveDest(map);
                }
            }
        }
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
        selectedUnitController = new SelectedUnitController(selectedUnits, game);
        return GameMenuMessage.ENTER_UNIT_MENU;
    }

    public GameMenuMessage selectBuilding(int x, int y) {
        Vector2D coordinate = new Vector2D(x, y);
        if (!game.getMap().isInMap(coordinate))
            return GameMenuMessage.INVALID_COORDINATE;
        ArrayList<MapAsset> assets = game.getMap().getCell(coordinate).getAllAssets();
        for (MapAsset asset : assets) {
            if (!(asset instanceof Building))
                continue;
            if (!asset.getOwner().equals(game.getCurrentPlayer()))
                return GameMenuMessage.WRONG_OWNER;
            selectedBuildingController = new SelectedBuildingController((Building) asset, game);
            return GameMenuMessage.ENTER_BUILDING_MENU;
        }
        return GameMenuMessage.NO_BUILDING_IN_PLACE;
    }

    public String showGameInfo() {
        return "Round " + game.getRound() + ":\n" +
                game.getCurrentPlayer().getNickname() + "'s Turn";
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

    public String showPopularity() {
        return "Popularity: " + game.getCurrentPlayer().getGovernance().getTotalPopularity();
    }

    public String showFoodRate() {
        return "Current food rate: " + game.getCurrentPlayer().getGovernance().getFoodRate();
    }

    public String showFoodList() {
        StringBuilder output = new StringBuilder();
        HashMap<Material, Integer> list = game.getCurrentPlayer().getGovernance().getFoodList();
        for (java.util.Map.Entry<Material, Integer> entry : list.entrySet())
            output.append(entry.getKey().name().toLowerCase()).append(": ").append(entry.getValue()).append('\n');
        output.deleteCharAt(output.length() - 1);
        return output.toString();
    }


    public String showTaxRate() {
        return "Current tax rate: " + game.getCurrentPlayer().getGovernance().getTaxRate();
    }

    public GameMenuMessage setFearRate(int fearRate) {
        if (fearRate > 5 || fearRate < -5)
            return GameMenuMessage.INVALID_FEAR_RATE;
        game.getCurrentPlayer().getGovernance().setFearRate(fearRate);
        return GameMenuMessage.FEAR_RATE_CHANGE_SUCCESS;
    }

    public boolean isModifiable() {
        return game.isEditableMode();
    }

    private void deletePlayer(Player player) {
        int highScore = 0;
        highScore += player.getGovernance().getGold() / 100;
        highScore += player.getGovernance().getTotalPopularity() * 10;
        highScore += player.getGovernance().getBuildings().size() / 10;
        highScore += player.getGovernance().getUnits().size() / 10;
        highScore += (game.getDeadPlayers().size()) * 200;
        if (game.getPlayers().size() == 1) highScore += 3000;
        deleteAllAsset(player.getGovernance());
        game.getPlayers().remove(player);
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
}
