package view;

import controller.GameController;
import view.enums.commands.GameMenuCommand;

import java.util.Scanner;

public class GameMenu {
    private GameController gameController;
    private Scanner scanner;
    public GameMenu(GameController gameController){
        this.gameController = gameController;
        this.scanner = Menu.getScanner();
    }

    public String run(){
        String input;
        while(true){
            input = scanner.nextLine();
            GameMenuCommand cmd = GameMenuCommand.getCommand(input);
            if(cmd == null){
                System.out.println();
                continue;
            }
            switch (cmd){
                case MODIFY_MAP:
                    return "modifyMap";
            }
        }
    }
}
