package view;

import controller.MainController;
import controller.ProfileController;
import view.enums.commands.MainMenuCommands;
import view.enums.messages.MainMenuMessages;

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
            MainMenuCommands cmd = MainMenuCommands.getCommand(input);
            if(cmd == null){
                System.out.println();
                continue;
            }
            switch (cmd){
                case PROFILE_MENU:
                    return "profileMenu";
                case GAME_MAKER_MENU:
                    return "newGame";
            }
        }
    }
}
