package controller.UserControllers;

import model.User.User;

public class AvatarController {
    private final User currentUser;

    public AvatarController(User currentUser) {
        this.currentUser = currentUser;
    }

    public User getCurrentUser() {
        return currentUser;
    }

    public void setAvatar(String avatarPath){
        currentUser.setAvatarPath(avatarPath);
    }
}
