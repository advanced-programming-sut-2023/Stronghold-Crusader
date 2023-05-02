package controller.GameControllers;

import model.Game.Store.StoreMaterial;
import model.User.Player;
import model.Game.Game;
import view.GameMenus.MarketMenu;

import java.util.ArrayList;

public class MarketController {
    private final Player currentPlayer;
    private final Game game;
    public MarketController(Player player, Game game){
        this.game = game;
        this.currentPlayer = player;
    }

    public void run(){
        MarketMenu menu = new MarketMenu(this);
        while (true){
            menu.run();
        }
    }

    public String showPriceList(){
        String output = "";
        ArrayList<StoreMaterial> materials = StoreMaterial.getMaterialList();
        for (StoreMaterial material : materials){
            output += material.toString();
            output += "\n";
        }
        return output;
    }
}
