package model;

import java.util.ArrayList;
import java.util.HashMap;

public class Stronghold {
    private static Stronghold instance = null;
    public HashMap<String, User> users = new HashMap<>();
    public ArrayList<User> userRankings = new ArrayList<>();

    private Stronghold() {
    }

    public static void load() {
        if (instance == null)
            instance = new Stronghold();
    }

    public static Stronghold getInstance() {
        return instance;
    }

    private static Integer compareForRanks(User u1, User u2) {
        return Integer.compare(u2.getHighScore(), u1.getHighScore());
    }

    public User getUser(String username) {
        return users.get(username);
    }

    public void addUser(User user) {
        users.put(user.getUsername(), user);
    }

    public void updateRankings() {
        userRankings.sort(Stronghold::compareForRanks);
    }

    public int getUserRank(User user) {
        //TODO fix this
        return 0;
    }

    public boolean emailExists(String email) {
        for (User user : users.values()) {
            if (user.getEmail().equalsIgnoreCase(email)) return true;
        }
        return false;
    }

    public boolean userExists(String username) {
        return users.containsKey(username);
    }
}
