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
    private final boolean requestMode;
    private final Pair info;
    private String acceptorMessage;
    private String message;


    public Trade(Player owner,Player acceptor, Material material, int amount, boolean isRequest  ) {
        info = new Pair(material, amount);
        this.acceptor = acceptor;
        this.owner = owner;
        this.requestMode = isRequest;
        acceptanceMode = false;
        numberOfTrades++;
        id = numberOfTrades;
        acceptorMessage = null;
        message = null;
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

    public Material getMaterial() {
        return Material.getMaterial(info.x);
    }

    public int getAmount() {
        return Integer.parseInt(info.y);
    }


    public boolean isRequest() {
        return requestMode;
    }


    public void accept() {
        acceptanceMode = true;
        owner.getNewTrades().add(this);
    }

    public void setAcceptorMessage(String acceptorMessage) {
        this.acceptorMessage = acceptorMessage;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        String trade = (!requestMode) ? "Donation" : "Request";
        return trade + "{" + "\n" +
                "id = " + id + "\n" +
                "acceptanceMode = " + acceptanceMode + "\n" +
                "owner = " + owner.getUsername() + "\n" +
                "acceptor = " + acceptor + "\n" +
                "price = " + requestMode + "\n" +
                "info = " + info + "\n" +
                "acceptorMessage = " + acceptorMessage + '\'' + "\n" +
                '}' + "\n";
    }

    public String showAcceptedTrade() {
        assert acceptor != null;
        return "the trade with id:[" + id + "]" + "accepted by " + acceptor.getUsername() + "\nmessage:" + acceptorMessage;
    }
}
