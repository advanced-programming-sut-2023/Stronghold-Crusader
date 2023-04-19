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

    public String changeUsername(String newUsername){
        if(!FormatValidation.isFormatValid(newUsername, FormatValidation.USERNAME)) return ProfileMessages.INVALID_USERNAME_FORMAT.getMessage();
        if(stronghold.userExists(newUsername)) return ProfileMessages.USERNAME_TAKEN.getMessage();
        currentUser.changeUsername(newUsername);
        return ProfileMessages.USERNAME_CHANGE_SUCCESS.getMessage();
    }

    public String changeNickname(String newNickname){
        currentUser.changeNickname(newNickname);
        return ProfileMessages.NICKNAME_CHANGE_SUCCESS.getMessage();
    }

    public String canChangePassword(String oldPass, String newPass){
        if(!currentUser.isPasswordCorrect(oldPass)) return ProfileMessages.PASSWORD_INCORRECT.getMessage();
        if(!FormatValidation.isFormatValid(newPass, FormatValidation.PASSWORD_LETTERS)) return ProfileMessages.INVALID_PASSWORD_FORMAT.getMessage();
        if(!FormatValidation.isFormatValid(newPass, FormatValidation.PASSWORD_LENGTH)) return ProfileMessages.INVALID_PASSWORD_LENGTH.getMessage();
        if(oldPass.equals(newPass)) return ProfileMessages.PASSWORD_NOT_NEW.getMessage();
        return "true";
    }

    public String changePassword(String newPass, String passConfirm, String oldPass){
        if(!passConfirm.equals(newPass)) return ProfileMessages.CONFIRMATION_INCORRECT.getMessage();
        currentUser.setPassword(newPass);
        return ProfileMessages.PASSWORD_CHANGE_SUCCESS.getMessage();
    }

    public String changeEmail(String newEmail){
        if(stronghold.emailExists(newEmail)) return ProfileMessages.EMAIL_EXISTS.getMessage();
        if(!FormatValidation.isFormatValid(newEmail, FormatValidation.EMAIL)) return ProfileMessages.INVALID_EMAIL_FORMAT.getMessage();
        return ProfileMessages.EMAIL_CHANGE_SUCCESS.getMessage();
    }

    public String changeSlogan(String newSlogan){
        currentUser.changeSlogan(newSlogan);
        return ProfileMessages.SLOGAN_CHANGE_SUCCESS.getMessage();
    }

    public String removeSlogan(){
        currentUser.removeSlogan();
        return ProfileMessages.SLOGAN_REMOVAL_SUCCESS.getMessage();
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
        return null;
    }
}
