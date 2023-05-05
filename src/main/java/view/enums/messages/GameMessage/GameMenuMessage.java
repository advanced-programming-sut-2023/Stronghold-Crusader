package view.enums.messages.GameMessage;

public enum GameMenuMessage {
    INVALID_COMMAND("Invalid command"),
    INVALID_COORDINATE("Invalid coordinate"),
    ENTER_SHOW_MAP("Entered show map menu successfully"),
    INVALID_FOOD_RATE("Invalid food rate"),
    FOOD_RATE_CHANGE_SUCCESS("Successfully changed food rate"),
    INVALID_TAX_RATE("Invalid tax rate"),
    TAX_RATE_CHANGE_SUCCESS("Successfully changed tax rate"),
    INVALID_FEAR_RATE("Invalid fear rate"),
    FEAR_RATE_CHANGE_SUCCESS("Successfully changed fear rate"),
    ;
    private final String message;

    GameMenuMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public static void printMessage(GameMenuMessage msg) {
        System.out.println(msg.message);
    }
}
