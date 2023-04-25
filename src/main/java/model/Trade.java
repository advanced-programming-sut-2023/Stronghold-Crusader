package model;

import utils.Pair;

public class Trade {
    private int id;
    private boolean acceptanceMode;
    private Player owner;
    private Player receiver;
    private final Pair info;

    Trade(String material, int price) {
        info = new Pair(material, price);
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

    public Player getReceiver() {
        return receiver;
    }

    public Pair getInfo() {
        return info;
    }
}
