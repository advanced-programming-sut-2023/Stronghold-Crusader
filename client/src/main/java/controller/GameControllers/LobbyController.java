package controller.GameControllers;

import model.Lobby.Lobby;
import model.Lobby.LobbyManager;
import model.Lobby.LobbyStatus;
import model.User.User;
import model.enums.User.Color;

import java.util.Set;

public class LobbyController {
    private Lobby gameRoom;
    private final String gameId;

    public LobbyController(Lobby gameRoom) {
        this.gameRoom = gameRoom;
        gameId = gameRoom.getId();
    }

    public void updateGameRoom() {
        this.gameRoom = LobbyManager.getLobby(gameId);
    }

    public boolean isLobbyExist() {
        return gameRoom != null;
    }

    public void setAdmin(User admin) {
        gameRoom.setAdmin(admin);
    }


    public void removePlayer(User player) {
        if (gameRoom != null) {
            gameRoom.removePlayer(player);
        }
    }

    public void refresh() {
        gameRoom = LobbyManager.getLobby(gameRoom.getId());
    }

    public Set<User> getPlayers() {
        if (gameRoom == null) return null;
        return gameRoom.getPlayers();
    }

    public boolean isAdmin(User player) {
        if (gameRoom == null) return false;
        return player.getUsername().equals(gameRoom.getAdmin().getUsername());
    }

    public Color getColor(User player) {
        if (gameRoom == null) return null;
        return gameRoom.getColor(player);
    }

    public int getPlayersCount() {
        if (gameRoom == null) return 0;
        return gameRoom.getPlayersCount();
    }

    public User getRandomPlayerForAdmin() {
        int temp = getPlayersCount() - 1;
        int randomNumber = (int) (Math.random() * temp);
        temp = 0;
        for (User player : getPlayers()) {
            if (temp == randomNumber) return player;
            temp++;
        }
        return null;
    }

    public String getGameId() {
        if (gameRoom == null) return null;
        return gameId;
    }

    public LobbyStatus getLobbyStatus() {
        if (gameRoom == null) return null;
        return gameRoom.getLobbyStatus();
    }

    public void setStatus(LobbyStatus lobbyStatus) {
        gameRoom.setStatus(lobbyStatus);
        updateGameRoom();
    }

    public String getMapId(){
        return gameRoom.getMapId();
    }
}
