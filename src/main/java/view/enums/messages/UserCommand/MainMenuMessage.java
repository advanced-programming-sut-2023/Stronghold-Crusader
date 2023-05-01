package view.enums.messages.UserCommand;

public enum MainMenuMessage {
    INVALID_COMMAND("invalid command");
    private String message;
    private MainMenuMessage(String message){
        this.message = message;
    }

    public static void printMessage(MainMenuMessage msg){
        System.out.println(msg.message);
    }
}
