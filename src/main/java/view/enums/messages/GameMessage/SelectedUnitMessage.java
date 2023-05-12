package view.enums.messages.GameMessage;

public enum SelectedUnitMessage {
    INVALID_COMMAND("Invalid command");
    private final String message;

    SelectedUnitMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void printMessage() {
        System.out.println(message);
    }
}
