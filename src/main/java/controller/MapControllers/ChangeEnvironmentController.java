package controller.MapControllers;

import model.Map.Map;
import model.User;
import view.MapMenus.ChangeEnvironmentMenu;

public class ChangeEnvironmentController {
    private Map map;
    public ChangeEnvironmentController(Map map){
        this.map = map;
    }

    public void run(){
        ChangeEnvironmentMenu menu = new ChangeEnvironmentMenu(this);
        while (true){
            if(menu.run().equals("back")) return;
        }
    }
}
