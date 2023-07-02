package view.GameMenus.Lobby;

import controller.GameControllers.GameController;
import controller.GameControllers.GraphicsController;
import controller.GameControllers.LobbyController;
import controller.GameControllers.SelectedBuildingController;
import controller.MapControllers.BuildingPlacementController;
import controller.UserControllers.MainController;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import model.Game.Game;
import model.Lobby.LobbyManager;
import model.Map.Map;
import model.Map.MapManager;
import model.Stronghold;
import model.User.Player;
import model.User.User;
import model.enums.User.Color;
import view.GameMenus.GraphicGameMenu;
import view.Main;
import view.MapMenus.dropBuildingMenu.GraphicBuildingPlacementMenu;
import view.UserMenus.ProfileMenu;

import java.net.URL;
import java.util.HashMap;
import java.util.ResourceBundle;

public class GameRoomMenu extends Application implements Initializable {
    private static LobbyController lobbyController;
    public TableView<GameRoomTable> lobbyTable;
    public TableColumn<GameRoomTable, Circle> avatarColumn;
    public TableColumn<GameRoomTable, String> nameColumn;
    public TableColumn<GameRoomTable, Circle> colorColumn;
    public Button refreshButton;
    public Button newGameButton;
    public CheckBox modifiabilityCheck;
    public Button privacyButton;
    private boolean editable = false;


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

    }

    @FXML
    public void initialize(URL url, ResourceBundle resourceBundle) {
        avatarColumn.setCellValueFactory(new PropertyValueFactory<GameRoomTable, Circle>("avatar"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<GameRoomTable, String>("nickname"));
        colorColumn.setCellValueFactory(new PropertyValueFactory<GameRoomTable, Circle>("color"));
        refreshButton.setOnMouseClicked(e -> {
            try {
                updateTable();
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
            newGameButton.setDisable(lobbyController.getPlayersCount() <= 1);
        });
        if (!lobbyController.isAdmin(MainController.getCurrentUser())) {
            modifiabilityCheck.setDisable(true);
            privacyButton.setVisible(false);
        }
        newGameButton.setDisable(lobbyController.getPlayersCount() <= 1);
        try {
            updateTable();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        privacyButton.setOnMouseClicked(e -> {
            changeGameRoomPrivacy();
            privacyButton.setText(lobbyController.getLobbyStatus().toString());
        });

        modifiabilityCheck.setOnMousePressed(e -> {
            editable = modifiabilityCheck.isSelected();
        });
    }

    private void changeGameRoomPrivacy() {
        lobbyController.changePrivacy();
    }

    private void updateTable() throws Exception {
        players.clear();
        lobbyController.updateGameRoom();
        if (!lobbyController.isLobbyExist()) exitFromRoom();
        else {

            for (User player : lobbyController.getPlayers()) {
                players.add(new GameRoomTable(player, lobbyController.isAdmin(player), lobbyController.getColor(player).toString()));
            }
            lobbyTable.setItems(players);
        }
    }

    public static void setLobbyController(LobbyController lobbyController) {
        GameRoomMenu.lobbyController = lobbyController;
    }

    public void back(MouseEvent mouseEvent) throws Exception {
        exitFromRoom();
    }

    private void exitFromRoom() throws Exception {
        lobbyController.removePlayer(MainController.getCurrentUser());
        if (lobbyController.getPlayersCount() == 0 && lobbyController.isLobbyExist())
            LobbyManager.deleteLobby(lobbyController.getGameId());
        else if (lobbyController.isAdmin(MainController.getCurrentUser()))
            lobbyController.setAdmin(lobbyController.getRandomPlayerForAdmin());
        newGameButton.setDisable(lobbyController.getPlayersCount() <= 1);
        new LobbyMenu().start(Main.mainStage);
    }

    public void createGame() throws Exception {
        if (!lobbyController.isAdmin(MainController.currentUser)) return;
        HashMap<Color, Player> players = new HashMap<>();
        for (User user : lobbyController.getPlayers())
            players.put(lobbyController.getColor(user), new Player(user));
        Map map = MapManager.load(lobbyController.getMapId());
        Game game = new Game(map, players, editable, lobbyController.getGameId());
        GameController gameController = new GameController(MainController.getCurrentUser(), game);
        GraphicGameMenu.setGameController(gameController);
        GraphicGameMenu graphicGameMenu = new GraphicGameMenu();
        GraphicGameMenu.setGraphicsController(new GraphicsController(gameController, game, graphicGameMenu));
        GraphicBuildingPlacementMenu.setController(new BuildingPlacementController(game.getCurrentPlayer(), map, editable));
        SelectedBuildingController.setIsModifiable(true);
        graphicGameMenu.start(Main.mainStage);
    }


}
