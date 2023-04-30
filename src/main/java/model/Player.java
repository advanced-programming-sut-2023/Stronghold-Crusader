package model;

public class Player {
    public Governance governance;
    private String username;
    private String nickname;
    private String slogan;

    public Player(String username, String nickname, String slogan) {

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
}
