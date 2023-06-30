package model.Lobby;

import java.util.ArrayList;

public class LobbyManager {

    public static ArrayList<GameRoom> getGameRooms() {
       return new ArrayList<GameRoom>();
    }

    public static GameRoom getGameRoomWithGameID(String gameID) {
        return new GameRoom(null, null,null,null);
    }
}
