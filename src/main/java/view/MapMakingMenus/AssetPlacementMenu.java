package view.MapMakingMenus;

import controller.MapMakerController;
import view.Menu;
import view.enums.commands.MapMakerCommand;
import view.enums.messages.MapMakerMessage;

import java.util.Scanner;
import java.util.regex.Matcher;

public class AssetPlacementMenu {
    private MapMakerController controller;
    private Scanner scanner;
    public AssetPlacementMenu(MapMakerController controller){
        this.scanner = Menu.getScanner();
        this.controller = controller;
    }

    public String run(){
        String input;
        while (true){
            input = scanner.nextLine();
            MapMakerCommand cmd = MapMakerCommand.getCommand(input);
            if(input == null){
                MapMakerMessage.printMessage(MapMakerMessage.INVALID_COMMAND);
                continue;
            }
            Matcher matcher = MapMakerCommand.getMatcher(input, cmd);
            switch (cmd){
                case DROP_BUILDING:
                    runDropBuilding(matcher);
                    break;
                case DROP_UNIT:
                    runDropUnit(matcher);
                    break;
                case DROP_HEADQUARTERS:
                    runDropHeadquarters(matcher);
                    break;
                case BACK:
                    return "back";
            }
        }
    }

    // TODO : handle command and inputs
    private void runDropUnit(Matcher matcher){

    }
    private void runDropBuilding(Matcher matcher){

    }
    private void runDropHeadquarters(Matcher matcher){

    }
}
