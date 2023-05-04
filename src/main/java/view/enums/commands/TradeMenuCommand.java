package view.enums.commands;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum TradeMenuCommand {
    REQUEST("\\s*trade((\\s+-t\\s+(?<resourceType>\\S+))|(\\s+-a\\s+(?<resourceType>\\S+))" +
            "|(\\s+-p\\s+(?<price>\\S+))|(\\s+-m\\s+(?<message>\\S+|(\"[^\"]+\"))))*\\s*"),

    BACK("\\s*back\\s*");

    private final String regex;
    TradeMenuCommand(String regex) {
        this.regex = regex;
    }
    public static TradeMenuCommand getCommand(String input) {
        for (TradeMenuCommand cmd : TradeMenuCommand.values()){
            if (input.matches(cmd.regex)) return cmd;
        }
        return null;
    }

    public static Matcher getMatcher(String command, TradeMenuCommand sampleCommand) {
        Matcher matcher = Pattern.compile(sampleCommand.regex).matcher(command);
        return matcher.matches() ? matcher : null;
    }

    public String getRegex() {
        return regex;
    }
}
