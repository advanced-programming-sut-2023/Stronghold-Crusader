package view.enums.commands.GameCommand;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum SelectedUnitCommand {
    ;
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
        return matcher.matches() ? matcher : null;
    }

    public String getRegex() {
        return regex;
    }
}
