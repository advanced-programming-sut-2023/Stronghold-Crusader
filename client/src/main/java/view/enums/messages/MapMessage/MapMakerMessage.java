package view.enums.messages.MapMessage;

public enum MapMakerMessage {

    INVALID_COMMAND("Invalid command"),
    INVALID_TEXTURE_TYPE("Invalid cell texture type"),
    INVALID_COORDINATE("Coordinate invalid"),
    INVALID_DIRECTION("Invalid direction"),
    INVALID_TREE_TYPE("Invalid tree type"),
    SET_TEXTURE_SUCCESS("cell texture changed successfully"),
    CLEAR_CELL_SUCCESS("Cell cleared successfully"),
    DROP_TREE_SUCCESS("Tree dropped successfully"),
    DROP_ROCK_SUCCESS("Cliff dropped successfully"),
    INVALID_COLOR_NAME("Invalid color name"),
    NOT_EMPTY("Target cell is not empty"),
    DROP_HEADQUARTER_SUCCESS("Headquarter dropped successfully"),
    ENTER_MAIN("Entered main menu successfully"),
    INVALID_CELL_TYPE("You can't drop this asset on this cell type"),
    INVALID_BUILDING("Invalid building type"),
    INVALID_UNIT("Invalid unit type"),
    PLACEMENT_SIGHT_VALID("valid"),
    BUILDING_DROP_SUCCESS("Building dropped successfully"),
    UNIT_DROP_SUCCESS("unit dropped successfully"),
    NO_STOREHOUSE_NEARBY("StoreHouses have to be built next to each other"),
    NO_GATEHOUSE_NEARBY("You can only build a bridge next to a gatehouse"),;
    private final String message;

    private MapMakerMessage(String message) {
        this.message = message;
    }

    public static void printMessage(MapMakerMessage msg) {
        System.out.println(msg.message);
    }
}
