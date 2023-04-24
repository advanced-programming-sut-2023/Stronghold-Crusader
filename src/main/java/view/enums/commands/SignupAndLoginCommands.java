package view.enums.commands;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum SignupAndLoginCommands {
    CREATE_USER("\\s*user\\s+create((\\s+-u\\s+(?<username>\\S+|(\"[^\"]+\")))|" +
            "(\\s+-p\\s+(?<password>(\"[^\"]+\")|\\S+)(\\s+(?<passwordConfirmation>\\S+|(\"[^\"]+\")))?)|" +
            "(\\s+-email\\s+(?<email>\\S+|(\"[^\"]+\")))|" +
            "(\\s+-n\\s+(?<nickname>\\S+|(\"[^\"]+\")))|(?<sloganTest>\\s+-s\\s+(?<slogan>\\S*|(\"[^\"]+\"))))*\\s*"),
    QUESTION_PICK("\\s*question\\s+pick\\s+-q\\s+(?<questionNumber>\\d+)\\s+-a\\s+(?<answer>.+)" +
            "\\s+-c\\s+(?<answerConfirm>.+)\\s*"),
    LOGIN("\\s*user\\s+login((\\s+-u\\s+(?<username>\\S+|(\"[^\"]+\")))|" +
            "(\\s+-p\\s+(?<password>\\S+|(\"[^\"]+\"))))*\\s*"),
    CHANGE_PASSWORD("\\s*forgot\\s+my\\s+password\\s+-u\\s+(?<username>\\S+|(\"[^\"]+\"))\\s*"),
    LOGOUT("\\s*logout\\s*");

    private final String regex;
    SignupAndLoginCommands(String regex) {
        this.regex = regex;
    }
    public static SignupAndLoginCommands getCommand(String input) {
        for (SignupAndLoginCommands cmd : SignupAndLoginCommands.values()){
            if (input.matches(cmd.regex)) return cmd;
        }
        return null;
    }

    public static Matcher getMatcher(String command, SignupAndLoginCommands sampleCommand) {
        Matcher matcher = Pattern.compile(sampleCommand.regex).matcher(command);
        return matcher.matches() ? matcher : null;
    }

    public String getRegex() {
        return regex;
    }
}