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
                case SELECT_MAP:
                    runSelectMap(matcher);
                    break;
                case PLAYERS_COUNT:
                    System.out.println(mapSelectController.numberOfPlayers());
                    break;
            }
        }
    }

    private void runSelectMap(Matcher matcher){
        String mapId = matcher.group("mapId");
        System.out.println(mapSelectController.selectMap(mapId));
    }
}
