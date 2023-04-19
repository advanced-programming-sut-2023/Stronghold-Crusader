package model;

import java.util.ArrayList;
import java.util.HashMap;

public class Stronghold {
    private static Stronghold instance = null;
    private final HashMap<String, User> users = new HashMap<>();
    private final ArrayList<User> userRankings = new ArrayList<>();

    private Stronghold() {
    }

    public static void load() {
        if (instance == null) {
            instance = new Stronghold();
            UserManager.load(instance);
        }
    }

    public static Stronghold getInstance() {
        return instance;
    }

    public void updateData() {
        UserManager.updateJson(users.values());
    }

    public User getUser(String username) {
        return users.get(username);
    }

    public void addUser(User user) {
        users.put(user.getUsername(), user);
        userRankings.add(user);
        // do we need to call updateRankings() here?
        updateData();
    }

    public void updateRankings() {
        userRankings.sort(User::compareForRanks);
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
