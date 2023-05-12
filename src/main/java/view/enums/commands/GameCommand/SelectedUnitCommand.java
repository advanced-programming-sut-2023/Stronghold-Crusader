package view.enums.commands.GameCommand;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum SelectedUnitCommand {
    MOVE_UNIT("\\s*move\\s+unit\\s+to\\s+-x\\s+(?<x>\\d+)\\s+-y\\s+(?<y>\\d+)\\s*"),
    PATROL_UNIT("\\s*patrol\\s+unit\\s+-x1\\s+(?<x1>\\d+)\\s+-y1\\s+(?<y1>\\d+)\\s+-x2\\s+(?<x2>\\d+)\\s+-y2\\s+(?<y2>\\d+)"),
    DISBAND_UNIT("\\s*disband\\s+unit\\s*"),
    BACK("\\s*back\\s*");
    private final String regex;

    SelectedUnitCommand(String regex) {
        this.regex = regex;
    }

    public static SelectedUnitCommand getCommand(String input) {
        for (SelectedUnitCommand cmd : SelectedUnitCommand.values()) {
            if (input.matches(cmd.regex)) return cmd;
        }
        return null;
    }

    public static Matcher getMatcher(String command, SelectedUnitCommand sampleCommand) {
        Matcher matcher = Pattern.compile(sampleCommand.regex).matcher(command);
        matcher.find();
        return matcher;
    }

    public String getRegex() {
        return regex;
    }
}
