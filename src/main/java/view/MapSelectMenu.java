package view;

import controller.MapSelectController;
import model.Player;
import view.enums.commands.MapSelectCommand;
import view.enums.messages.MapSelectMessage;

import java.util.ArrayList;
import java.util.Map;
import java.util.Scanner;
import java.util.regex.Matcher;

public class MapSelectMenu {
    private MapSelectController mapSelectController;
    private Scanner scanner;
    private Map selectedMap;
    private ArrayList<Player> players;
    private boolean isMapModifiable;
    public MapSelectMenu(MapSelectController mapSelectController){
        this.mapSelectController = mapSelectController;
        this.scanner = Menu.getScanner();
    }

    public String run(){
        String input;
        while (true){
            input = scanner.nextLine();
            MapSelectCommand cmd = MapSelectCommand.getCommand(input);
            if(cmd == null){
                MapSelectMessage.printMessage(MapSelectMessage.INVALID_COMMAND);
                continue;
            }
            Matcher matcher = MapSelectCommand.getMatcher(input, cmd);
            switch (cmd){
                case GET_MAP_LIST:
                    System.out.print(mapSelectController.getMapsList());
                    break;
            }
        }
    }
}
