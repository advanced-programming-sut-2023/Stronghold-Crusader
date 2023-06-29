package controller.ChatControllers;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import model.User.User;
import model.chatRoom.Chat;
import model.chatRoom.Message;
import network.Connection;
import network.Request;

import java.util.ArrayList;

public class ChatController {
    private Chat currentChat;
    private final User currentUser;
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
        Request request = new Request();
        request.setType("chat");
        request.setCommand("get_global_chat");
        return new Gson().fromJson(Connection.getInstance().sendRequest(request), Chat.class);
    }

    public ArrayList<Chat> loadRoomChats(){
        Request request = new Request();
        request.setType("chat");
        request.setCommand("get_private_chats");
        Gson gson = new Gson();
        JsonObject jsonObject = gson.fromJson(Connection.getInstance().sendRequest(request), JsonObject.class);
        JsonArray chatsArray = jsonObject.getAsJsonArray("chats");
        ArrayList<Chat> privateChats = new ArrayList<>();
        for (JsonElement element : chatsArray)
            privateChats.add(gson.fromJson(element, Chat.class));
        return privateChats;
    }
    public ArrayList<Chat> loadPrivateChats(){
        Request request = new Request();
        request.setType("chat");
        request.setCommand("get_room_chats");
        Gson gson = new Gson();
        JsonObject jsonObject = gson.fromJson(Connection.getInstance().sendRequest(request), JsonObject.class);
        JsonArray chatsArray = jsonObject.getAsJsonArray("chats");
        ArrayList<Chat> roomChats = new ArrayList<>();
        for (JsonElement element : chatsArray)
            roomChats.add(gson.fromJson(element, Chat.class));
        return roomChats;
    }

}
