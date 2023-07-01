package view.GameMenus.Lobby;

import controller.GameControllers.LobbyController;
import controller.MapControllers.MapSelectController;
import controller.UserControllers.MainController;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import javafx.stage.Popup;
import javafx.stage.Stage;
import javafx.util.Duration;
import model.Lobby.Lobby;
import model.Lobby.LobbyManager;
import model.Lobby.LobbyStatus;
import model.User.User;
import model.enums.User.Color;
import view.Main;
import view.UserMenus.MainMenu;
import view.UserMenus.ProfileMenu;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Set;

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
    public Text error;
    public TextField privateRoomJoining;
    public Button join;
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
        join.setOnMouseClicked(e -> {
            try {
                joinToGame(privateRoomJoining.getText());
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
            privateRoomJoining.setText("");
        });
        addGamesToTable();
    }

    private void joinToGame(String gameId) throws Exception {
        Lobby lobby = LobbyManager.getLobby(gameId);
        if (lobby == null) {
            error.setText("there is not any gameRoom with this ID");
            new Timeline(new KeyFrame(Duration.seconds(2), e -> {
                error.setText("");
            })).play();
        } else {
            if (lobby.getCapacity() == lobby.getPlayersCount() || lobby.getLobbyStatus().equals(LobbyStatus.RUNNING))
                LobbyTable.loadPopUp();
            else {
                Color color = LobbyTable.pickColor(lobby);
                lobby.addPlayer(MainController.getCurrentUser(), color);
                GameRoomMenu.setLobbyController(new LobbyController(lobby));
                new GameRoomMenu().start(Main.mainStage);
            }
        }
    }

    private void addGamesToTable() {
        gameList.clear();
        for (Lobby lobby : LobbyManager.getLobbies()) {
            if (!lobby.getLobbyStatus().equals(LobbyStatus.PRIVATE))
                gameList.add(new LobbyTable(lobby));
        }
        lobbyTable.setItems(gameList);
    }

    public static void setLobbyController(LobbyController lobbyController) {
        LobbyMenu.lobbyController = lobbyController;
    }


    public void createNewGame(MouseEvent mouseEvent) throws Exception {
        CreateGameRoomMenu.setMapSelectController(new MapSelectController(MainController.getCurrentUser()));
        new CreateGameRoomMenu().start(Main.mainStage);
    }

    public void refresh(MouseEvent mouseEvent) throws IOException {
        addGamesToTable();
    }

    public void back(MouseEvent mouseEvent) throws Exception {
        new MainMenu().start(Main.mainStage);
    }

    public void openInfo(MouseEvent mouseEvent) throws IOException {
        System.out.println(lobbyTable.getSelectionModel().getSelectedItem());
        if (lobbyTable.getSelectionModel().getSelectedItem() != null) {
            Lobby gameRoom = LobbyManager.getLobby(lobbyTable.getSelectionModel().getSelectedItem().getGameId());
            assert gameRoom != null;
            createInfoPopUp(gameRoom);
        }
    }

    private void createInfoPopUp(Lobby gameRoom) throws IOException {
        Popup popup = new Popup();
        popup.setAutoHide(true);
        createInfoPopUpPane(popup, gameRoom.getPlayers());
        popup.setAnchorY(250);
        popup.setAnchorX(525);
        popup.show(Main.mainStage);
    }

    private void createInfoPopUpPane(Popup popup, Set<User> players) throws IOException {
        AnchorPane info = FXMLLoader.load(LobbyMenu.class.getResource("/FXML/Gamefxml/Lobbyfxml/infoPopUp.fxml"));
        int counter = 2;
        for (User user : players) {
            ((Text) info.getChildren().get(counter))
                    .setText(user.getNickname());
            counter++;
        }
        info.getChildren().get(10).setOnMousePressed(e -> {
            popup.hide();
        });
        popup.getContent().add(info);
    }
}
