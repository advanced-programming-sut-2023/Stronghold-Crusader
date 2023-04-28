package view.enums.messages;

public enum MapSelectMessage {
    INVALID_COMMAND("invalid command"),
    MAP_SELECT_SUCCESS("Map selected successfully"),
    INVALID_MAP_ID("Map Id invalid"),
    MAP_NOT_SELECTED("Please first select a map"),
    PLAYER_ADD_SUCCESS("Player added successfully");
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
