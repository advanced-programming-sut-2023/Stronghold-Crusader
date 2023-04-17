package view;

import controller.ProfileController;
import view.enums.commands.ProfileCommands;
import view.enums.messages.ProfileMessages;

import java.util.Scanner;

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
            ProfileCommands cmd = ProfileCommands.getCommand(input);
            if(cmd == null){
                ProfileMessages.printMessage(ProfileMessages.INVALID_COMMAND);
            }
        }
    }
}
