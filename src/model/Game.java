package model;

import java.util.ArrayList;
import java.util.HashMap;

public class Game {
    private HashMap<String, Player> players;
    private int turnCounter;
    private Map map;
    public Game(String mapFileName, ArrayList<User> users){
        map = new Map(mapFileName);
        //TODO make players from the users
    }
}
