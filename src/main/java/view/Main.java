package view;

import controller.GameControllers.GameController;
import controller.GameControllers.GraphicsController;
import javafx.application.Application;
import javafx.stage.Stage;
import model.ConstantManager;
import model.Game.Game;
import model.Map.MapManager;
import model.Stronghold;
import model.User.Player;
import model.enums.User.Color;
import view.GameMenus.GraphicGameMenu;

import java.util.HashMap;

public class Main extends Application {
    public static Stage mainStage;
    public static void main(String[] args) throws Exception {
        Stronghold.load();
        ConstantManager.load();
        launch(args);
    }

//    @Override
//    public void start(Stage stage) throws Exception {
//        LoginMenu.setLoginController(new LoginController());
//        new LoginMenu().start(stage);
//    }

    @Override
    public void start(Stage stage) throws Exception {
        HashMap<Color, Player> players = new HashMap<>();
        players.put(Color.RED, new Player(Stronghold.getInstance().getUser("ayeen")));
        players.put(Color.BLUE, new Player(Stronghold.getInstance().getUser("kian")));
        Game game = new Game(MapManager.load("1001"), players, true);
        GraphicGameMenu.setGameController(new GameController(Stronghold.getInstance().getUser("ayeen"), game));
        GraphicGameMenu.setGraphicsController(new GraphicsController(game));
        new GraphicGameMenu().start(stage);
    }
}
