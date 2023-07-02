package controller.GameControllers;

import controller.ChatControllers.ChatController;
import controller.ChatControllers.ChatCreationController;
import controller.UserControllers.MainController;
import javafx.application.Platform;
import model.Lobby.Lobby;
import model.Lobby.LobbyManager;
import model.Lobby.LobbyStatus;
import model.User.Player;
import model.User.User;
import model.chatRoom.Chat;
import model.chatRoom.ChatManager;
import model.enums.User.Color;
import view.ChatMenus.MainChatMenu;
import view.ChatMenus.MainChatMenuController;
import view.Main;

import java.io.IOException;
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

    public void changePrivacy() {
        gameRoom.changePrivacy();
        updateGameRoom();
    }

    public String getMapId(){
        return gameRoom.getMapId();
    }

    public void goToChat() throws Exception {
        ChatCreationController controller = new ChatCreationController(MainController.currentUser);
        for (User user : getPlayers()) {
            controller.addUser(user.getUsername());
        }
        controller.setMode(Chat.ChatMode.ROOM);
        System.out.println(controller.CreateChat(gameId));
        ChatController chatController = new ChatController(MainController.currentUser);
        MainChatMenu chatMenu = new MainChatMenu();
        MainChatMenuController.setController(chatController);
        MainChatMenuController.currentChatMenu = chatMenu;
        chatMenu.start(Main.mainStage);
        MainChatMenuController menu = ChatController.currentMenu;
        Platform.runLater(() -> {
            try {
                menu.loadChat(ChatManager.loadChat("room" + gameId, Chat.ChatMode.ROOM));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }
}
