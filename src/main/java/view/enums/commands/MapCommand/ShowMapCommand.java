package view.enums.commands.MapCommand;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum ShowMapCommand {
    SHOW_DETAILS("\\s*show\\s+details\\s+-x\\s+(?<x>\\d+)\\s+-y\\s+(?<y>\\d+)\\s*"),
    MOVE_MAP("\\s*map\\s+(?<details>.+)"),
    EXIT("\\s*exit\\s*");
    private final String regex;

    ShowMapCommand(String regex) {
        this.regex = regex;
    }

    public static ShowMapCommand getCommand(String input) {
        for (ShowMapCommand cmd : ShowMapCommand.values()) {
            if (input.matches(cmd.regex)) return cmd;
        }
        return null;
    }

    public static Matcher getMatcher(String input, ShowMapCommand cmd) {
        Pattern pattern = Pattern.compile(cmd.regex);
        Matcher matcher = pattern.matcher(input);
        matcher.find();
        return matcher;
    }
}
