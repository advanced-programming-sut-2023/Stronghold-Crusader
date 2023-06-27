package model.Game;

import model.enums.AssetType.Material;
import model.User.Player;

public class Trade {
    public static int numberOfTrades = 0;
    private final int ID;
    private boolean state;
    private final Player owner;
    private final Player acceptor;
    private final boolean requestMode;
    private final Material good;
    private final int amount;
    private String acceptorMessage;
    private String Message;


    public Trade(Player owner, Player acceptor, Material material, int amount, boolean isRequest  ) {
        good = material;
        this.amount = amount;
        this.acceptor = acceptor;
        this.owner = owner;
        this.requestMode = isRequest;
        state = false;
        numberOfTrades++;
        ID = numberOfTrades;
        acceptorMessage = null;
        Message = null;
    }

    public int getID() {
        return ID;
    }

    public boolean isAccepted() {
        return state;
    }

    public Player getOwner() {
        return owner;
    }

    public Player getAcceptor() {
        return acceptor;
    }

    public Material getMaterial() {
        return good;
    }

    public int getAmount() {
        return amount;
    }

    public String getMessage() {
        return Message;
    }

    public boolean isRequest() {
        return requestMode;
    }


    public void accept() {
        state = true;
        owner.getNewTrades().add(this);
    }

    public void setAcceptorMessage(String acceptorMessage) {
        this.acceptorMessage = acceptorMessage;
    }

    public void setMessage(String message) {
        this.Message = message;
    }

    @Override
    public String toString() {
        String trade = (!requestMode) ? "Donation" : "Request";
        return trade + "{" + "\n" +
                "id = " + ID + "\n" +
                "acceptanceMode = " + state + "\n" +
                "owner = " + owner.getUsername() + "\n" +
                "acceptor = " + acceptor + "\n" +
                "price = " + requestMode + "\n" +
                "info = " + good + ":" + amount + "\n" +
                "acceptorMessage = " + acceptorMessage + '\'' + "\n" +
                '}' + "\n";
    }

    public String showAcceptedTrade() {
        assert acceptor != null;
        return "the trade with id:[" + ID + "]" + "accepted by " + acceptor.getUsername() + "\nmessage:" + acceptorMessage;
    }
}
