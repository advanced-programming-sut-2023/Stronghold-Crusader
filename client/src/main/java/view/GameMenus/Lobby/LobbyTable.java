package view.GameMenus.Lobby;

import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import model.Lobby.GameRoom;

public class LobbyTable {
    private final String gameId;
    private final String admin;
    private final String mapId;
    private final String capacity;
    private final ImageView television = new ImageView();
    private final Button join = new Button("join");

    public LobbyTable(GameRoom gameRoom) {
        addTelevision();
        gameId = gameRoom.getGameID();
        admin = gameRoom.getAdmin().getNickname();
        mapId = gameRoom.getMapId();
        capacity = Integer.valueOf(gameRoom.getPlayersCount()).toString() + "/" +
                Integer.valueOf(gameRoom.getCapacity()).toString();
        join.setOnMouseClicked(e -> {
            goToGameRoom(gameId);
        });
    }

    private void goToGameRoom(String gameId) {
        //TODO gameRoomMenu;
    }

    private void addTelevision() {
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
}
