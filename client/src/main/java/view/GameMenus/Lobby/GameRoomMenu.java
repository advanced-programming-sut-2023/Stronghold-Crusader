package view.GameMenus.Lobby;

import controller.GameControllers.LobbyController;
import controller.UserControllers.MainController;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import model.User.User;
import view.Main;
import view.UserMenus.ProfileMenu;

import java.net.URL;
import java.util.ResourceBundle;

public class GameRoomMenu extends Application implements Initializable {
    private static LobbyController lobbyController;
    public TableView<GameRoomTable> lobbyTable;
    public TableColumn<GameRoomTable, Circle> avatarColumn;
    public TableColumn<GameRoomTable, String> nameColumn;
    public TableColumn<GameRoomTable, Circle> colorColumn;
    public Button refreshButton;
    public Button newGameButton;


    private ObservableList<GameRoomTable> players = FXCollections.observableArrayList();

    @Override
    public void start(Stage stage) throws Exception {
        AnchorPane rootPane = FXMLLoader.load(LobbyMenu.class.getResource("/FXML/Gamefxml/Lobbyfxml/gameRoom.fxml"));
        stage.setScene(new Scene(rootPane));
        stage.setFullScreen(true);
        rootPane.setBackground(new Background(new BackgroundImage(new Image(
                ProfileMenu.class.getResource("/assets/backgrounds/lobbyBackGround.jpg").toExternalForm()),
                BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER,
                new BackgroundSize(1, 1, true, true, false, false))));
        stage.show();
    }

    @FXML
    public void initialize(URL url, ResourceBundle resourceBundle) {
        avatarColumn.setCellValueFactory(new PropertyValueFactory<GameRoomTable, Circle>("avatar"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<GameRoomTable, String>("nickname"));
        colorColumn.setCellValueFactory(new PropertyValueFactory<GameRoomTable, Circle>("color"));
        refreshButton.setOnMouseClicked(e -> {
            newGameButton.setDisable(lobbyController.getPlayersCount() <= 1);
            updateTable();
        });
        newGameButton.setDisable(lobbyController.getPlayersCount() <= 1);
        updateTable();

    }

    private void updateTable() {
        players.clear();
        for (User player : lobbyController.getPlayers()) {
            players.add(new GameRoomTable(player, lobbyController.isAdmin(player), lobbyController.getColor(player)));
        }
        lobbyTable.setItems(players);
    }

    public static void setLobbyController(LobbyController lobbyController) {
        GameRoomMenu.lobbyController = lobbyController;
    }

    public void back(MouseEvent mouseEvent) throws Exception {
        lobbyController.removePlayer(MainController.getCurrentUser());
        new LobbyMenu().start(Main.mainStage);
    }

    public void createGame(MouseEvent mouseEvent) {

    }


}
