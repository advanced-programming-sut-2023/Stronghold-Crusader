package view.enums.messages;

public enum SelectedBuildingMessage{
    STONE_NEEDED("stone is needed"),
    INVALID_COMMAND("invalid command");

    SelectedBuildingMessage(String message){
        this.message = message;
    }
    private final String message;
    public String getMessage(){
        return message;
    }
    public static void printMessage(SelectedBuildingMessage msg){
        System.out.println(msg.getMessage());
    }
}
