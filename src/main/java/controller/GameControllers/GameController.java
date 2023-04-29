package controller.GameControllers;

import controller.MapControllers.BuildingPlacementController;
import controller.MapControllers.ChangeEnvironmentController;
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
                    ChangeEnvironmentController environmentController = new ChangeEnvironmentController();
                    break;
                case "buildingPlacement":
                    BuildingPlacementController placementController = new BuildingPlacementController();
                    break;
                case "tradeMenu":
                    TradeController tradeController = new TradeController();
                    break;
                case "showMap":

                    break;
            }
        }
    }

}
