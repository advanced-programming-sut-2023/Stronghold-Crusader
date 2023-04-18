package model;

import java.util.ArrayList;
import java.util.HashMap;

public class Stronghold {
    public static HashMap<String, User> users;
    public static ArrayList<Integer> rankings;

    public static User getUserByUsername(String username) {
        return  users.get(username);
    }

    public static void addUser(User user) {
        users.put(user.getUsername(), user);
    }
    public static boolean isEmailExist(String email) {
        for (User user : users.values()) {
            if (user.getEmail().toLowerCase().matches(email.toLowerCase())) return true;
        }
        return false;
    }

}
