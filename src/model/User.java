package model;

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
        this.password = password;
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
    }

    public String getEmail() {
        return email;
    }

    public String getPasswordRecoveryQuestion() {
        return passwordRecovery.x;
    }

    public boolean isPasswordCorrect(String password) {
        return this.password.matches(password);
    }

    public boolean isRecoveryPasswordCorrect(String recoveryAnswer) {
        return passwordRecovery.y.equals(recoveryAnswer);
    }

    public void changeUsername(String username) {
        this.username = username;
    }

    public void changeNickname(String nickname) {
        this.nickname = nickname;
    }

    public void changeSlogan(String slogan) {
        this.slogan = slogan;
    }

    public void changeEmail(String email) {
        this.email = email;
    }

    public void setPasswordRecovery(Pair passwordRecovery) {
        this.passwordRecovery = passwordRecovery;
    }

    public void setPassword(String newPass) {
        this.password = newPass;
    }

    public void removeSlogan() {
        this.slogan = "";
    }
}
