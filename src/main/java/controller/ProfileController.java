package controller;

import model.Stronghold;
import model.User;
import utils.FormatValidation;
import view.ProfileMenu;
import view.enums.messages.ProfileMessages;

public class ProfileController {
    private User currentUser;
    private Stronghold stronghold = Stronghold.getInstance();
    public ProfileController(User currentUser){
        this.currentUser = currentUser;
    }

    public void run(){
        while(true){
            ProfileMenu profileMenu = new ProfileMenu(this);
            switch (profileMenu.run()){
                case "back":
                    return;
            }
        }
    }

    public ProfileMessages changeUsername(String newUsername){
        if(!FormatValidation.isFormatValid(newUsername, FormatValidation.USERNAME)) return ProfileMessages.INVALID_USERNAME_FORMAT;
        if(stronghold.userExists(newUsername)) return ProfileMessages.USERNAME_TAKEN;
        currentUser.changeUsername(newUsername);
        return ProfileMessages.USERNAME_CHANGE_SUCCESS;
    }

    public ProfileMessages changeNickname(String newNickname){
        currentUser.changeNickname(newNickname);
        return ProfileMessages.NICKNAME_CHANGE_SUCCESS;
    }

    public ProfileMessages canChangePassword(String oldPass, String newPass){
        if(!currentUser.isPasswordCorrect(oldPass)) return ProfileMessages.PASSWORD_INCORRECT;
        if(!FormatValidation.isFormatValid(newPass, FormatValidation.PASSWORD_LETTERS)) return ProfileMessages.INVALID_PASSWORD_FORMAT;
        if(!FormatValidation.isFormatValid(newPass, FormatValidation.PASSWORD_LENGTH)) return ProfileMessages.INVALID_PASSWORD_LENGTH;
        if(oldPass.equals(newPass)) return ProfileMessages.PASSWORD_NOT_NEW;
        return ProfileMessages.CAN_CHANGE_PASSWORD;
    }

    public ProfileMessages changePassword(String newPass, String passConfirm, String oldPass){
        if(!passConfirm.equals(newPass)) return ProfileMessages.CONFIRMATION_INCORRECT;
        currentUser.setPassword(newPass);
        return ProfileMessages.PASSWORD_CHANGE_SUCCESS;
    }

    public ProfileMessages changeEmail(String newEmail){
        if(stronghold.emailExists(newEmail)) return ProfileMessages.EMAIL_EXISTS;
        if(!FormatValidation.isFormatValid(newEmail, FormatValidation.EMAIL)) return ProfileMessages.INVALID_EMAIL_FORMAT;
        currentUser.changeEmail(newEmail);
        return ProfileMessages.EMAIL_CHANGE_SUCCESS;
    }

    public ProfileMessages changeSlogan(String newSlogan){
        currentUser.changeSlogan(newSlogan);
        return ProfileMessages.SLOGAN_CHANGE_SUCCESS;
    }

    public ProfileMessages removeSlogan(){
        currentUser.removeSlogan();
        return ProfileMessages.SLOGAN_REMOVAL_SUCCESS;
    }

    public int displayHighscore(){
        return currentUser.getHighScore();
    }
    public int displayRank(){
        return stronghold.getUserRank(currentUser);
    }

    public String displaySlogan(){
        return currentUser.getSlogan();
    }

    public String displayUserInfo(){
//      TODO fixOutput : Diba
        return null;
    }

    public void setCurrentUser(User currentUser) {
        this.currentUser = currentUser;
    }
}
