package view;

import controller.GameControllers.GameController;
import controller.GameControllers.GraphicsController;
import controller.GameControllers.SelectedBuildingController;
import controller.MapControllers.BuildingPlacementController;
import javafx.application.Application;
import javafx.stage.Stage;
import model.ConstantManager;
import model.Game.Game;
import model.Map.Map;
import model.Map.MapManager;
import model.Stronghold;
import model.User.Player;
import model.enums.User.Color;
import network.Connection;
import view.GameMenus.GraphicGameMenu;
import view.MapMenus.dropBuildingMenu.GraphicBuildingPlacementMenu;

import java.util.HashMap;

public class newMain extends Application {
    public static Stage mainStage;

    public static void main(String[] args) throws Exception {
        Connection.connect("localhost", 8080);
        ConstantManager.load();
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        HashMap<Color, Player> players = new HashMap<>();
        players.put(Color.RED, new Player(Stronghold.getInstance().getUser("diba")));
        players.put(Color.BLUE, new Player(Stronghold.getInstance().getUser("kian")));
        Map map = MapManager.load("1001");
        Game game = new Game(map, players, true, "test");
        GameController gameController = new GameController(Stronghold.getInstance().getUser("diba"), game);
        GraphicGameMenu.setGameController(gameController);
        GraphicGameMenu graphicGameMenu = new GraphicGameMenu();
        GraphicGameMenu.setGraphicsController(new GraphicsController(gameController, game, graphicGameMenu));
        GraphicBuildingPlacementMenu.setController(new BuildingPlacementController(game.getCurrentPlayer(), map, true));
        SelectedBuildingController.setIsModifiable(true);
        mainStage = stage;
        stage.setFullScreen(true);
        graphicGameMenu.start(stage);
    }
}
