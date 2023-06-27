package view.GameMenus.TradeMenus;

public class TableItem {
    private final String State;
    private final int ID ;
    private final String To;
    private final String Good;
    private final int Amount;
    private final String Message;

    public TableItem(String state, int ID, String to, String good, int amount, String message) {
        State = state;
        this.ID = ID;
        To = to;
        Good = good;
        Amount = amount;
        Message = message;
    }

    public String getState() {
        return State;
    }

    public int getID() {
        return ID;
    }

    public String getTo() {
        return To;
    }

    public String getGood() {
        return Good;
    }

    public int getAmount() {
        return Amount;
    }

    public String getMessage() {
        return Message;
    }
}
