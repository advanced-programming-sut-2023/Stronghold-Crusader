package model;

import java.util.ArrayList;
import java.util.HashMap;

public class Stronghold {
    public static HashMap<String, User> users = new HashMap<>();
    public static ArrayList<User> userRankings = new ArrayList<>();

    public static User getUser(String username) {
        return users.get(username);
    }

    public static void addUser(User user) {
        users.put(user.getUsername(), user);
    }

    public static void updateRankings() {
        userRankings.sort(Stronghold::compareForRanks);
    }

    private static Integer compareForRanks(User u1, User u2) {
        return Integer.compare(u2.getHighScore(), u1.getHighScore());
    }

    public static int getUserRank(User user) {
        return userRankings.indexOf(user) + 1;
    }

    public static boolean emailExists(String email) {
        for (User user : users.values()) {
            if (user.getEmail().equalsIgnoreCase(email)) return true;
        }
        return false;
    }

    public static boolean userExists(String username) {
        return users.containsKey(username);
    }
}
