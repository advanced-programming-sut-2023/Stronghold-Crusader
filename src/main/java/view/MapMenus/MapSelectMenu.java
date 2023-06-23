package view.MapMenus;

import controller.MapControllers.MapSelectController;
import javafx.scene.layout.AnchorPane;
import view.Menu;
import view.enums.commands.MapCommand.MapSelectCommand;
import view.enums.messages.MapMessage.MapSelectMessage;

import java.util.Scanner;
import java.util.regex.Matcher;

public class MapSelectMenu {
    private final MapSelectController mapSelectController;
    private final Scanner scanner;

    public MapSelectMenu(MapSelectController mapSelectController) {
        this.mapSelectController = mapSelectController;
        this.scanner = Menu.getScanner();
    }

    public String run() {
        String input;
        while (true) {
            input = scanner.nextLine();
            MapSelectCommand cmd = MapSelectCommand.getCommand(input);
            if (cmd == null) {
                MapSelectMessage.INVALID_COMMAND.printMessage();
                continue;
            }
            Matcher matcher = MapSelectCommand.getMatcher(input, cmd);
            switch (cmd) {
                case GET_MAP_LIST:
                    System.out.print(mapSelectController.getMapsList());
                    break;
                case SELECT_MAP:
                    runSelectMap(matcher);
                    break;
                case PLAYERS_COUNT:
                    System.out.println(mapSelectController.numberOfPlayers());
                    break;
                case ADD_PLAYER:
                    runAddPlayer(matcher);
                    break;
                case START_GAME:
                    MapSelectMessage msg = mapSelectController.startGame();
                    msg.printMessage();
                    if (msg.equals(MapSelectMessage.GAME_CREATION_SUCCESS)) return "startGame";
                    break;
            }
        }
    }

    private void runSelectMap(Matcher matcher) {
        String mapId = matcher.group("mapId");
        boolean flag = matcher.group("modifle") != null;
        mapSelectController.selectMap(mapId, flag).printMessage();
    }

    private void runAddPlayer(Matcher matcher) {
        String username = matcher.group("username");
        String colorName = matcher.group("color");
        mapSelectController.addPlayer(username, colorName).printMessage();
    }
}
