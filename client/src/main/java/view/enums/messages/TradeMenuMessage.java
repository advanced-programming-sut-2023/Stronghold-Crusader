package view.enums.messages;

public enum TradeMenuMessage {
    EMPTY_FIELD("There are empty fields among the entered entries"),
    INVALID_MATERIAL("Invalid material"),
    INVALID_AMOUNT("The number of requested gold or amount of resource is not valid "),
    MATERIAL_NEEDED("There is not enough of this material in the storage"),
    GOLD_NEEDED("Gold is not enough"),
    REQUEST_SUCCESS("Request added successfully"),
    INVALID_ID("there is not any trade with this id"),
    ACCEPTED("trade accepted successfully"),
    ALREADY_ACCEPTED("this trade already accepted"),

    INVALID_COMMAND("Invalid command!"),
    ENTER_MAIN("Entered main menu successfully");


    private final String output;

    TradeMenuMessage() {
        this.output = "";
    }

    TradeMenuMessage(String output) {
        this.output = output;
    }

    public void printMessage() {
        if (!output.equals("")) System.out.println(output);
    }

    public String getMessage() {
        return output;
    }
}
