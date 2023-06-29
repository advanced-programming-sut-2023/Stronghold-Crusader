package model.chatRoom;

import com.google.gson.Gson;
import network.Connection;
import network.Request;


import java.io.*;

public class ChatManager {

    public static String updateChat(Chat chat) {
        Request request = new Request();
        request.setType("chat");
        request.setCommand("update_chat");
        request.addParameter("chat", new Gson().toJson(chat));
        return Connection.getInstance().sendRequest(request);
    }

    public static Chat loadChat(String chatId, Chat.ChatMode mode) {
        Request request = new Request();
        request.setType("chat");
        request.setCommand("load_chat");
        request.addParameter("chat_id", chatId);
        request.addParameter("chat_type", new Gson().toJson(mode));
        return new Gson().fromJson(Connection.getInstance().sendRequest(request), Chat.class);
    }
}
