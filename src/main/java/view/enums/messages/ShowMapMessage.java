package view.enums.messages;

public enum ShowMapMessage {
    INVALID_COMMAND("invalid command"),
    ;
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
