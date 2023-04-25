package view.enums.messages;

public enum MapMakerMessage {
    CHOOSE_MAP_NAME("Please choose a name for your map"),
    MAP_NAME_TAKEN("Map with given name already exists"),
    INVALID_COMMAND("invalid command");
    private String message;
    private MapMakerMessage(String message){
        this.message = message;
    }
    public static void printMessage(MapMakerMessage msg){
        System.out.println(msg.message);
    }
}
