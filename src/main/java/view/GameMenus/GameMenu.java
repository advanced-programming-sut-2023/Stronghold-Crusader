package view.GameMenus;

import controller.GameControllers.GameController;
import view.Menu;
import view.enums.commands.GameCommand.GameMenuCommand;
import view.enums.messages.GameMessage.GameMenuMessage;

import java.util.Map;
import java.util.Scanner;
import java.util.regex.Matcher;

public class GameMenu {
    private final GameController gameController;
    private final Scanner scanner;

    public GameMenu(GameController gameController) {
        this.gameController = gameController;
        this.scanner = Menu.getScanner();
    }

    public String run() {
        String input;
        while (true) {
            input = scanner.nextLine();
            GameMenuCommand cmd = GameMenuCommand.getCommand(input);
            if (cmd == null) {
                GameMenuMessage.printMessage(GameMenuMessage.INVALID_COMMAND);
                continue;
            }
            Matcher matcher = GameMenuCommand.getMatcher(input, cmd);
            switch (cmd) {
                case CHANGE_ENVIRONMENT:
                    return "changeEnvironment";
                case PLACE_BUILDING:
                    return "buildingPlacement";
                case TRADE_MENU:
                    return "tradeMenu";
                case SHOW_MAP:
                    if (runShowMap(matcher))
                        return "showMap";
                case MARKET_MENU:
                    return "marketMenu";
            }
        }
    }

    private boolean runShowMap(Matcher matcher) {
        int x = Integer.parseInt(matcher.group("x"));
        int y = Integer.parseInt(matcher.group("y"));
        GameMenuMessage msg = gameController.showMap(x, y);
        GameMenuMessage.printMessage(msg);
        return msg.equals(GameMenuMessage.ENTER_SHOW_MAP);
    }
}