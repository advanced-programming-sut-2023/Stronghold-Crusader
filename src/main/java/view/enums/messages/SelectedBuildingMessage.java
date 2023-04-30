package view.enums.messages;

public enum SelectedBuildingMessage{
    STONE_NEEDED("stone is needed"),
    ENEMY_EXIST("There is an enemy near the building"),
    SUCCESS_REPAIR("The building has been repaired"),
    DELETED_BUILDING("The building was successfully deleted"),
    NOT_ALLOWED_TO_DELETE("Clearing the building is not allowed"),
    INVALID_COMMAND("invalid command");

    SelectedBuildingMessage(String message){
        this.message = message;
    }
    private final String message;
    public String getMessage(){
        return message;
    }
    public void printMessage(){
        System.out.println(message);
    }
}
