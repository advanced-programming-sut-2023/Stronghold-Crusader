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

    public String run() {
        System.out.println(gameController.showGameInfo());
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
                case SELECT_UNIT:
                    if (runSelectUnit(matcher))
                        return "selectedUnit";
                    break;
                case SELECT_BUILDING:
                    if (runSelectBuilding(matcher))
                        return "selectedBuilding";
                    break;
                case SHOW_POPULARITY:
                    System.out.println(gameController.showPopularity());
                    break;
                case SHOW_POPULARITY_FACTORS:
                    System.out.println(gameController.showPopularityFactors());
                    break;
                case SHOW_FOOD_LIST:
                    System.out.println(gameController.showFoodList());
                    break;
                case SHOW_FOOD_RATE:
                    System.out.println(gameController.showFoodRate());
                    break;
                case SHOW_TAX_RATE:
                    System.out.println(gameController.showTaxRate());
                    break;
                case SET_FEAR_RATE:
                    runSetFearRate(matcher);
                    break;
                case NEXT_TURN:
                    gameController.nextTurn();
                    System.out.println(gameController.showGameInfo());
                    break;
                case SHOW_GAME_INFO:
                    System.out.println(gameController.showGameInfo());
                    break;
                case CHANGE_ENVIRONMENT:
                    if (!gameController.isModifiable()) {
                        GameMenuMessage.printMessage(GameMenuMessage.NOT_MODIFIABLE);
                        break;
                    }
                    GameMenuMessage.printMessage(GameMenuMessage.CHANGE_ENVIRONMENT);
                    return "changeEnvironment";
                case PLACE_BUILDING:
                    GameMenuMessage.printMessage(GameMenuMessage.BUILDING_PLACEMENT);
                    return "buildingPlacement";
                case TRADE_MENU:
                    GameMenuMessage.printMessage(GameMenuMessage.TRADE);
                    return "tradeMenu";
                case SHOW_MAP:
                    if (runShowMap(matcher))
                        return "showMap";
                    break;
                case MARKET_MENU:
                    GameMenuMessage.printMessage(GameMenuMessage.ENTER_MARKET);
                    return "marketMenu";
            }
        }
    }

    private boolean runSelectUnit(Matcher matcher) {
        int x = Integer.parseInt(matcher.group("x"));
        int y = Integer.parseInt(matcher.group("y"));
        GameMenuMessage msg = gameController.selectUnit(x, y);
        GameMenuMessage.printMessage(msg);
        return msg.equals(GameMenuMessage.ENTER_UNIT_MENU);
    }

    private boolean runSelectBuilding(Matcher matcher) {
        int x = Integer.parseInt(matcher.group("x"));
        int y = Integer.parseInt(matcher.group("y"));
        GameMenuMessage msg = gameController.selectBuilding(x, y);
        GameMenuMessage.printMessage(msg);
        return msg.equals(GameMenuMessage.ENTER_BUILDING_MENU);
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
        GameMenuMessage.printMessage(gameController.setFearRate(rate));
    }

}
