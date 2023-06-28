package model.chatRoom;

import Settings.Settings;
import com.google.gson.*;
import model.User.User;


import java.io.*;

public class ChatManager {

    public static void load(){
        initializeChatFolder();
    }

    public static void initializeChatFolder(){
        File resourceDir = new File(Settings.CHAT_PATH);
        if (!resourceDir.exists())
            resourceDir.mkdir();
        resourceDir = new File(Settings.GLOBAL_CHAT_PATH);
        if (!resourceDir.exists())
            resourceDir.mkdir();
        resourceDir = new File(Settings.PRIVATE_CHAT_PATH);
        if (!resourceDir.exists())
            resourceDir.mkdir();
        resourceDir = new File(Settings.ROOM_CHAT_PATH);
        if (!resourceDir.exists())
            resourceDir.mkdir();
    }

    public static void updateChat(Chat chat, Chat.ChatMode mode) {
        Gson gson = new GsonBuilder().serializeNulls().create();
        JsonObject mainObject = gson.toJsonTree(chat).getAsJsonObject();
        try {
            String chatPath = "";
            switch (mode){
                case ROOM:
                    chatPath = Settings.ROOM_CHAT_PATH + chat.getChatId() + ".json";
                    break;
                case GLOBAL:
                    chatPath = Settings.GLOBAL_CHAT_PATH + chat.getChatId() + ".json";
                    break;
                case PRIVATE:
                    chatPath = Settings.PRIVATE_CHAT_PATH + chat.getChatId() + ".json";
                    break;
            }
            File file = new File(chatPath);
            if (!file.exists()) file.createNewFile();
            FileWriter fileWriter = new FileWriter(chatPath);
            fileWriter.write(gson.toJson(mainObject));
            fileWriter.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static Chat loadChat(String chatId, Chat.ChatMode mode){
        String chatPath = "";
        switch (mode){
            case ROOM:
                chatPath = Settings.ROOM_CHAT_PATH + mode.name().toLowerCase() + chatId + ".json";
                break;
            case GLOBAL:
                chatPath = Settings.GLOBAL_CHAT_PATH + mode.name().toLowerCase() + chatId + ".json";
                break;
            case PRIVATE:
                chatPath = Settings.PRIVATE_CHAT_PATH + mode.name().toLowerCase() + chatId + ".json";
                break;
        }
        Reader reader;
        try {
            reader = new FileReader(chatPath);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        }
        Gson gson = new Gson();
        JsonObject jsonObject = gson.fromJson(reader, JsonObject.class);
        if (jsonObject == null)
            return null;
        return gson.fromJson(jsonObject, Chat.class);
    }
}
