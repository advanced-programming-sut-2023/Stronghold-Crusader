package view.enums.messages.GameMessage;

public enum GameMenuMessage {
    INVALID_COMMAND("invalid command");
    private final String message;

    GameMenuMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void printMessage() {
        System.out.println(message);
    }
}
