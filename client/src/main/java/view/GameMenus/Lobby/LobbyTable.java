package view.GameMenus.Lobby;

import controller.GameControllers.LobbyController;
import controller.UserControllers.MainController;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.stage.Popup;
import model.Lobby.Lobby;
import model.Lobby.LobbyManager;
import model.enums.User.Color;
import view.Main;

import java.io.IOException;

public class LobbyTable {
    private final String gameId;
    private final String admin;
    private final String mapId;
    private final String capacity;
    private final ImageView television = new ImageView();
    private final Circle avatar = new Circle();
    private final Button join = new Button("join");

    public LobbyTable(Lobby gameRoom) {
        avatar.setFill(new ImagePattern(new Image(gameRoom.getAdmin().getAvatarPath())));
        avatar.setRadius(25);
        addTelevision();
        gameId = gameRoom.getId();
        admin = gameRoom.getAdmin().getNickname();
        mapId = gameRoom.getMapId();
        capacity = Integer.valueOf(gameRoom.getPlayersCount()).toString() + "/" +
                Integer.valueOf(gameRoom.getCapacity()).toString();
        join.setOnMouseClicked(e -> {
            try {
                goToGameRoom(gameId);
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        });
    }

    private void goToGameRoom(String gameId) throws Exception {
       Lobby lobby = LobbyManager.getLobby(gameId);
       if (lobby == null || lobby.getCapacity() == lobby.getPlayersCount()) {
           loadPopUp();
       } else {
           Color color = pickColor(lobby);
           lobby.addPlayer(MainController.getCurrentUser(), color);
           GameRoomMenu.setLobbyController(new LobbyController(lobby));
           new GameRoomMenu().start(Main.mainStage);
       }
    }

    private Color pickColor(Lobby lobby) {
        Color choosedColor = null;
        for (Color color: Color.values()) {
            choosedColor = Color.getColorWithSizeCheck(color.toString(), lobby.getCapacity());
            if (choosedColor != null && !lobby.isColorPicked(color)) return choosedColor;
        }
        return null;
    }

    private void loadPopUp() throws IOException {
        Popup popup = new Popup();
        AnchorPane pane = FXMLLoader.load(LobbyTable.class.getResource("/FXML/Gamefxml/Lobbyfxml/JoiningErrorPopUp.fxml"));
        popup.setAutoHide(true);
        popup.getContent().add(pane);
        popup.setAnchorX(525);
        popup.setAnchorY(250);
        popup.show(Main.mainStage);
    }

    private void addTelevision() {
        television.setFitHeight(35);
        television.setFitWidth(35);
        television.setImage(new Image(LobbyTable.class.getResource("/assets/icons/television.png").toString()));
        television.setOnMouseClicked(e -> {
            //TODO add live
        });
    }

    public String getGameId() {
        return gameId;
    }

    public String getAdmin() {
        return admin;
    }

    public String getMapId() {
        return mapId;
    }

    public String getCapacity() {
        return capacity;
    }

    public ImageView getTelevision() {
        return television;
    }

    public Button getJoin() {
        return join;
    }

    public Circle getAvatar() {
        return avatar;
    }
}
