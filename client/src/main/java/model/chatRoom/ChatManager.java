package model.chatRoom;

import Settings.Settings;
import com.google.gson.*;
import model.Stronghold;


import java.io.*;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ChatManager {

    public static void load(){
        initializeChatFolder();
    }

    public static void initializeChatFolder(){
        File resourceDir = new File(Settings.CHAT_PATH);
        if (!resourceDir.exists())
            resourceDir.mkdir();
        resourceDir = new File(Settings.GLOBAL_CHAT_PATH);
        if (!resourceDir.exists()) {
            try {
                new File(Settings.GLOBAL_CHAT_PATH).createNewFile();
                ArrayList<String> users = new ArrayList<>();
                users.add("diba");
                Chat chat = new Chat(users, Chat.ChatMode.GLOBAL, "Chat", "diba");
                Gson gson = new GsonBuilder().serializeNulls().create();
                JsonObject mainObject = gson.toJsonTree(chat).getAsJsonObject();
                FileWriter fileWriter = new FileWriter(Settings.GLOBAL_CHAT_PATH);
                fileWriter.write(gson.toJson(mainObject));
                fileWriter.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
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

    public static ArrayList<Chat> loadPrivateChats(){
        ArrayList<Chat> chats = new ArrayList<>();
        File file = new File(Settings.PRIVATE_CHAT_PATH);
        File[] directoryListing = file.listFiles();
        if (directoryListing != null) {
            for (File child : directoryListing) {
                String fileName = child.getName();
                Pattern pattern = Pattern.compile("private(?<id>\\S+)\\.json");
                Matcher matcher = pattern.matcher(fileName);
                if (matcher.find()){
                    chats.add(loadChat(matcher.group("id"), Chat.ChatMode.PRIVATE));
                }
            }
        }
        return chats;
    }

    public static ArrayList<Chat> loadRoomChats(){
        ArrayList<Chat> chats = new ArrayList<>();
        File file = new File(Settings.ROOM_CHAT_PATH);
        File[] directoryListing = file.listFiles();
        if (directoryListing != null) {
            for (File child : directoryListing) {
                String fileName = child.getName();
                Pattern pattern = Pattern.compile("room(?<id>\\S+)\\.json");
                Matcher matcher = pattern.matcher(fileName);
                if (matcher.find()){
                    chats.add(loadChat(matcher.group("id"), Chat.ChatMode.ROOM));
                }
            }
        }
        return chats;
    }

    public static Chat loadGlobalChat(){
        Reader reader;
        try {
            reader = new FileReader(Settings.GLOBAL_CHAT_PATH);
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
