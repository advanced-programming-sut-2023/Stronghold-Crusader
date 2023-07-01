package controller.GameControllers;

import model.Lobby.Lobby;
import model.Lobby.LobbyManager;
import model.Lobby.LobbyStatus;
import model.User.User;

import java.util.Set;

public class LobbyController {
    private Lobby gameRoom;
    private String gameId;

    public LobbyController(Lobby gameRoom) {
        this.gameRoom = gameRoom;
        gameId = gameRoom.getId();
    }

    public void updateGameRoom() {
        this.gameRoom = LobbyManager.getLobby(gameId);
    }
    public boolean isLobbyExist(){
        return gameRoom != null;
    }

    public void setAdmin(User admin) {
        gameRoom.setAdmin(admin);
    }


    public void removePlayer(User player) {
        gameRoom.removePlayer(player);
    }

    public void refresh() {
        gameRoom = LobbyManager.getLobby(gameRoom.getId());
    }

    public Set<User> getPlayers() {
        return gameRoom.getPlayers();
    }

    public boolean isAdmin(User player) {

        return player.getUsername().equals(gameRoom.getAdmin().getUsername());
    }

    public String getColor(User player) {
        return gameRoom.getColor(player);
    }

    public int getPlayersCount() {
        return gameRoom.getPlayersCount();
    }
    public User getRandomPlayerForAdmin(){
        int temp = getPlayersCount() - 1;
        int randomNumber = (int)(Math.random() * temp);
        temp = 0;
        for (User player: getPlayers()) {
            if (temp ==  randomNumber) return player;
            temp ++;
        }
        return null;
    }

    public String getGameId() {
        return gameId;
    }
    public LobbyStatus getLobbyStatus(){
        return gameRoom.getLobbyStatus();
    }

    public void changePrivacy(){
        gameRoom.changePrivacy();
        updateGameRoom();
    }


}
