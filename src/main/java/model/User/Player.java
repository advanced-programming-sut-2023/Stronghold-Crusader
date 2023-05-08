package model.User;

import model.Game.Governance;
import model.Game.Trade;

import java.util.ArrayList;

public class Player {
    private final Governance governance;
    private final String username;
    private final String nickname;
    private final String slogan;
    private final ArrayList<Trade> newTrades;

    public Player(User user) {
        this.username = user.getUsername();
        this.nickname = user.getNickname();
        this.slogan = user.getSlogan();
        governance = new Governance();
        newTrades = new ArrayList<>();
    }

    public String getUsername() {
        return username;
    }

    public String getNickname() {
        return nickname;
    }

    public String getSlogan() {
        return slogan;
    }

    public Governance getGovernance() {
        return governance;
    }

    public ArrayList<Trade> getNewTrades() {
        return newTrades;
    }
}
