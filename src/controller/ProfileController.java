package controller;

import model.Stronghold;
import model.User;
import utils.SignupAndLoginUtils;
import view.ProfileMenu;
import view.enums.messages.ProfileMessages;

public class ProfileController {
    User currentUser;
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
   //     if(!SignupAndLoginUtils.isUsernameFormatCorrect(newUsername)) return ProfileMessages.INVALID_USERNAME_FORMAT.getMessage();
        if(Stronghold.doesUserExist(newUsername)) return ProfileMessages.USERNAME_TAKEN.getMessage();
        currentUser.changeUsername(newUsername);
        return ProfileMessages.USERNAME_CHANGE_SUCCESS.getMessage();
    }

    public String changeNickname(String newNickname){
        currentUser.changeNickname(newNickname);
        return ProfileMessages.NICKNAME_CHANGE_SUCCESS.getMessage();
    }

    public String canChangePassword(String oldPass){
        if(!currentUser.isPasswordCorrect(oldPass)) return "false";
        return "true";
    }

    public String changePassword(String newPass, String passConfirm){
        return ProfileMessages.PASSWORD_CHANGE_SUCCESS.getMessage();
    }

    public String changeEmail(String newEmail){
        return ProfileMessages.EMAIL_CHANGE_SUCCESS.getMessage();
    }

    public String changeSlogan(String newSlogan){
        return ProfileMessages.SLOGAN_CHANGE_SUCCESS.getMessage();
    }

    public String removeSlogan(){
        return ProfileMessages.SLOGAN_REMOVAL_SUCCESS.getMessage();
    }

    public int displayHighscore(){
        return currentUser.getHighscore();
    }
    public int displayRank(){
        return Stronghold.getUserRank(currentUser);
    }

    public String displaySlogan(){
        return currentUser.getSlogan();
    }

    public String displayUserInfo(){
        return null;
    }
}
