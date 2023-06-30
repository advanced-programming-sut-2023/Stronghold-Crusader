package view.enums.messages.MapMessage;

public enum MapCreationMessage {
    ENTER_ID("Please enter mapId"),
    ID_TAKEN("Map with the given id already exists"),
    ENTER_PLAYER_NUMER("Please enter player number"),
    ENTER_SIZE("Please enter map size"),
    MAP_INITIALIZE_SUCCESS("Map was successfully initialized");
    private String message;

    private MapCreationMessage(String message) {
        this.message = message;
    }

    public static void printMessage(MapCreationMessage msg) {
        System.out.println(msg.message);
    }

    public String getMessage() {
        return message;
    }
}
