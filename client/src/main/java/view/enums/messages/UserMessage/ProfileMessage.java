package view.enums.messages.UserMessage;

public enum ProfileMessage {
    INVALID_COMMAND("Invalid command"),
    INVALID_USERNAME_FORMAT("Invalid username"),
    INVALID_PASSWORD_FORMAT("Invalid password"),
    INVALID_EMAIL_FORMAT("Invalid email"),
    INVALID_CAPTCHA("Invalid captcha"),
    INVALID_PASSWORD_LENGTH("Invalid password length"),
    USERNAME_TAKEN("User with given name already exists"),
    USERNAME_CHANGE_SUCCESS("Username changed successfully"),
    NICKNAME_CHANGE_SUCCESS("Nickname changed successfully"),
    PASSWORD_CHANGE_SUCCESS("Password changed successfully"),
    EMAIL_CHANGE_SUCCESS("Email changed successfully"),
    SLOGAN_CHANGE_SUCCESS("Slogan changed successfully"),
    SLOGAN_REMOVAL_SUCCESS("Slogan removed successfully"),
    PASSWORD_INCORRECT("Old password is incorrect!"),
    PASSWORD_NOT_NEW("Please enter a new password!"),
    ENTER_NEWPASS_AGAIN("Please enter your new password again"),
    EMAIL_EXISTS("Email already exists"),
    CAN_CHANGE_PASSWORD("true"),
    MAIN_MENU("Entered main menu");
    private final String message;

    ProfileMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public static void printMessage(ProfileMessage msg) {
        System.out.println(msg.message);
    }
}
