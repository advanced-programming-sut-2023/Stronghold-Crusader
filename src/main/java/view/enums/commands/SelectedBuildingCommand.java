package view.enums.commands;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum SelectedBuildingCommand {
    REPAIR("\\s*repair\\s*"),
    INFO("\\s*info\\s*"),
    DELETE("\\s*delete\\s*");

    private final String regex;

    SelectedBuildingCommand(String regex) {
        this.regex = regex;
    }

    public static SelectedBuildingCommand getCommand(String input) {
        for (SelectedBuildingCommand cmd : SelectedBuildingCommand.values()) {
            if (input.matches(cmd.regex)) return cmd;
        }
        return null;
    }

    public static Matcher getMatcher(String command, SelectedBuildingCommand sampleCommand) {
        Matcher matcher = Pattern.compile(sampleCommand.regex).matcher(command);
        return matcher.matches() ? matcher : null;
    }

    public String getRegex() {
        return regex;
    }
    }
