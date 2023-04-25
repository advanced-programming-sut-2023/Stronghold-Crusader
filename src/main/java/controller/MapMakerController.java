package controller;

import model.Map.Map;
import model.Map.MapLoader;
import model.MapAsset.MapAsset;
import model.MapAsset.Tree;
import model.User;
import model.enums.CellType;
import model.enums.Color;
import utils.Vector2D;
import view.MapMakingMenus.AssetPlacementMenu;
import view.MapMakingMenus.ChangeEnvironmentMenu;
import view.MapMakingMenus.MapMakerMenu;
import view.enums.messages.MapMakerMessage;

public class MapMakerController {
    private User currentUser;
    private Map map;
    private MapMakerMenu mapMakerMenu;
    public MapMakerController(User currentUser, Map map){
        this.currentUser = currentUser;
        this.map = map;
        mapMakerMenu = new MapMakerMenu(this);
    }

    public void run(){
        while (true){
            switch (mapMakerMenu.run()){
                case "back":
                    MapLoader.addMap(map);
                    return;
                case "environment":
                    runChangeEnvironment();
                    break;
                case "asset":
                    runAssetPlacement();
                    break;
                }
            }

    }

    private void runAssetPlacement(){
        AssetPlacementMenu menu = new AssetPlacementMenu(this);
        while (true){
            if(menu.run().equals("back")) return;
        }
    }

    private void runChangeEnvironment(){
        ChangeEnvironmentMenu menu = new ChangeEnvironmentMenu(this);
        while (true){
            if(menu.run().equals("back")) return;
        }
    }


    // TODO : complete the functions

    //errors for this section : invalid coordinates/existing building beforehand
    public MapMakerMessage setTexture(int x, int y, CellType texture){
        return null;
    }

    public MapMakerMessage setTexture(int x1, int y1, int x2, int y2, CellType texture){
        return null;
    }

    //errors for this section : invalid coordinates
    public MapMakerMessage clearCell(int x, int y){
        return null;
    }

    //errors for this section : invalid coordinates/invalid direction
    public MapMakerMessage dropRock(int x, int y, String direction){
        return null;
    }

    //errors for this section : invalid coordinates/invalid type/inappropriate TargetCellType
    public MapMakerMessage dropTree(int x, int y, Tree type){
        return null;
    }

    public MapMakerMessage dropHeadquarters(int x, int y, Color color){
        // increase map player number
        return null;
    }

    //errors for this section : invalid coordinates/invalid type/inappropriate TargetCellType
    public MapMakerMessage dropBuilding(int x, int y, MapAsset type){
        return null;
    }

    //errors for this section : invalid coordinates/invalid type/inappropriate TargetCellType
    public MapMakerMessage dropUnit(int x, int y, MapAsset type){
        return null;
    }
}
