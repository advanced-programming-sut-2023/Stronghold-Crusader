package view.enums.messages.GameMessage;

public enum GameMenuMessage {
    INVALID_COMMAND("invalid command"),
    INVALID_COORDINATE("Invalid coordinate"),
    ENTER_SHOW_MAP("Entered show map menu successfully");
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
