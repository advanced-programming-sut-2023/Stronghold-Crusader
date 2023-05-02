package view.enums.messages.GameMessage;

public enum MarketMessage {
    INVALID_COMMAND("Invalid command"),
    CHOOSE_TYPE("Choose a goods type to trade");
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
