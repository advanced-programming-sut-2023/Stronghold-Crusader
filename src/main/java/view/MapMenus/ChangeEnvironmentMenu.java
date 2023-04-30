package view.MapMenus;

import controller.MapControllers.ChangeEnvironmentController;
import view.Menu;
import view.enums.commands.MapMakerCommand;
import view.enums.messages.MapMakerMessage;

import java.util.Scanner;
import java.util.regex.Matcher;

public class ChangeEnvironmentMenu {
    private ChangeEnvironmentController controller;
    private Scanner scanner;
    public ChangeEnvironmentMenu(ChangeEnvironmentController controller){
        this.scanner = Menu.getScanner();
        this.controller = controller;
    }

    public String run(){
        String input;
        while (true){
            input = scanner.nextLine();
            MapMakerCommand cmd = MapMakerCommand.getCommand(input);
            if(cmd == null){
                MapMakerMessage.printMessage(MapMakerMessage.INVALID_COMMAND);
                continue;
            }
            Matcher matcher = MapMakerCommand.getMatcher(input, cmd);
            switch (cmd){
                case SET_TEXTURE:
                    runSetTexture(matcher);
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
                case DROP_HEADQUARTERS:
                    runDropHeadquarter(matcher);
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
    private void runSetTexture(Matcher matcher){
        int x = Integer.parseInt(matcher.group("x"));
        int y = Integer.parseInt(matcher.group("y"));
        String type = matcher.group("type");
        MapMakerMessage.printMessage(controller.setTexture(x, y, type));
    }
    private void runDropTree(Matcher matcher){
        int x = Integer.parseInt(matcher.group("x"));
        int y = Integer.parseInt(matcher.group("y"));
        String type = matcher.group("type");
        MapMakerMessage.printMessage((controller.dropTree(x, y, type)));
    }
    private void runDropRock(Matcher matcher){
        int x = Integer.parseInt(matcher.group("x"));
        int y = Integer.parseInt(matcher.group("y"));
        String direction = matcher.group("direction");
        MapMakerMessage.printMessage((controller.dropRock(x, y, direction)));
    }
    private void runClearCell(Matcher matcher){
        int x = Integer.parseInt(matcher.group("x"));
        int y = Integer.parseInt(matcher.group("y"));
        MapMakerMessage.printMessage((controller.clearCell(x, y)));
    }

    private void runDropHeadquarter(Matcher matcher){
        int x = Integer.parseInt(matcher.group("x"));
        int y = Integer.parseInt(matcher.group("y"));
        String color = matcher.group("color");
        MapMakerMessage.printMessage(controller.dropHeadquarters(x, y, color));
    }
}
