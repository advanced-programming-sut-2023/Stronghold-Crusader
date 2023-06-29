package view.MapMenus.dropBuildingMenu;

import controller.MapControllers.ChangeEnvironmentController;
import view.Menu;
import view.enums.commands.MapCommand.MapMakerCommand;
import view.enums.messages.MapMessage.MapMakerMessage;

import java.util.Scanner;
import java.util.regex.Matcher;

public class ChangeEnvironmentMenu {
    private final ChangeEnvironmentController controller;
    private final Scanner scanner;

    public ChangeEnvironmentMenu(ChangeEnvironmentController controller) {
        this.scanner = Menu.getScanner();
        this.controller = controller;
    }

    public String run() {
        String input;
        while (true) {
            input = scanner.nextLine();
            MapMakerCommand cmd = MapMakerCommand.getCommand(input);
            if (cmd == null) {
                MapMakerMessage.printMessage(MapMakerMessage.INVALID_COMMAND);
                continue;
            }
            Matcher matcher = MapMakerCommand.getMatcher(input, cmd);
            switch (cmd) {
                case SET_TEXTURE:
                    runSetTexture(matcher);
                    break;
                case SET_TEXTURE_BLOCK:
                    runSetTextureBlock(matcher);
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
                case DROP_BUILDING:
                    runDropBuilding(matcher);
                    break;
                case DROP_UNIT:
                    runDropUnit(matcher);
                    break;
                case BACK:
                    MapMakerMessage.printMessage(MapMakerMessage.ENTER_MAIN);
                    return "back";
                default:
                    MapMakerMessage.printMessage(MapMakerMessage.INVALID_COMMAND);
                    break;
            }
        }
    }

    private void runSetTexture(Matcher matcher) {
        int x = Integer.parseInt(matcher.group("x"));
        int y = Integer.parseInt(matcher.group("y"));
        String type = matcher.group("type");
        MapMakerMessage.printMessage(controller.setTexture(x, y, type));
    }

    private void runSetTextureBlock(Matcher matcher) {
        int x1 = Integer.parseInt(matcher.group("x1"));
        int y1 = Integer.parseInt(matcher.group("y1"));
        int x2 = Integer.parseInt(matcher.group("x2"));
        int y2 = Integer.parseInt(matcher.group("y2"));
        String type = matcher.group("type");
        MapMakerMessage.printMessage(controller.setTexture(x1, y1, x2, y2, type));
    }

    private void runDropTree(Matcher matcher) {
        int x = Integer.parseInt(matcher.group("x"));
        int y = Integer.parseInt(matcher.group("y"));
        String type = matcher.group("type");
        MapMakerMessage.printMessage((controller.dropTree(x, y, type)));
    }

    private void runDropRock(Matcher matcher) {
        int x = Integer.parseInt(matcher.group("x"));
        int y = Integer.parseInt(matcher.group("y"));
        String direction = matcher.group("direction");
        MapMakerMessage.printMessage((controller.dropRock(x, y, direction)));
    }

    private void runClearCell(Matcher matcher) {
        int x = Integer.parseInt(matcher.group("x"));
        int y = Integer.parseInt(matcher.group("y"));
        MapMakerMessage.printMessage((controller.clearCell(x, y)));
    }

    private void runDropBuilding(Matcher matcher){
        int x = Integer.parseInt(matcher.group("x"));
        int y = Integer.parseInt(matcher.group("y"));
        String type = matcher.group("type");
        MapMakerMessage.printMessage(controller.dropBuilding(x, y, type));
    }

    private void runDropUnit(Matcher matcher){
        int x = Integer.parseInt(matcher.group("x"));
        int y = Integer.parseInt(matcher.group("y"));
        String type = matcher.group("type");
        MapMakerMessage.printMessage(controller.dropUnit(x, y, type));
    }

}