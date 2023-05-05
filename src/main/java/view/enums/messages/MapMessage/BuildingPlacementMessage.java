package view.enums.messages.MapMessage;

public enum BuildingPlacementMessage {
    INVALID_COORDINATE("Invalid coordinate"),
    INVALID_BUILDING_TYPE("Invalid building type"),

    INVALID_COMMAND("Invalid command"),
    BUILDING_DROP_SUCCESS("Building dropped successfully"),
    INVALID_CELL_TYPE("You can not drop in this cell type"),
    NOT_ENOUGH_RESOURCE("Not enough resources"),
    INVALID_BUILDING_CATEGORY("Invalid Building category"),
    BUILDING_CATEGORY_SUCCESS("Building category set successfully");
    BuildingPlacementMessage(String message) {
        this.message = message;
    }

    private final String message;

    public String getMessage() {
        return message;
    }

    public static void printMessage(BuildingPlacementMessage msg) {
        System.out.println(msg.message);
    }
}
