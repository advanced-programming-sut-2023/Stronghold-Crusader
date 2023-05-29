package model;

import model.User.User;
import model.User.UserManager;

import java.util.ArrayList;
import java.util.Collection;
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
        UserManager.updateAllUsers(users.values());
    }

    public User getUser(String username) {
        if (!users.containsKey(username)) return null;
        return users.get(username);
    }

    public void addUser(User user) {
        users.put(user.getUsername(), user);
        userRankings.add(user);
        updateData();
    }

    public void updateRankings() {
        userRankings.sort((o1, o2) -> o2.getHighScore() - o1.getHighScore());
    }

    public int getUserRank(User user) {
        for (int i = 0; i < userRankings.size(); i++) {
            if (userRankings.get(i).equals(user)) return i + 1;
        }
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

    public Collection<User> getUsers() {
        return users.values();
    }
}
