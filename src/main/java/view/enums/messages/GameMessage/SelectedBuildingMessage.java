package view.enums.messages.GameMessage;

public enum SelectedBuildingMessage {
    MATERIAL_NEEDED("material is needed"),
    ENEMY_EXIST("There is an enemy near the building"),
    SUCCESS_REPAIR("The building has been repaired"),
    DELETED_BUILDING("The building was successfully deleted"),
    NOT_ALLOWED_TO_DELETE("Clearing the building is not allowed"),
    STOP_PRODUCTION("Production stopped"),
    RESUME_PRODUCTION("Production resumes"),
    INVALID_COMMAND_FOR_BUILDING("this command is not allowed here"),
    INVALID_UNIT_FOR_CREATING("this building does not train this type of unit"),
    GOLD_NEEDED("gold is not enough"),
    WEAPON_NEEDED("Weapon is not enough"),
    EMPTY_FIELD("There are empty fields among the entered entries"),
    INVALID_COUNT("The number of production soldiers requested is not valid"),
    SUCCESS_CREATING_UNIT("units has been successfully created"),
    SUCCESS("Success!"),
    ENEMY_IN_GATE("Access is not allowed"),
    INVALID_FOOD_RATE("Invalid food rate"),
    FOOD_RATE_CHANGE_SUCCESS("Successfully changed food rate"),
    INVALID_TAX_RATE("Invalid tax rate"),
    TAX_RATE_CHANGE_SUCCESS("Successfully changed tax rate"),

    INVALID_COMMAND("Invalid command");

    SelectedBuildingMessage(String message) {
        this.message = message;
    }

    private final String message;

    public String getMessage() {
        return message;
    }

    public void printMessage() {
        System.out.println(message);
    }
}
