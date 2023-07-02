package view.enums.messages.UserMessage;

public enum SignupAndLoginMessage {
    ALREADY_LOGGED_IN("user already in a session"),
    EMPTY_FIELD("There are empty fields among the entered entries"),
    INVALID_USERNAME_FORMAT("Username format is not valid"),
    INVALID_EMAIL_FORMAT("Email format is not valid"),
    PASSWORD_WEEK_LENGTH("password must has at least 6 characters"),
    PASSWORD_WEEK_LOWERCASE_PROBLEM("password must include at least 1 lowercase character"),
    PASSWORD_WEEK_UPPERCASE_PROBLEM("password must include at least 1 upperCase character"),
    PASSWORD_WEEK_NUMBER_PROBLEM("password must include at least 1 number"),
    PASSWORD_WEEK_SPECIAL_CHARACTER_PROBLEM("password must include at least 1 special character"),
    EXISTING_USERNAME("The username already taken"),
        CONFIRMATION_ERROR("Passwords do not match"),
    EXISTED_EMAIL("The email already taken"),
    RANDOM_PASSWORD,
    PASSWORD_CHANGE_SUCCESSFULLY("Password changed successfully"),
    PICKING_QUESTION("Pick your security question: 1. What is my father’s name?" +
            " 2. What was my first pet’s name? 3. What is my mother’s last name?"),
    FAIL_PICKING_UP_QUESTION("Some error found"),
    SUCCESS_CREATING_USER("user created successfully"),
    USER_DOES_NOT_EXIST("There is no user with this username"),
    INCORRECT_PASSWORD("The password entered is incorrect"),
    INCORECT_RECOVERY_ANSWER,
    WEEK_SIMPLE_PASSWORD,
    TOO_MANY_ATTEMPTS,
    SUCCESS_PROCESS,
    INVALID_CAPTCHA("Invalid CAPTCHA"),
    INVALID_COMMAND("Invalid command!"),
    SIGNUP_MENU("Entered signup menu"),
    LOGIN_MENU("Entered login menu"),
    MAIN_MENU("Entered main menu");
    private final String output;

    SignupAndLoginMessage() {
        this.output = "";
    }

    SignupAndLoginMessage(String output) {
        this.output = output;
    }

    public void printMessage() {
        if (!output.equals("")) System.out.println(output);
    }

    public String getOutput() {
        return output;
    }
}
