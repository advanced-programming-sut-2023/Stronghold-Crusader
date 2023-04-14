package model;

import utils.*;
public class User {
    private String username;
    private String password;
    private String nickname;
    private String slogan;
    private String Email;
    private Pair passwordRecovery;
    private int highscore;
    User(String username, String password, String Email) {

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

    public int getHighscore() {
        return highscore;
    }

    public String getPasswordRecoveryQuestion() {
        return passwordRecovery.x;
    }
    public boolean isPasswordCorrect(String password){
        return true;
    }
    public boolean isRecoveryPasswordCorrect(String recoveryAnswer){
        return true;
    }

    public void changeUsername(String username) {
        this.username = username;
    }

    public void changeNickname(String nickname) {
        this.nickname = nickname;
    }

    public void setSlogan(String slogan) {
        this.slogan = slogan;
    }

    public void changeEmail(String email) {
        Email = email;
    }

    public void setPasswordRecovery(Pair passwordRecovery) {
        this.passwordRecovery = passwordRecovery;
    }

    public void setHighscore(int highscore) {
        this.highscore = highscore;
    }
}
