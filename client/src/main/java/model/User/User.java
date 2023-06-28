package model.User;

import model.Stronghold;
import utils.*;

import java.util.ArrayList;

public class User {
    private String username;
    private String password;
    private String nickname;
    private String slogan;
    private String email;
    private Pair passwordRecovery;
    private String avatarPath;
    private final ArrayList<User> friends;
    private final ArrayList<User> senders;
    private int highScore;

    public User(String username, String password, String email, String nickname, String slogan) {
        friends = new ArrayList<>();
        senders = new ArrayList<>();
        this.username = username;
        this.password = PasswordConverter.encodePassword(password);
        this.email = email;
        this.nickname = nickname;
        this.slogan = slogan;
        this.highScore = 0;
        this.avatarPath = User.class.getResource("/assets/avatars/defaults/default.png").toExternalForm();
    }

    public String getUsername() {
        return username;
    }

    public String getNickname() {
        return nickname;
    }

    public String getSlogan() {
        return slogan;
    }

    public int getHighScore() {
        return highScore;
    }

    public void setHighScore(int highScore) {
        this.highScore = highScore;
        Stronghold.getInstance().updateRankings();
        Stronghold.getInstance().updateData();
    }

    public String getEmail() {
        return email;
    }

    public String getPasswordRecoveryQuestion() {
        return passwordRecovery.x;
    }

    public boolean isPasswordCorrect(String password) {
        return this.password.equals(PasswordConverter.encodePassword(password));
    }

    public boolean isRecoveryPasswordCorrect(String recoveryAnswer) {
        return passwordRecovery.y.equals(recoveryAnswer);
    }

    public void changeUsername(String username) {
        this.username = username;
        Stronghold.getInstance().updateData();
    }

    public void changeNickname(String nickname) {
        this.nickname = nickname;
        Stronghold.getInstance().updateData();
    }

    public void changeSlogan(String slogan) {
        this.slogan = slogan;
        Stronghold.getInstance().updateData();
    }

    public void changeEmail(String email) {
        this.email = email;
        Stronghold.getInstance().updateData();
    }

    public void setPasswordRecovery(Pair passwordRecovery) {
        this.passwordRecovery = passwordRecovery;
        Stronghold.getInstance().updateData();
    }

    public void setPassword(String newPass) {
        this.password = PasswordConverter.encodePassword(newPass);
        Stronghold.getInstance().updateData();
    }

    public void removeSlogan() {
        this.slogan = "";
        Stronghold.getInstance().updateData();
    }

    public void setAvatarPath(String avatarPath) {
        this.avatarPath = avatarPath;
        Stronghold.getInstance().updateData();
    }

    public String getAvatarPath() {
        return avatarPath;
    }

    @Override
    public String toString() {
        return "username : " + username +
                "\nnickname : " + nickname +
                "\nslogan : " + slogan +
                "\nemail : " + email +
                "\nhighscore : " + highScore;
    }

    public int getRank() {
        return Stronghold.getInstance().getUserRank(this);
    }

    public void addFriend(User user) {
        friends.add(user);
    }

    public void addSender(User user) {
        senders.add(user);
    }

    public void removeFriend(User user) {
        friends.remove(user);
    }

    public void removeSender(User user) {
        senders.remove(user);
    }

    public ArrayList<User> getFriends() {
        return friends;
    }

    public ArrayList<User> getSenders() {
        return senders;
    }

    public boolean isFriend(User user) {
        return friends.contains(user);
    }
    public boolean isHaveRequestFrom(User user) {
        return senders.contains(user);
    }
}
