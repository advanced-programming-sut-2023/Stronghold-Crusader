package controller.MapControllers;

import model.User;
import view.MapMenus.ShowMapMenu;

public class ShowMapController {
    private User currentUser;
    public ShowMapController(User currentUser){
        this.currentUser = currentUser;
    }

    public void run(){
        ShowMapMenu menu = new ShowMapMenu(this);
        while (true){
            if(menu.run().equals("back")) return;
        }
    }
}
