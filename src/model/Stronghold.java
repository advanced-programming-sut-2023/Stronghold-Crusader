package model;

import java.util.ArrayList;
import java.util.HashMap;

public class Stronghold {
    public static HashMap<String, User> users;
    public static ArrayList<User> rankings;

    public static User getUserByUsername(String username) {
        return  users.get(username);
    }

    public static void addUser(User user) {
        users.put(user.getUsername(), user);
    }

    public static int getUserRank(User user){
        return rankings.indexOf(user) + 1;
    }
}
