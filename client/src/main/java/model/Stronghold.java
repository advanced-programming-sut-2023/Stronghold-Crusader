package model;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import model.User.User;
import network.Connection;
import network.Request;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;

public class Stronghold {
    private static Stronghold instance = null;

    private Stronghold() {
    }

    public static Stronghold getInstance() {
        if (instance == null)
            instance = new Stronghold();
        return instance;
    }

    public User getUser(String username) {
        Request request = new Request();
        request.setType("user_query");
        request.setCommand("get_user");
        request.addParameter("username", username);
        String result = Connection.getInstance().sendRequest(request);
        if (result.startsWith("400")) return null;
        return new Gson().fromJson(result, User.class);
    }

    public boolean isUserOnline(String username) {
        Request request = new Request();
        request.setType("user_query");
        request.setCommand("user_online");
        request.addParameter("username", username);
        String result = Connection.getInstance().sendRequest(request);
        return Boolean.parseBoolean(result);
    }

    public void addUser(User user) {
        Request request = new Request();
        request.setType("user_change");
        request.setCommand("add");
        request.addParameter("user", new Gson().toJson(user));
        Connection.getInstance().sendRequest(request);
    }

    public int getUserRank(User user) {
        Request request = new Request();
        request.setType("user_query");
        request.setCommand("get_user_rank");
        request.addParameter("username", user.getUsername());
        String result = Connection.getInstance().sendRequest(request);
        return Integer.parseInt(result);
    }

    public boolean emailExists(String email) {
        Request request = new Request();
        request.setType("user_query");
        request.setCommand("email_exists");
        request.addParameter("email", email);
        return Boolean.parseBoolean(Connection.getInstance().sendRequest(request));
    }

    public boolean userExists(String username) {
        Request request = new Request();
        request.setType("user_query");
        request.setCommand("user_exists");
        request.addParameter("username", username);
        return Boolean.parseBoolean(Connection.getInstance().sendRequest(request));
    }

    public Collection<User> getUsers() {
        Request request = new Request();
        request.setType("user_query");
        request.setCommand("get_users");
        String result = Connection.getInstance().sendRequest(request);
        Type arrayListType = new TypeToken<ArrayList<User>>() {
        }.getType();
        return new Gson().fromJson(result, arrayListType);
    }

    public Collection<User> getOnlineUsers() {
        Request request = new Request();
        request.setType("user_query");
        request.setCommand("get_online_users");
        String result = Connection.getInstance().sendRequest(request);
        Type arrayListType = new TypeToken<ArrayList<User>>() {
        }.getType();
        return new Gson().fromJson(result, arrayListType);
    }

    public ArrayList<User> getUserRankings() {
        Request request = new Request();
        request.setType("user_query");
        request.setCommand("get_rankings");
        String result = Connection.getInstance().sendRequest(request);
        Type arrayListType = new TypeToken<ArrayList<User>>() {
        }.getType();
        return new Gson().fromJson(result, arrayListType);
    }
}
