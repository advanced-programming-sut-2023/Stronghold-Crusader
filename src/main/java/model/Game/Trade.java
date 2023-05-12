package model.Game;

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

    public boolean isAccepted() {
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

    public int getPrice() {
        return price;
    }


    public void accept() {
        acceptanceMode = true;
        owner.getNewTrades().add(this);
    }

    public void setAcceptorMessage(String acceptorMessage) {
        this.acceptorMessage = acceptorMessage;
    }

    @Override
    public String toString() {
        String trade = (price == 0) ? "Donation" : "Request";
        return trade + "{" + "\n" +
                "id = " + id + "\n" +
                "acceptanceMode = " + acceptanceMode + "\n" +
                "owner = " + owner.getUsername() + "\n" +
                "acceptor = " + acceptor + "\n" +
                "price = " + price + "\n" +
                "info = " + info + "\n" +
                "message = " + message + '\'' + "\n" +
                "acceptorMessage = " + acceptorMessage + '\'' + "\n" +
                '}' + "\n";
    }

    public String showAcceptedTrade() {
        assert acceptor != null;
        return "the trade with id:[" + id + "]" + "accepted by " + acceptor.getUsername() + "\nmessage:" + acceptorMessage;
    }
}
