package view.enums.messages;

public enum MapSelectMessage {
    INVALID_COMMAND("invalid command");
    private String message;
    private MapSelectMessage(String message){
        this.message = message;
    }
    public String getMessage(){
        return message;
    }
    public static void printMessage(MapSelectMessage msg) {
        System.out.println(msg.message);
    }
}
