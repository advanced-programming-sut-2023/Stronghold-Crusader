package controller.UserControllers;

import model.Stronghold;
import model.User.User;

import java.util.ArrayList;

public class FriendsMenuController {
    private final User currentUser;

    public  FriendsMenuController(User user) {
        this.currentUser = user;
    }

    public ArrayList<User> getUsersFromText(String text) {
        ArrayList<User> searchResultUsers = new ArrayList<>();
        if (text.matches("[a|A]ll")) {
            searchResultUsers.addAll(Stronghold.getInstance().getUsers());
            searchResultUsers.remove(currentUser);
        }
        else if (text.equals("")) searchResultUsers.addAll(currentUser.getFriends());
        else {
            for (User user : Stronghold.getInstance().getUsers()) {
                if (user.getUsername().matches(text + ".*") && !user.equals(currentUser)) searchResultUsers.add(user);
            }
      }
        return searchResultUsers;
    }
    public User getCurrentUser() {
        return currentUser;
    }
}
