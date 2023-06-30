package view.GameMenus.Lobby;

import controller.GameControllers.LobbyController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class LobbyMenu extends Application implements Initializable {
    private static LobbyController lobbyController;
    @Override
    public void start(Stage stage) throws Exception {
        AnchorPane rootPane = FXMLLoader.load(LobbyMenu.class.getResource("/FXML/Gamefxml/Lobbyfxml/lobbyMenu.fxml"));
        stage.setScene(new Scene(rootPane));
        stage.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public static void setLobbyController(LobbyController lobbyController) {
        LobbyMenu.lobbyController = lobbyController;
    }


}
