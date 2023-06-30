package controller.UserControllers;

import model.Stronghold;
import model.User.User;

import java.util.ArrayList;

public class FriendsMenuController {
    ArrayList<User> friends = new ArrayList<>();
    public  FriendsMenuController() {
         createFriends();
    }

    public void createFriends() {
        for (String username: MainController.getCurrentUser().getFriends()) {
            friends.add(Stronghold.getInstance().getUser(username));
        }
    }

    public ArrayList<User> getUsersFromText(String text) {
        ArrayList<User> searchResultUsers = new ArrayList<>();
        if (text.matches("[a|A]ll")) {
            searchResultUsers.addAll(Stronghold.getInstance().getUsers());
            searchResultUsers.remove(MainController.getCurrentUser());
        }
        else if (text.equals(""))
            searchResultUsers.addAll(friends);
        else {
            for (User user : Stronghold.getInstance().getUsers()) {
                if (user.getUsername().matches(text + ".*") && !user.equals(MainController.getCurrentUser())) searchResultUsers.add(user);
            }
      }
        return searchResultUsers;
    }


}
