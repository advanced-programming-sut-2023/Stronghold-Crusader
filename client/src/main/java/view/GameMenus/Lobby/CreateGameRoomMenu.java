package view.GameMenus.Lobby;

import controller.GameControllers.GraphicsController;
import controller.MapControllers.MapSelectController;
import controller.UserControllers.MainController;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.Game.Game;
import model.Lobby.Lobby;
import model.Lobby.LobbyManager;
import model.Map.MapManager;
import model.enums.User.Color;
import view.Main;
import view.UserMenus.ProfileMenu;
import view.enums.messages.MapMessage.MapSelectMessage;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class CreateGameRoomMenu extends Application implements Initializable {
    private static MapSelectController mapSelectController;
    public TextField gameID;
    public Text gameIdError;
    public TextField mapID;
    public Text colorError;
    public Text mapIdError;
    public ImageView addMapButton;
    public ScrollPane mapPreview;
    public Circle colorCircle;
    public ImageView pickColor;
    public TextField pickedColor;
    public Text capacity;
    public Button createNewGameButton;
    public ArrayList<String> gameIds  = new ArrayList<>();
    @Override
    public void start(Stage stage) throws Exception {
        AnchorPane rootPane = FXMLLoader.load(LobbyMenu.class.getResource("/FXML/Gamefxml/Lobbyfxml/createGameRoom.fxml"));
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
        mapPreview.setOpacity(0.5);
        initializeGameIdsForRooms();
        createNewGameButton.setDisable(true);
        colorCircle.setVisible(false);
        gameID.textProperty().addListener((observableValue, oldText, newText) -> {
            if (gameIds.contains(gameID.getText())) gameIdError.setText("This id already taken");
            else gameIdError.setText("");
        });

        addMapButton.setOnMousePressed(e -> {
            addMap();
        });

        pickColor.setOnMousePressed(e -> {
            addColor();
        });

        createNewGameButton.setOnMouseClicked(e -> {
           createNewGameRoom();
        });

    }

    private void initializeGameIdsForRooms() {
        ArrayList<Lobby> lobbies = LobbyManager.getLobbies();
        if (lobbies != null) {
            for (Lobby lobby :lobbies) {
                gameIds.add(lobby.getId());
            }
        }
    }

    private void createNewGameRoom() {

        Lobby lobby = new Lobby(gameID.getText(), MainController.getCurrentUser(),
                Color.getColorWithSizeCheck(pickedColor.getText()), mapID.getText());
        LobbyManager.createLobby(lobby);
    }

    private void addColor() {
        if (mapSelectController.isColorValid(pickedColor.getText())) {
            colorCircle.setVisible(true);
            colorCircle.setFill(Paint.valueOf(pickedColor.getText()));
            pickedColor.setDisable(true);
            colorError.setText("");
            if (mapID.isDisable() && pickedColor.isDisable()) createNewGameButton.setDisable(false);
        } else {
            pickedColor.setText("");
            colorError.setText("invalid color");
        }
    }

    private void addMap() {
        MapSelectMessage msg = mapSelectController.selectMap(mapID.getText(), true);
        if (msg.equals(MapSelectMessage.MAP_SELECT_SUCCESS)) {
            GraphicsController graphicsController = new GraphicsController(
                    new Game(mapSelectController.getSelectedMap(), mapSelectController.getPlayers(), true));
            mapIdError.setText("");
            graphicsController.loadGraphics();
            mapPreview.setContent(graphicsController.getMainGrid());
            mapPreview.setOpacity(1);
            mapID.setDisable(true);
            capacity.setText(Integer.valueOf(MapManager.getMapPlayerCount(mapID.getText())).toString());
            if (mapID.isDisable() && pickedColor.isDisable()) createNewGameButton.setDisable(false);
        } else {
            mapID.setText("");
            mapIdError.setText("This id does not exist");
        }

    }

    public void back(MouseEvent mouseEvent) throws Exception {
        new LobbyMenu().start(Main.mainStage);
    }



    public static void setMapSelectController(MapSelectController mapSelectController) {
        CreateGameRoomMenu.mapSelectController = mapSelectController;
    }

}
