package view.enums.messages;

public enum ProfileMessages {
    INVALID_COMMAND("invalid command");
    private String message;
    private ProfileMessages(String message){
        this.message = message;
    }

    public static void printMessage(ProfileMessages msg){
        System.out.println(msg.message);
    }
}
