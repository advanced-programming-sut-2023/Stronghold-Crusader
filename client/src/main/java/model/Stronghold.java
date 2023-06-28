package model;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import model.User.User;
import model.User.UserManager;
import network.Connection;
import network.Request;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class Stronghold {
    private static Stronghold instance = null;

    private Stronghold() {
    }

    public static Stronghold getInstance() {
        if(instance == null)
            instance = new Stronghold();
        return instance;
    }

    public User getUser(String username) {
        Request request = new Request();
        request.setType("users_query");
        request.setCommand("get_user");
        request.addParameter("username", username);
        String result = Connection.getInstance().sendRequest(request);
        if (result.equals("no_user")) return null;
        return new Gson().fromJson(result, User.class);
    }

    public void addUser(User user) {
        Request request = new Request();
        request.setType("users_query");
        request.setCommand("add_user");
        request.addParameter("user", new Gson().toJson(user));
        Connection.getInstance().sendRequest(request);
    }

    public int getUserRank(User user) {
        Request request = new Request();
        request.setType("users_query");
        request.setCommand("get_user_rank");
        request.addParameter("username", user.getUsername());
        String result = Connection.getInstance().sendRequest(request);
        return Integer.parseInt(result);
    }

    public boolean emailExists(String email) {
        Request request = new Request();
        request.setType("users_query");
        request.setCommand("email_exists");
        request.addParameter("email", email);
        return Boolean.parseBoolean(Connection.getInstance().sendRequest(request));
    }

    public boolean userExists(String username) {
        Request request = new Request();
        request.setType("users_query");
        request.setCommand("user_exists");
        request.addParameter("username", username);
        return Boolean.parseBoolean(Connection.getInstance().sendRequest(request));
    }

    public Collection<User> getUsers() {
        Request request = new Request();
        request.setType("users_query");
        request.setCommand("get_users");
        String result = Connection.getInstance().sendRequest(request);
        Type arrayListType = new TypeToken<ArrayList<User>>() {}.getType();
        return new Gson().fromJson(result, arrayListType);
    }

    public ArrayList<User> getUserRankings() {
        Request request = new Request();
        request.setType("users_query");
        request.setCommand("get_rankings");
        String result = Connection.getInstance().sendRequest(request);
        Type arrayListType = new TypeToken<ArrayList<User>>() {}.getType();
        return new Gson().fromJson(result, arrayListType);
    }
}
