package model.User;

import Settings.Settings;
import com.google.gson.*;
import model.Stronghold;

import java.io.*;
import java.util.Collection;

public class UserManager {
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
