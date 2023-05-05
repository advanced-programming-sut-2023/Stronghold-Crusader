package controller.GameControllers;

import controller.MapControllers.BuildingPlacementController;
import controller.MapControllers.ChangeEnvironmentController;
import controller.MapControllers.ShowMapController;
import model.Game.Game;
import model.Game.Governance;
import model.User.User;
import model.enums.AssetType.Material;
import utils.Vector2D;
import view.GameMenus.GameMenu;
import view.enums.messages.GameMessage.GameMenuMessage;

import java.util.HashMap;
import java.util.Map;

public class GameController {
    private final User currentUser;
    private final Game game;
    private ShowMapController showMapController;

    public GameController(User currentUser, Game game) {
        this.currentUser = currentUser;
        this.game = game;
    }

    public String run() {
        GameMenu gameMenu = new GameMenu(this);
        while (true) {
            switch (gameMenu.run()) {
                case "changeEnvironment":
                    ChangeEnvironmentController environmentController = new ChangeEnvironmentController(game.getMap());
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
                    MarketController marketController = new MarketController(game.getCurrentPlayer(), game);
                    marketController.run();
                    break;
            }
        }
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
        return "Popularity: " + game.getCurrentPlayer().getGovernance().getPopularity();
    }

    public String showFoodRate() {
        return "Current food rate: " + game.getCurrentPlayer().getGovernance().getFoodRate();
    }

    public String showFoodList() {
        StringBuilder output = new StringBuilder();
        HashMap<Material, Integer> list = game.getCurrentPlayer().getGovernance().getFoodList();
        for (Map.Entry<Material, Integer> entry : list.entrySet())
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
            return GameMenuMessage.INVALID_TAX_RATE;
        game.getCurrentPlayer().getGovernance().setFearRate(fearRate);
        return GameMenuMessage.TAX_RATE_CHANGE_SUCCESS;
    }
}
