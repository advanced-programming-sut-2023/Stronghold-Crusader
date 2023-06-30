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
import javafx.scene.text.Text;
import javafx.stage.Popup;
import javafx.stage.Stage;
import model.Lobby.Lobby;
import model.Lobby.LobbyManager;
import model.User.User;
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
        for (Lobby lobby: LobbyManager.getGameRooms()) {
            gameList.add(new LobbyTable(lobby));
        }
        lobbyTable.setItems(gameList);
    }

    public static void setLobbyController(LobbyController lobbyController) {
        LobbyMenu.lobbyController = lobbyController;
    }


    public void createNewGame(MouseEvent mouseEvent) {
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
            Lobby gameRoom = LobbyManager.getGameRoom(lobbyTable.getSelectionModel().getSelectedItem().getGameId());
            createInfoPopUp(gameRoom);
        }
    }

    private void createInfoPopUp(Lobby gameRoom) throws IOException {
        Popup popup = new Popup();
        popup.setAutoHide(true);
        createInfoPopUpPane(popup, gameRoom.getPlayers());
        popup.setAnchorY(250);
        popup.setAnchorX(500);
        popup.show(Main.mainStage);
    }

    private void createInfoPopUpPane(Popup popup, Set<User> players) throws IOException {
        AnchorPane info = FXMLLoader.load(LobbyMenu.class.getResource("/FXML/Gamefxml/Lobbyfxml/infoPopUp.fxml"));
        int counter = 2;
        for (User user: players) {
            ((Text) info.getChildren().get(counter))
                    .setText(user.getNickname());
            counter ++;
        }
        info.getChildren().get(10).setOnMousePressed(e -> {
            popup.hide();
        });
        popup.getContent().add(info);
    }
}
