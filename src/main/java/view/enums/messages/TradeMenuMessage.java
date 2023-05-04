package view.enums.messages;

public enum TradeMenuMessage {
    EMPTY_FIELD("There are empty fields among the entered entries"),
    INVALID_MATERIAL("Invalid material"),
    INVALID_AMOUNT("The number of requested gold or amount of resource is not valid "),
    MATERIAL_NEEDED("There is not enough of this material in the storage"),
    REQUEST_SUCCESS("Request added successfully"),

    INVALID_COMMAND("invalid command!");




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
}
