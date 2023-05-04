package view.enums.messages.UserMessage;

public enum MainMenuMessage {
    INVALID_COMMAND("invalid command"),
    ENTER_NEW_GAME("Entered new game menu"),
    ENTER_PROFILE("Entered profile menu");
    private String message;
    private MainMenuMessage(String message){
        this.message = message;
    }

    public static void printMessage(MainMenuMessage msg){
        System.out.println(msg.message);
    }
}