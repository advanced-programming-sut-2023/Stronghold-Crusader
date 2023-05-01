package view.GameMenus;

import controller.GameControllers.GameController;
import view.Menu;
import view.enums.commands.GameCommand.GameMenuCommand;

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
                case CHANGE_ENVIRONMENT:
                    return "changeEnvironment";
                case PLACE_BUILDING:
                    return "buildingPlacement";
                case TRADE_MENU:
                    return "tradeMenu";
                case SHOW_MAP:
                    return "showMap";
            }
        }
    }
}
