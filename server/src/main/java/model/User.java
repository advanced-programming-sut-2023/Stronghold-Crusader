package model;

import database.Database;
import utils.Pair;

import java.net.Socket;
import java.util.ArrayList;

@SuppressWarnings("FieldCanBeLocal")
public class User {
    private transient Socket socket;
    private long lastOnlineTime;
    private String username;
    private String password;
    private String nickname;
    private String slogan;
    private String email;
    private Pair passwordRecovery;
    private String avatarPath;
    private ArrayList<String> friends;
    private ArrayList<String> senders;
    private int highScore;
    private ArrayList<String> mapList;


    public String getUsername() {
        return username;
    }

    public int getHighScore() {
        return highScore;
    }

    public void setHighScore(int highScore) {
        this.highScore = highScore;
        Database.getInstance().updateRankings();
        Database.getInstance().updateData();
    }

    public String getEmail() {
        return email;
    }


    public void changeUsername(String username) {
        this.username = username;
        Database.getInstance().updateData();
    }

    public void changeNickname(String nickname) {
        this.nickname = nickname;
        Database.getInstance().updateData();
    }

    public void changeSlogan(String slogan) {
        this.slogan = slogan;
        Database.getInstance().updateData();
    }

    public void changeEmail(String email) {
        this.email = email;
        Database.getInstance().updateData();
    }

    public void setPasswordRecovery(Pair passwordRecovery) {
        this.passwordRecovery = passwordRecovery;
        Database.getInstance().updateData();
    }

    public void setPassword(String newPass) {
        this.password = newPass;
        Database.getInstance().updateData();
    }

    public void removeSlogan() {
        this.slogan = "";
        Database.getInstance().updateData();
    }

    public void setAvatarPath(String avatarPath) {
        this.avatarPath = avatarPath;
        Database.getInstance().updateData();
    }

    public void addFriend(User user) {
        friends.add(user.getUsername());
        Database.getInstance().updateData();
    }

    public void addSender(User user) {
        senders.add(user.getUsername());
        Database.getInstance().updateData();
    }

    public void removeFriend(User user) {
        friends.remove(user.getUsername());
        Database.getInstance().updateData();
    }

    public void removeSender(User user) {
        senders.remove(user.getUsername());
        Database.getInstance().updateData();
    }

    public void updateLastOnline(){
        lastOnlineTime = System.currentTimeMillis();
        Database.getInstance().updateData();
    }

    public void setSocket(Socket socket) {
        this.socket = socket;
    }

    public boolean isOnline(){
        return socket != null;
    }

    @Override
    public String toString() {
        return "username : " + username +
                "\nnickname : " + nickname +
                "\nslogan : " + slogan +
                "\nemail : " + email +
                "\nhighscore : " + highScore;
    }

    public Socket getSocket() {
        return socket;
    }

    public void addMap(String mapId){
        mapList.add(mapId);
        Database.getInstance().updateData();
    }
}
