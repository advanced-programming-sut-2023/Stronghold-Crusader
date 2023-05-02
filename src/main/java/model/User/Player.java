package model.User;

import model.Game.Governance;

public class Player {
    private Governance governance;
    private final String username;
    private final String nickname;
    private final String slogan;

    public Player(User user) {
        this.username = user.getUsername();
        this.nickname = user.getNickname();
        this.slogan = user.getSlogan();
        governance = new Governance(this);
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
}
