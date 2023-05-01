package controller.GameControllers;

import controller.MapControllers.BuildingPlacementController;
import controller.MapControllers.ChangeEnvironmentController;
import controller.MapControllers.ShowMapController;
import model.Game;
import model.User;
import utils.Vector2D;
import view.GameMenus.GameMenu;
import view.enums.messages.GameMessage.GameMenuMessage;

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
                    TradeController tradeController = new TradeController(currentUser);
                    tradeController.run();
                    break;
                case "showMap":
                    showMapController.run();
                    break;
                case "marketMenu":
                    break;
            }
        }
    }

    public GameMenuMessage showMap(int x, int y){
        Vector2D coordinate = new Vector2D(x, y);
        if(!ShowMapController.isCenterValid(coordinate, game.getMap()))
            return GameMenuMessage.INVALID_COORDINATE;
        showMapController = new ShowMapController(game.getMap(), coordinate);
        return GameMenuMessage.ENTER_SHOW_MAP;
    }

}
