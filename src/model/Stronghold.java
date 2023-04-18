package model;

import java.util.ArrayList;
import java.util.HashMap;

public class Stronghold {
    public  static HashMap<String, User> users = new HashMap<>();
    public static ArrayList<Integer> rankings = new ArrayList<>();

    public static User getUserByUsername(String username) {
        return  users.get(username);
    }

    public static void addUser(User user) {
        users.put(user.getUsername(), user);
    }
    public static boolean isEmailExist(String email) {
        for (User user : users.values()) {
            if (user.getEmail().equalsIgnoreCase(email)) return true;
        }
        return false;
    }
    public static boolean doesUserExist(String username) {
        return users.containsKey(username);
    }
}
