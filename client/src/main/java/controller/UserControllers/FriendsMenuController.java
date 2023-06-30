package controller.UserControllers;

import model.Stronghold;
import model.User.User;

import java.util.ArrayList;

public class FriendsMenuController {

    public  FriendsMenuController() {
    }

    public ArrayList<User> getUsersFromText(String text) {
        ArrayList<User> searchResultUsers = new ArrayList<>();
        if (text.matches("[a|A]ll")) {
            searchResultUsers.addAll(Stronghold.getInstance().getUsers());
            searchResultUsers.remove(MainController.getCurrentUser());
        }
        else if (text.equals(""))
            searchResultUsers.addAll(MainController.getCurrentUser().getFriends());
        else {
            for (User user : Stronghold.getInstance().getUsers()) {
                if (user.getUsername().matches(text + ".*") && !user.equals(MainController.getCurrentUser())) searchResultUsers.add(user);
            }
      }
        return searchResultUsers;
    }


}
