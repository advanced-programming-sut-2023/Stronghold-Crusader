package controller.GameControllers;

import model.Lobby.Lobby;
import model.Lobby.LobbyManager;
import model.User.User;

import java.util.Set;

public class LobbyController {
    private Lobby gameRoom;

    public LobbyController(Lobby gameRoom) {
        this.gameRoom = gameRoom;
    }

    public void setGameRoom(Lobby gameRoom) {
        this.gameRoom = gameRoom;
    }

    public void setAdmin(User admin){
        gameRoom.setAdmin(admin);
    }


    public void removePlayer(User player){
        gameRoom.removePlayer(player);
    }

    public void refresh(){
        gameRoom = LobbyManager.getLobby(gameRoom.getId());
    }

    public Set<User> getPlayers() {
        return gameRoom.getPlayers();
    }

    public boolean isAdmin(User player) {
        return player.equals(gameRoom.getAdmin());
    }

    public String getColor(User player) {
        return gameRoom.getColor(player);
    }

    public int getPlayersCount(){
        return gameRoom.getPlayersCount();
    }
}
