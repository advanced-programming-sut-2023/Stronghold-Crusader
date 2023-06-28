package view.enums.messages.GameMessage;

public enum MarketMessage {
    INVALID_COMMAND("Invalid command"),
    CHOOSE_TYPE("Choose a goods type to trade"),
    INVALID_MATERIAL("Invalid material"),
    BUY_SUCCESS("Material bought successfully"),
    NOT_ENOUGH_STOCK("You don't have enough material in stock"),
    NOT_ENOUGH_GOLD("You don't have enough gold"),
    BUY_FAIL("Purchase terminated"),
    ENTERED_MAIN("Entered main menu successfully"),
    NOT_ENOUGH_SPACE("You don't have enough storage space for this action");
    private final String message;

    MarketMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public static void printMessage(MarketMessage msg) {
        System.out.println(msg.message);
    }
}
