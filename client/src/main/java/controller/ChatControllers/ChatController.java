package controller.ChatControllers;

import model.chatRoom.Chat;
import model.chatRoom.ChatManager;
import model.chatRoom.Message;

public class ChatController {
    private Chat currentChat;
    public ChatController(){
        currentChat = ChatManager.loadGlobalChat();
    }

    public void changeCurrentChat(Chat chat) {
        updateChat();
        currentChat = chat;
    }

    public Chat getCurrentChat() {
        return currentChat;
    }

    public void updateChat(){
        ChatManager.updateChat(currentChat, currentChat.getChatMode());
    }

    public void addMessage(Message msg){
        currentChat.addMessage(msg);
        // notify the server to send the message to all other users
    }
}
