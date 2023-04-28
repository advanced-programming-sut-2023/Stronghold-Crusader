package view;

import controller.ProfileController;
import view.enums.commands.ProfileCommand;
import view.enums.messages.ProfileMessage;

import java.util.Scanner;
import java.util.regex.Matcher;

public class ProfileMenu {
    private ProfileController profileController;
    private Scanner scanner;
    public ProfileMenu(ProfileController profileController){
        this.profileController = profileController;
        this.scanner = Menu.getScanner();
    }

    public String run(){
        String input;
        while(true){
            input = scanner.nextLine();
            ProfileCommand cmd = ProfileCommand.getCommand(input);
            if(cmd == null){
                ProfileMessage.printMessage(ProfileMessage.INVALID_COMMAND);
                continue;
            }
            Matcher matcher = ProfileCommand.getMatcher(input, cmd);
            switch (cmd){
                case CHANGE_USERNAME :
                    runChangeUsername(matcher);
                    break;
                case CHANGE_NICKNAME:
                    runChangeNickname(matcher);
                    break;
                case CHANGE_PASSWORD:
                    runChangePassword(matcher);
                    break;
                case CHANGE_EMAIL:
                    runChangeEmail(matcher);
                    break;
                case CHANGE_SLOGAN:
                    runChangeSlogan(matcher);
                    break;
                case DISPLAY_HIGHSCORE:
                    System.out.println(profileController.displayHighscore());
                    break;
                case DISPLAY_RANK:
                    System.out.println(profileController.displayRank());
                    break;
                case DISPLAY_SLOGAN:
                    System.out.println(profileController.displaySlogan());
                    break;
                case PROFILE_INFO:
                    System.out.println(profileController.displayUserInfo());
                    break;
                case REMOVE_SLOGAN:
                    System.out.println(profileController.removeSlogan());
                    break;
                case BACK:
                    return "mainMenu";
                default:
                    ProfileMessage.printMessage(ProfileMessage.INVALID_COMMAND);
                    break;
            }
        }
    }

    private void runChangeUsername(Matcher matcher){
        String newUsername = matcher.group("newUsername");
        System.out.println(profileController.changeUsername(newUsername).getMessage());
    }

    private void runChangeNickname(Matcher matcher){
        String newNickname = matcher.group("newNickname");
        System.out.println(profileController.changeNickname(newNickname).getMessage());
    }

    private void runChangePassword(Matcher matcher){
        String oldPass = matcher.group("oldPass");
        String newPass = matcher.group("newPass");
        String canChangePass = profileController.canChangePassword(oldPass, newPass).getMessage();
        if(canChangePass.equals("true")) {
            System.out.println(ProfileMessage.ENTER_NEWPASS_AGAIN.getMessage());
            String confirmation = scanner.nextLine();
            System.out.println(profileController.changePassword(newPass, confirmation, oldPass));
        }
        else System.out.println(canChangePass);
    }

    private void runChangeEmail(Matcher matcher){
        String newEmail = matcher.group("email");
        System.out.println(profileController.changeEmail(newEmail).getMessage());
    }

    private void runChangeSlogan(Matcher matcher){
        String newSlogan = matcher.group("slogan");
        System.out.println(profileController.changeSlogan(newSlogan).getMessage());
    }
}
