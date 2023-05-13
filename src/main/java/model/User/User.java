package model.User;

import model.Stronghold;
import utils.*;

public class User {
    private String username;
    private String password;
    private String nickname;
    private String slogan;
    private String email;
    private Pair passwordRecovery;
    private int highScore;

    public User(String username, String password, String email, String nickname, String slogan) {
        this.username = username;
        this.password = PasswordConverter.encodePassword(password);
        this.email = email;
        this.nickname = nickname;
        this.slogan = slogan;
        this.highScore = 0;
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

    @Override
    public String toString() {
        return "username : " + username +
                "\nnickname : " + nickname +
                "\nslogan : " + slogan +
                "\nemail : " + email +
                "\nhighscore : " + highScore;
    }

}
