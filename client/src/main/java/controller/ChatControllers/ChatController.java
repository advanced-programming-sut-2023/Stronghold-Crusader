package controller.ChatControllers;

import model.User.User;
import model.chatRoom.Chat;
import model.chatRoom.ChatManager;
import model.chatRoom.Message;

public class ChatController {
    private Chat currentChat;
    private User currentUser;
    public ChatController(User currentUser){
        this.currentUser = currentUser;
    }

    public void setCurrentChat(Chat chat) {
        currentChat = chat;
    }

    public Chat getCurrentChat() {
        return currentChat;
    }

    public void updateChat(){
        if (currentChat != null)
            ChatManager.updateChat(currentChat, currentChat.getChatMode());
    }

    public void addMessage(Message msg){
        currentChat.addMessage(msg);
        // notify the server to send the message to all other users
    }

    public String getCurrentUsername() {
        return currentUser.getUsername();
    }
}
