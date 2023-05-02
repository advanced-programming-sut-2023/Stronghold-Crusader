package model.User;

import Settings.Settings;
import com.google.gson.*;
import model.Stronghold;
import model.User.User;

import java.io.*;
import java.util.Collection;

public class UserManager {
    public static void load(Stronghold stronghold) {
        initializeResources();
        Reader reader;
        try {
            reader = new FileReader(Settings.USERS_PATH);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return;
        }
        Gson gson = new Gson();
        JsonObject jsonObject = gson.fromJson(reader, JsonObject.class);
        if (jsonObject == null)
            return;
        JsonArray usersArray = jsonObject.getAsJsonArray("users");
        for (JsonElement element : usersArray)
            stronghold.addUser(gson.fromJson(element, User.class));
    }

    @SuppressWarnings("ResultOfMethodCallIgnored")
    private static void initializeResources() {
        File resourceDir = new File(Settings.RESOURCE_PATH);
        if (!resourceDir.exists())
            resourceDir.mkdir();
        File mapsDir = new File(Settings.MAPS_PATH);
        if (!mapsDir.exists())
            mapsDir.mkdir();
        try {
            new File(Settings.USERS_PATH).createNewFile();
            new File(Settings.LOGGED_IN_USER_PATH).createNewFile();
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }

    public static void updateAllUsers(Collection<User> users) {
        Gson gson = new GsonBuilder().serializeNulls().create();
        JsonObject mainObject = new JsonObject();
        JsonArray usersArray = new JsonArray();
        for (User user : users)
            usersArray.add(gson.toJsonTree(user).getAsJsonObject());
        mainObject.add("users", usersArray);
        try {
            FileWriter fileWriter = new FileWriter(Settings.USERS_PATH);
            fileWriter.write(gson.toJson(mainObject));
            fileWriter.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static User getLoggedInUser() {
        Reader reader;
        try {
            reader = new FileReader(Settings.LOGGED_IN_USER_PATH);
            User user = new Gson().fromJson(reader, User.class);
            reader.close();
            return user;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void setLoggedInUser(User user) {
        try {
            FileWriter fileWriter = new FileWriter(Settings.LOGGED_IN_USER_PATH);
            fileWriter.write(new Gson().toJson(user));
            fileWriter.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
