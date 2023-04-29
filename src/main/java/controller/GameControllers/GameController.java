package controller.GameControllers;

import controller.MapControllers.BuildingPlacementController;
import controller.MapControllers.ChangeEnvironmentController;
import controller.MapControllers.ShowMapController;
import model.Game;
import model.User;
import view.GameMenus.GameMenu;

public class GameController {
    private User currentUser;
    private Game game;
    public GameController(User currentUser){
        this.currentUser = currentUser;
    }

    public void run(){
        GameMenu gameMenu = new GameMenu(this);
        while(true){
            switch (gameMenu.run()){
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
                    ShowMapController showMapController = new ShowMapController(currentUser);
                    showMapController.run();
                    break;
            }
        }
    }

}
