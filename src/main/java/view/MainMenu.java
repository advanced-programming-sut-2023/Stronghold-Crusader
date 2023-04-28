package view;

import controller.MainController;
import view.enums.commands.MainMenuCommand;
import view.enums.commands.MapSelectCommand;
import view.enums.messages.MainMenuMessage;
import view.enums.messages.MapSelectMessage;
import view.enums.messages.SignupAndLoginMessage;

import java.util.Scanner;

public class MainMenu {
    private MainController mainController;
    private Scanner scanner;
    public MainMenu(MainController mainController){
        this.mainController = mainController;
        this.scanner = Menu.getScanner();
    }

    public String run(){
        String input;
        while(true){
            input = scanner.nextLine();
            MainMenuCommand cmd = MainMenuCommand.getCommand(input);
            if(cmd == null){
                MainMenuMessage.printMessage(MainMenuMessage.INVALID_COMMAND);
                continue;
            }
            switch (cmd){
                case PROFILE_MENU:
                    return "profileMenu";
                case GAME_MAKER_MENU:
                    return "newGame";
                case BACK:
                    return "logout";
            }
        }
    }
}
