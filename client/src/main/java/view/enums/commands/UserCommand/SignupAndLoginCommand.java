package view.enums.commands.UserCommand;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum SignupAndLoginCommand {
    CREATE_USER("\\s*user\\s+create((\\s+-u\\s+(?<username>\\S+|(\"[^\"]+\")))|" +
            "(\\s+-p\\s+(?<password>(\"[^\"]+\")|\\S+)(\\s+(?<passwordConfirmation>\\S+|(\"[^\"]+\")))?)|" +
            "(\\s+-email\\s+(?<email>\\S+|(\"[^\"]+\")))|" +
            "(\\s+-n\\s+(?<nickname>\\S+|(\"[^\"]+\")))|(?<sloganTest>\\s+-s\\s+(?<slogan>\\S*|(\"[^\"]+\"))))*\\s*"),
    QUESTION_PICK("\\s*question\\s+pick\\s+-q\\s+(?<questionNumber>\\d+)\\s+-a\\s+(?<answer>\\S+|(\"[^\"]+\"))" +
            "\\s+-c\\s+(?<answerConfirm>\\S+|(\"[^\"]+\"))\\s*"),
    LOGIN("\\s*user\\s+login((\\s+-u\\s+(?<username>\\S+|(\"[^\"]+\")))|" +
            "(\\s+-p\\s+(?<password>\\S+|(\"[^\"]+\"))))*\\s*(?<stayLoggedIn>--stay-logged-in)?\\s*"),
    CHANGE_PASSWORD("\\s*forgot\\s+my\\s+password\\s+-u\\s+(?<username>\\S+|(\"[^\"]+\"))\\s*"),
    LOGOUT("\\s*logout\\s*"),
    EXIT("\\s*exit\\s*"),
    LOGIN_MENU("\\s*login\\s+menu\\s*"),
    SIGNUP_MENU("\\s*signup\\s+menu\\s*");

    private final String regex;

    SignupAndLoginCommand(String regex) {
        this.regex = regex;
    }

    public static SignupAndLoginCommand getCommand(String input) {
        for (SignupAndLoginCommand cmd : SignupAndLoginCommand.values()) {
            if (input.matches(cmd.regex)) return cmd;
        }
        return null;
    }

    public static Matcher getMatcher(String command, SignupAndLoginCommand sampleCommand) {
        Matcher matcher = Pattern.compile(sampleCommand.regex).matcher(command);
        return matcher.matches() ? matcher : null;
    }

    public String getRegex() {
        return regex;
    }
}
