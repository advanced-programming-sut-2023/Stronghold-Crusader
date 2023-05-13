package view.GameMenus;

import controller.GameControllers.MarketController;
import view.Menu;
import view.enums.commands.GameCommand.MarketCommand;
import view.enums.messages.GameMessage.MarketMessage;

import java.util.Scanner;
import java.util.regex.Matcher;

public class MarketMenu {
    private final MarketController marketController;
    private final Scanner scanner;

    public MarketMenu(MarketController marketController) {
        this.scanner = Menu.getScanner();
        this.marketController = marketController;
    }

    public String run() {
        String input;
        while (true) {
            MarketMessage.printMessage(MarketMessage.CHOOSE_TYPE);
            input = scanner.nextLine();
            MarketCommand cmd = MarketCommand.getCommand(input);
            if (cmd == null) {
                MarketMessage.printMessage(MarketMessage.INVALID_COMMAND);
                continue;
            }
            Matcher matcher = MarketCommand.getMatcher(input, cmd);
            switch (cmd) {
                case PRICE_LIST:
                    System.out.println(marketController.showPriceList());
                    break;
                case BUY:
                    runBuy(matcher);
                    break;
                case SELL:
                    runSell(matcher);
                    break;
                case BACK:
                    MarketMessage.printMessage(MarketMessage.ENTERED_MAIN);
                    return "back";
            }
        }
    }

    public boolean confirm(String output) {
        System.out.println(output);
        String input = scanner.nextLine();
        return input.matches("[yY]");
    }

    private void runBuy(Matcher matcher) {
        String materialName = matcher.group("item");
        int amount = Integer.parseInt(matcher.group("amount"));
        System.out.println(marketController.buy(materialName, amount));
    }

    private void runSell(Matcher matcher) {
        String materialName = matcher.group("item");
        int amount = Integer.parseInt(matcher.group("amount"));
        System.out.println(marketController.sell(materialName, amount));
    }
}
