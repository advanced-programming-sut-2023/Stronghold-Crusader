package controller;


import model.Map.Map;
import model.Map.MapManager;
import model.User;
import utils.Pair;
import view.MapSelectMenu;

import java.util.ArrayList;

public class MapSelectController {
    private User currentUser;
    public MapSelectController(User currentUser){
        this.currentUser = currentUser;
    }

    public void run(){
        MapSelectMenu mapSelectMenu = new MapSelectMenu(this);
        while(true){
            switch (mapSelectMenu.run()){

            }
        }
    }

    public String getMapsList(){
        ArrayList<Pair> maps = MapManager.getMapList();
        String output = "";
        for(Pair map : maps){
            output += "Map name : " + map.x + " Number of players : " + map.y;
            output += "\n";
        }
        return output;
    }
}
