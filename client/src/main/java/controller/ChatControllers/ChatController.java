package controller.ChatControllers;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import model.User.User;
import model.chatRoom.Chat;
import model.chatRoom.Message;
import network.Connection;
import network.Request;
import view.ChatMenus.MainChatMenu;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class ChatController {
    private Chat currentChat;
    private final User currentUser;
    public ChatController(User currentUser){
        this.currentUser = currentUser;
    }
    public static MainChatMenu currentMenu;

    public void setCurrentChat(Chat chat) {
        currentChat = chat;
    }

    public Chat getCurrentChat() {
        return currentChat;
    }

    public void addMessage(Message msg){
        currentChat.addMessage(msg);
    }

    public String getCurrentUsername() {
        return currentUser.getUsername();
    }

    public Chat getGlobalChat() {
        Request request = new Request();
        request.setType("chat");
        request.setCommand("get_global_chat");
        String in = Connection.getInstance().sendRequest(request);
        return new Gson().fromJson(in, Chat.class);
    }

    public ArrayList<Chat> loadRoomChats(){
        Request request = new Request();
        request.setType("chat");
        request.setCommand("get_room_chats");
        return getChats(request);
    }
    public ArrayList<Chat> loadPrivateChats(){
        Request request = new Request();
        request.setType("chat");
        request.setCommand("get_private_chats");
        return getChats(request);
    }

    private ArrayList<Chat> getChats(Request request) {
        String result = Connection.getInstance().sendRequest(request);
        Type arrayListType = new TypeToken<ArrayList<Chat>>() {
        }.getType();
        System.out.println();
        System.out.println(result);
        System.out.println();
        return new Gson().fromJson(result, arrayListType);
    }
}
