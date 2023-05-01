package view.enums.messages;

public enum ShowMapMessage {
    COORDINATE_OUT_OF_RANGE("cell coordinate is out of range"),
    MOVE_SUCCESSFUL("moved successfully"),
    TARGET_OUT_OF_RANGE("target coordinate is out of range"),
    INVALID_MOVE_COMMAND("invalid map move command"),
    INVALID_COMMAND("invalid command");
    private final String message;

    private ShowMapMessage(String message) {
        this.message = message;
    }

    public static void printMessage(ShowMapMessage msg) {
        System.out.println(msg.message);
    }

    public String getMessage() {
        return message;
    }
}
