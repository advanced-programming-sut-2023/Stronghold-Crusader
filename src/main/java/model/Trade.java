package model;

import model.enums.AssetType.Material;
import model.User.Player;
import utils.Pair;

public class Trade {
    public static int numberOfTrades = 0;
    private final int id;
    private boolean acceptanceMode;
    private final Player owner;
    private final Player acceptor;
    private final int price;
    private final Pair info;
    private final String message;
    private String acceptorMessage;


    public Trade(Player owner, String message, Material material, int amount, int price) {
        info = new Pair(material, amount);
        this.owner = owner;
        this.price = price;
        acceptanceMode = false;
        numberOfTrades++;
        id = numberOfTrades;
        this.message = message;
        acceptor = null;
        acceptorMessage = null;
    }

    public int getId() {
        return id;
    }

    public boolean getAcceptanceMode() {
        return acceptanceMode;
    }

    public Player getOwner() {
        return owner;
    }

    public Player getAcceptor() {
        return acceptor;
    }

    public Pair getInfo() {
        return info;
    }

    public String getMessage() {
        return message;
    }

    public boolean isAcceptanceMode() {
        return acceptanceMode;
    }

    public String getAcceptorMessage() {
        return acceptorMessage;
    }

    public void accept() {
        acceptanceMode = true;
    }

    @Override
    public String toString() {
        String trade = (price == 0) ? "Donation" : "Request";
        return trade + "{" +
                "id=" + id +
                ", acceptanceMode=" + acceptanceMode +
                ", owner=" + owner +
                ", acceptor=" + acceptor +
                ", price=" + price +
                ", info=" + info +
                ", message='" + message + '\'' +
                ", acceptorMessage='" + acceptorMessage + '\'' +
                '}';
    }
}
