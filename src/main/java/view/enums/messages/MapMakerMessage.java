package view.enums.messages;

public enum MapMakerMessage {
    CHOOSE_MAP_NAME("Please choose a name for your map"),
    MAP_NAME_TAKEN("Map with given name already exists"),
    INVALID_COMMAND("invalid command"),
    INVALID_TEXTURE_TYPE("Invalid cell texture type"),
    INVALID_COORDINATE("Coordinate invalid"),
    INVALID_DIRECTION("Invalid direction"),
    INVALID_TREE_TYPE("Invalid tree type"),
    SET_TEXTURE_SUCCESS("cell texture changed successfully"),
    CLEAR_CELL_SUCCESS("Cell cleared successfully"),
    DROP_TREE_SUCCESS("Tree dropped successfully"),
    DROP_ROCK_SUCCESS("");
    private String message;
    private MapMakerMessage(String message){
        this.message = message;
    }
    public static void printMessage(MapMakerMessage msg){
        System.out.println(msg.message);
    }
}