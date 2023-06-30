package view.GameMenus.Lobby;

import controller.GameControllers.LobbyController;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import view.UserMenus.ProfileMenu;

import java.net.URL;
import java.util.ResourceBundle;

public class LobbyMenu extends Application implements Initializable {
    private static LobbyController lobbyController;
    public TableView<LobbyTable> lobbyTable;
    public TableColumn<LobbyTable, String> gameIdColumn;
    public TableColumn<LobbyTable, Circle> avatarColumn;
    public TableColumn<LobbyTable, String> adminColumn;
    public TableColumn<LobbyTable, String> mapIdColumn;
    public TableColumn<LobbyTable, String> capacityColumn;
    public TableColumn<LobbyTable, ImageView> televisionColumn;
    public TableColumn<LobbyTable, Button> joinColumn;
    private ObservableList<LobbyTable> gameList = FXCollections.observableArrayList();


    @Override
    public void start(Stage stage) throws Exception {
        AnchorPane rootPane = FXMLLoader.load(LobbyMenu.class.getResource("/FXML/Gamefxml/Lobbyfxml/lobbyMenu.fxml"));
        stage.setScene(new Scene(rootPane));
        stage.setFullScreen(true);
        rootPane.setBackground(new Background(new BackgroundImage(new Image(
                ProfileMenu.class.getResource("/assets/backgrounds/lobbyBackGround.jpg").toExternalForm()),
                BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER,
                new BackgroundSize(1, 1, true, true, false, false))));
        stage.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        gameIdColumn.setCellValueFactory(new PropertyValueFactory<LobbyTable, String>("gameId"));
        avatarColumn.setCellValueFactory(new PropertyValueFactory<LobbyTable, Circle>("avatar"));
        adminColumn.setCellValueFactory(new PropertyValueFactory<LobbyTable, String>("admin"));
        mapIdColumn.setCellValueFactory(new PropertyValueFactory<LobbyTable, String>("mapId"));
        capacityColumn.setCellValueFactory(new PropertyValueFactory<LobbyTable, String>("capacity"));
        televisionColumn.setCellValueFactory(new PropertyValueFactory<LobbyTable, ImageView>("television"));
        joinColumn.setCellValueFactory(new PropertyValueFactory<LobbyTable, Button>("join"));

        addGamesToTable();
    }

    private void addGamesToTable() {
        gameList.clear();
        lobbyTable.setItems(gameList);
    }

    public static void setLobbyController(LobbyController lobbyController) {
        LobbyMenu.lobbyController = lobbyController;
    }


    public void createNewGame(MouseEvent mouseEvent) {
    }
}
