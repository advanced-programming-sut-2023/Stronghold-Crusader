package model;

import database.Database;
import utils.Pair;
import utils.PasswordConverter;

public class User {
    private String username;
    private String password;
    private String nickname;
    private String slogan;
    private String email;
    private Pair passwordRecovery;
    private String avatarPath;
    private int highScore;
    private int rank;

    public User(String username, String password, String email, String nickname, String slogan) {
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
        Database.getInstance().updateRankings();
        Database.getInstance().updateData();
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
        this.password = PasswordConverter.encodePassword(newPass);
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
        return rank = Database.getInstance().getUserRank(username);
    }
}