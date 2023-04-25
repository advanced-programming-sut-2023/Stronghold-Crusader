package view.MapMakingMenus;

import controller.MapMakerController;
import view.Menu;
import view.enums.commands.MapMakerCommands;
import view.enums.messages.MapMakerMessage;

import java.util.Scanner;
import java.util.regex.Matcher;

public class ChangeEnvironmentMenu {
    private MapMakerController controller;
    private Scanner scanner;
    public ChangeEnvironmentMenu(MapMakerController controller){
        this.scanner = Menu.getScanner();
        this.controller = controller;
    }

    public String run(){
        String input;
        while (true){
            input = scanner.nextLine();
            MapMakerCommands cmd = MapMakerCommands.getCommand(input);
            if(input == null){
                MapMakerMessage.printMessage(MapMakerMessage.INVALID_COMMAND);
                continue;
            }
            Matcher matcher = MapMakerCommands.getMatcher(input, cmd);
            switch (cmd){
                case SET_TEXTURE:
                    runSetTeture(matcher);
                    break;
                case DROP_ROCK:
                    runDropRock(matcher);
                    break;
                case DROP_TREE:
                    runDropTree(matcher);
                    break;
                case CLEAR_CELL:
                    runClearCell(matcher);
                    break;
                case BACK:
                    return "back";
                default:
                    MapMakerMessage.printMessage(MapMakerMessage.INVALID_COMMAND);
                    break;
            }
        }
    }

    // TODO : handle command and inputs
    private void runSetTeture(Matcher matcher){

    }
    private void runDropTree(Matcher matcher){

    }
    private void runDropRock(Matcher matcher){

    }
    private void runClearCell(Matcher matcher){

    }

}
