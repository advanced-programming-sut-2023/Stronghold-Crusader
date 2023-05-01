package view.enums.messages.UserMessage;

public enum MainMenuMessage {
    INVALID_COMMAND("invalid command"),
    ENTER_NEW_GAME("Entered map select menu"),
    ENTER_PROFILE("Entered profile menu"),
    ENTER_LOGIN_MENU("Entered the login menu");
    private final String message;
    private MainMenuMessage(String message){
        this.message = message;
    }

    public static void printMessage(MainMenuMessage msg){
        System.out.println(msg.message);
    }
}
