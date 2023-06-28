package view;

import controller.UserControllers.LoginController;
import javafx.application.Application;
import javafx.stage.Stage;
import model.Stronghold;
import view.UserMenus.LoginMenu;

public class Main extends Application {
    public static Stage mainStage;

    public static void main(String[] args) {
        Stronghold.load();
        launch(args);
    }


    @Override
    public void start(Stage stage) throws Exception {
        LoginMenu.setLoginController(new LoginController());
        mainStage = stage;
        new LoginMenu().start(stage);
    }


//    @Override
//    public void start(Stage stage) throws Exception {
//        HashMap<Color, Player> players = new HashMap<>();
//        players.put(Color.RED, new Player(Stronghold.getInstance().getUser("ayeen")));
//        players.put(Color.BLUE, new Player(Stronghold.getInstance().getUser("someone")));
//        Map map = MapManager.load("1001");
//        Game game = new Game(map, players, true);
//        GameController gameController = new GameController(Stronghold.getInstance().getUser("ayeen"), game);
//        GraphicGameMenu.setGameController(gameController);
//        GraphicGameMenu graphicGameMenu = new GraphicGameMenu();
//        GraphicGameMenu.setGraphicsController(new GraphicsController(gameController, game, graphicGameMenu));
//        GraphicBuildingPlacementMenu.setController(new BuildingPlacementController(game.getCurrentPlayer(), map, true));
//        SelectedBuildingController.setIsModifiable(true);
//        mainStage = stage;
//        graphicGameMenu.start(stage);
//    }
}
