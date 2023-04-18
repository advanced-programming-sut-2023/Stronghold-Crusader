package view.enums.messages;

public enum ProfileMessages {
    INVALID_COMMAND("invalid command"),
    INVALID_USERNAME_FORMAT("Invalid Username"),
    USERNAME_TAKEN("User with given name already exists"),
    USERNAME_CHANGE_SUCCESS("Username changed successfully"),
    NICKNAME_CHANGE_SUCCESS("Nickname changed successfully"),
    PASSWORD_CHANGE_SUCCESS("Password changed successfully"),
    EMAIL_CHANGE_SUCCESS("Email changed successfully"),
    SLOGAN_CHANGE_SUCCESS("Slogan changed successfully"),
    SLOGAN_REMOVAL_SUCCESS("Slogan changed successfully"),
    PASSWORD_INCORRECT("Current password is incorrect!"),
    PASSWORD_NOT_NEW("Please enter a new password!"),
    ENTER_NEWPASS_AGAIN("Please enter your new password again");
    private String message;
    private ProfileMessages(String message){
        this.message = message;
    }
    public String getMessage(){
        return message;
    }
    public static void printMessage(ProfileMessages msg){
        System.out.println(msg.message);
    }
}
