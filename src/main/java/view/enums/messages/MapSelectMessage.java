package view.enums.messages;

public enum MapSelectMessage {
    INVALID_COMMAND("invalid command"),
    MAP_SELECT_SUCCESS("Map selected successfully"),
    MODIFIABILITY_SUCCESS("Modifiability set successfully"),
    INVALID_MAP_ID("Map Id invalid"),
    MAP_NOT_SELECTED("Please first select a map"),
    PLAYER_ADD_SUCCESS("Player added successfully"),
    USERNAME_INVALID("User with the given username doesn't exist"),
    PLAYER_COUNT_EXCEEDED("You have exceeded map's player number"),
    INVALID_MODIFIABILITY("Invalid Modifiability format"),
    NOT_ENOUGH_PLAYERS("You have not selected enough players for this map"),
    GAME_CREATION_SUCCESS("Game created successfully"),
    INVALID_COLOR("Invalid color");
    private String message;
    MapSelectMessage(String message){
        this.message = message;
    }
    public String getMessage(){
        return message;
    }
    public static void printMessage(MapSelectMessage msg) {
        System.out.println(msg.message);
    }
}
