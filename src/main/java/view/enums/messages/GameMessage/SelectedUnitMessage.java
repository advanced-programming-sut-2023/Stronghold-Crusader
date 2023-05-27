package view.enums.messages.GameMessage;

public enum SelectedUnitMessage {
    NO_TARGET("No targets found"),
    TARGET_SELECT_SUCCESS("Successfully selected attack target"),
    NO_ATTACKING_UNIT("No attacking units found to set state"),
    STATE_SELECT_SUCCESS("Successfully selected unit state"),
    INVALID_STATE("Invalid unit state"),
    PATROL_SUCCESS("Successfully selected patrol destination"),
    MOVE_SUCCESS("Successfully selected move destination"),
    INVALID_COORDINATE("Invalid coordinate"),
    INVALID_DESTINATION("Your destination is unattainable"),
    NO_PATH("There is not any path to the destination"),
    TUNNEL_NOT_NEARBY("Tunnel destination not in range"),
    NO_WALLS_OR_TOWERS("No walls or tunnels exist in the destination"),
    TUNNEL_PLACEMENT_SUCCESS("Successfully placed tunnel"),
    INVALID_COMMAND("Invalid command");
    private final String message;

    SelectedUnitMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void printMessage() {
        System.out.println(message);
    }
}
