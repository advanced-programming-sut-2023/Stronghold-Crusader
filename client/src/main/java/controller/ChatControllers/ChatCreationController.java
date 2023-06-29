package controller.ChatControllers;

import model.Stronghold;
import model.User.User;
import model.chatRoom.Chat;
import model.chatRoom.ChatManager;
import view.enums.messages.ChatMessage.ChatMessage;

import java.util.ArrayList;

public class ChatCreationController {
    private Chat.ChatMode mode;
    private ArrayList<String> users;
    private User currentUser;
    private String chatID;

    public ChatCreationController(User user){
        this.currentUser = user;
        this.users = new ArrayList<>();
    }

    public void setMode(Chat.ChatMode mode) {
        this.mode = mode;
    }

    public void setChatID(String chatID) {
        this.chatID = chatID;
    }

    public ChatMessage addUser(String username){
        if (!Stronghold.getInstance().userExists(username)) return ChatMessage.USER_NOT_FOUND;
        else if (mode == null) return ChatMessage.SELECT_MODE;
        else if (mode.equals(Chat.ChatMode.PRIVATE) && users.size() == 1) return ChatMessage.CHAT_PARTICIPANT_LIMIT;
        else {
            users.add(username);
            return ChatMessage.USER_ADDED_SUCCESSFULLY;
        }
    }

    public ChatMessage CreateChat(){
        if (mode == null) return ChatMessage.SELECT_MODE;
        else if (users.size() == 0) return ChatMessage.SELECT_PARTICIPANT;
        else if (chatID == null) return ChatMessage.ENTER_CHAT_ID;
        else {
            Chat chat = new Chat(users, mode, chatID, currentUser.getUsername());
            ChatManager.updateChat(chat);
            return ChatMessage.CHAT_CREATED_SUCCESSFULLY;
        }
    }
}
