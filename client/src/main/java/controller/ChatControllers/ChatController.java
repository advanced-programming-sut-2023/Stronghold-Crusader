package controller.ChatControllers;

import com.google.gson.Gson;
import model.User.User;
import model.chatRoom.Chat;
import model.chatRoom.Message;
import network.Connection;
import network.Request;

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
        if (currentChat != null){
            Request request = new Request();
            request.setType("chat");
            request.setCommand("update_chat");
            request.addParameter("chat", new Gson().toJson(currentChat));
            request.addParameter("chat_type", new Gson().toJson(currentChat.getChatMode()));
            Connection.getInstance().sendRequest(request);
        }
    }

    public void addMessage(Message msg){
        currentChat.addMessage(msg);
        // notify the server to send the message to all other users
    }

    public String getCurrentUsername() {
        return currentUser.getUsername();
    }

    public Chat getGlobalChat(){

    }
}
