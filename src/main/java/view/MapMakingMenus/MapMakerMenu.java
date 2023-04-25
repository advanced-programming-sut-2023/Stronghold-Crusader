package view.MapMakingMenus;

import controller.MapMakerController;
import utils.Vector2D;
import view.Menu;
import view.enums.commands.MapMakerCommands;
import view.enums.messages.MapMakerMessage;

import java.util.Scanner;

public class MapMakerMenu {
    private MapMakerController controller;
    private Scanner scanner;
    public MapMakerMenu(MapMakerController mapMakerController){
        this.controller = mapMakerController;
        this.scanner = Menu.getScanner();
    }

    public String run(){
        String input;
        while(true){
            input = scanner.nextLine();
            MapMakerCommands cmd = MapMakerCommands.getCommand(input);
            if(input == null){
                MapMakerMessage.printMessage(MapMakerMessage.INVALID_COMMAND);
                continue;
            }
            switch (cmd){
                case CHANGE_ENVIRONMENT:
                    return "environment";
                case PLACE_ASSETS:
                    return "asset";
                default:
                    MapMakerMessage.printMessage(MapMakerMessage.INVALID_COMMAND);
                    break;
            }
        }
    }

    public String getMapName(){
        MapMakerMessage.printMessage(MapMakerMessage.CHOOSE_MAP_NAME);
        return scanner.nextLine();
    }
    public Vector2D getMapSize(){
        //TODO getmapSize
        return null;
    }
}
