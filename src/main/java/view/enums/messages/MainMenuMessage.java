package view.enums.messages;

public enum MainMenuMessage {
    INVALID_COMMAND("invalid command");
    private String message;
    private MainMenuMessage(String message){
        this.message = message;
    }

    public void printMessage(MainMenuMessage msg){
        System.out.println(msg.message);
    }
}
