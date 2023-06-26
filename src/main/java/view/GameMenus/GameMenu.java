package view.GameMenus;

import controller.GameControllers.GameController;
import view.Menu;
import view.enums.commands.GameCommand.GameMenuCommand;
import view.enums.messages.GameMessage.GameMenuMessage;

import java.util.Scanner;
import java.util.regex.Matcher;

public class GameMenu {
    private final GameController gameController;
    private final Scanner scanner;

    public GameMenu(GameController gameController) {
        this.gameController = gameController;
        this.scanner = Menu.getScanner();
    }

    private boolean runSelectUnit(Matcher matcher) {
        int x = Integer.parseInt(matcher.group("x"));
        int y = Integer.parseInt(matcher.group("y"));
        GameMenuMessage msg = gameController.selectUnit(x, y);
        GameMenuMessage.printMessage(msg);
        return msg.equals(GameMenuMessage.UNIT_SELECTED);
    }

    private boolean runSelectBuilding(Matcher matcher) {
        int x = Integer.parseInt(matcher.group("x"));
        int y = Integer.parseInt(matcher.group("y"));
        GameMenuMessage msg = gameController.selectBuilding(x, y);
        GameMenuMessage.printMessage(msg);
        return msg.equals(GameMenuMessage.BUILDING_SELECTED);
    }

    private boolean runShowMap(Matcher matcher) {
        int x = Integer.parseInt(matcher.group("x"));
        int y = Integer.parseInt(matcher.group("y"));
        GameMenuMessage msg = gameController.showMap(x, y);
        GameMenuMessage.printMessage(msg);
        return msg.equals(GameMenuMessage.ENTER_SHOW_MAP);
    }


    private void runSetFearRate(Matcher matcher) {
        int rate = Integer.parseInt(matcher.group("rate"));
//        GameMenuMessage.printMessage(gameController.setFearRate(rate));
    }

}
