package controller.GameControllers;

import controller.MapControllers.BuildingPlacementController;
import controller.MapControllers.ChangeEnvironmentController;
import controller.MapControllers.ShowMapController;
import model.Game;
import model.User;
import utils.Vector2D;
import view.GameMenus.GameMenu;

public class GameController {
    private User currentUser;
    private Game game;

    public GameController(User currentUser, Game game) {
        this.currentUser = currentUser;
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
                    BuildingPlacementController controller = new BuildingPlacementController(currentUser, game.getMap());
                    controller.run();
                    break;
                case "tradeMenu":
                    TradeController tradeController = new TradeController(currentUser);
                    tradeController.run();
                    break;
                case "showMap":
                    //TODO this command takes a center coordinate variable and should get passed to the constructor
                    //the center coordinate should be within map range otherwise don't run these
                    //for validating the map range call ShowMapController.isCenterValid()
                    ShowMapController showMapController = new ShowMapController(
                            game.getMap(), new Vector2D(0, 0));
                    showMapController.run();
                    break;
            }
        }
    }

}
