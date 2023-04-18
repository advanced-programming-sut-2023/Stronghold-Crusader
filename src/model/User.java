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
    public User(String username, String password, String Email, String nickname, String slogan) {
        this.username = username;
        this.password = password;
        this.Email = Email;
        this.nickname = nickname;
        this.slogan = slogan;
        this.highscore = 0 ;
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

    public String getEmail() {
        return Email;
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

    public void changeSlogan(String slogan) {
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

    public void setPassword(String newPass, String oldPass) {
        if(isPasswordCorrect(oldPass)) this.password = newPass;
    }

    public void removeSlogan(){
        this.slogan = "";
    }
}
