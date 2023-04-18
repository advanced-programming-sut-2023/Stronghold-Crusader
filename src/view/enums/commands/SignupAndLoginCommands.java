package view.enums.commands;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum SignupAndLoginCommands {
    CREATEUSER("\\s*user\\s+create((\\s+-u\\s+(?<username>\\S+))|" +
            "(\\s+-p\\s+(?<password>\\S+)(\\s+(?<passwordConfirmation>\\S+))?)|" +
            "(\\s+-email\\s+(?<email>\\S+))|" +
            "(\\s+-n\\s+(?<nickname>\\S+))|(\\s+-s\\s+(?<slogan>\\S*)))*\\s*");

    private String regex;
    SignupAndLoginCommands(String regex) {
        this.regex = regex;
    }

    public static Matcher getMatcher(String command, SignupAndLoginCommands sampleCommand) {
        Matcher matcher = Pattern.compile(sampleCommand.regex).matcher(command);
        return matcher.matches() ? matcher : null;
    }

    public String getRegex() {
        return regex;
    }
}
