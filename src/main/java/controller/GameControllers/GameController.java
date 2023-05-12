package controller.GameControllers;

import controller.MapControllers.BuildingPlacementController;
import controller.MapControllers.ChangeEnvironmentController;
import controller.MapControllers.ShowMapController;
import model.Game.Game;
import model.Game.Governance;
import model.Map.Map;
import model.MapAsset.MapAsset;
import model.MapAsset.MobileUnit.AttackingUnit;
import model.MapAsset.MobileUnit.MobileUnit;
import model.User.Player;
import model.User.User;
import model.enums.AssetType.Material;
import utils.Vector2D;
import view.GameMenus.GameMenu;
import view.enums.messages.GameMessage.GameMenuMessage;

import java.util.ArrayList;
import java.util.HashMap;

public class GameController {
    private final User currentUser;
    private final Game game;
    private ShowMapController showMapController;

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
            }
        }
    }

    public void nextTurn() {
        game.nextTurn();
        Governance governance = game.getCurrentPlayer().getGovernance();
        governance.processPopulation();
        governance.payTax();
        governance.distributeFoods();
        //produce
        governance.calculatePopularity();
    }

    public void nextRound() {
        processUnitDecisions();
        applyUnitDecisions();
        //check for game end
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
                    if (!(asset instanceof AttackingUnit))
                        continue;
                    ((AttackingUnit) asset).processNextRoundMove(map);
                    ((MobileUnit) asset).findNextMoveDest(map);
                }
            }
        }
    }

    public String showGameInfo(){
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

    public GameMenuMessage setFoodRate(int foodRate) {
        if (foodRate > 2 || foodRate < -2)
            return GameMenuMessage.INVALID_FOOD_RATE;
        game.getCurrentPlayer().getGovernance().setFoodRate(foodRate);
        return GameMenuMessage.FOOD_RATE_CHANGE_SUCCESS;
    }

    public GameMenuMessage setTaxRate(int taxRate) {
        if (taxRate > 8 || taxRate < -3)
            return GameMenuMessage.INVALID_TAX_RATE;
        game.getCurrentPlayer().getGovernance().setTaxRate(taxRate);
        return GameMenuMessage.TAX_RATE_CHANGE_SUCCESS;
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
}
