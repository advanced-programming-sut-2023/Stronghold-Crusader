package controller;

import model.Stronghold;
import model.User;
import utils.FormatValidation;
import view.ProfileMenu;
import view.enums.messages.ProfileMessage;

public class ProfileController {
    private User currentUser;
    private Stronghold stronghold = Stronghold.getInstance();

    public ProfileController(User currentUser) {
        this.currentUser = currentUser;
    }

    public void run() {
        ProfileMenu profileMenu = new ProfileMenu(this);
        while (true){
            if(profileMenu.run().equals("mainMenu")) return;
        }
    }

    public ProfileMessage changeUsername(String newUsername) {
        if (!FormatValidation.isFormatValid(newUsername, FormatValidation.USERNAME))
            return ProfileMessage.INVALID_USERNAME_FORMAT;
        if (stronghold.userExists(newUsername)) return ProfileMessage.USERNAME_TAKEN;
        currentUser.changeUsername(newUsername);
        return ProfileMessage.USERNAME_CHANGE_SUCCESS;
    }

    public ProfileMessage changeNickname(String newNickname) {
        currentUser.changeNickname(newNickname);
        return ProfileMessage.NICKNAME_CHANGE_SUCCESS;
    }

    public ProfileMessage canChangePassword(String oldPass, String newPass) {
        if (!currentUser.isPasswordCorrect(oldPass)) return ProfileMessage.PASSWORD_INCORRECT;
        if (!FormatValidation.isFormatValid(newPass, FormatValidation.PASSWORD_LETTERS))
            return ProfileMessage.INVALID_PASSWORD_FORMAT;
        if (!FormatValidation.isFormatValid(newPass, FormatValidation.PASSWORD_LENGTH))
            return ProfileMessage.INVALID_PASSWORD_LENGTH;
        if (oldPass.equals(newPass)) return ProfileMessage.PASSWORD_NOT_NEW;
        return ProfileMessage.CAN_CHANGE_PASSWORD;
    }

    public ProfileMessage changePassword(String newPass, String passConfirm, String oldPass) {
        if (!passConfirm.equals(newPass)) return ProfileMessage.CONFIRMATION_INCORRECT;
        currentUser.setPassword(newPass);
        return ProfileMessage.PASSWORD_CHANGE_SUCCESS;
    }

    public ProfileMessage changeEmail(String newEmail) {
        if (stronghold.emailExists(newEmail)) return ProfileMessage.EMAIL_EXISTS;
        if (!FormatValidation.isFormatValid(newEmail, FormatValidation.EMAIL))
            return ProfileMessage.INVALID_EMAIL_FORMAT;
        currentUser.changeEmail(newEmail);
        return ProfileMessage.EMAIL_CHANGE_SUCCESS;
    }

    public ProfileMessage changeSlogan(String newSlogan) {
        currentUser.changeSlogan(newSlogan);
        return ProfileMessage.SLOGAN_CHANGE_SUCCESS;
    }

    public ProfileMessage removeSlogan() {
        currentUser.removeSlogan();
        return ProfileMessage.SLOGAN_REMOVAL_SUCCESS;
    }

    public int displayHighscore() {
        return currentUser.getHighScore();
    }

    public int displayRank() {
        return stronghold.getUserRank(currentUser);
    }

    public String displaySlogan() {
        return currentUser.getSlogan();
    }

    public String displayUserInfo() {
//      TODO fixOutput : Diba
        return null;
    }

    public void setCurrentUser(User currentUser) {
        this.currentUser = currentUser;
    }
}
