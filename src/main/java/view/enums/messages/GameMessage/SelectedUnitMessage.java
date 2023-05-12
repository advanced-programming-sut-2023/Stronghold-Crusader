package view.enums.messages.GameMessage;

public enum SelectedUnitMessage {
    PATROL_SUCCESS("Successfully selected patrol destination"),
    MOVE_SUCCESS("Successfully selected move destination"),
    INVALID_COORDINATE("Invalid coordinate"),
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
