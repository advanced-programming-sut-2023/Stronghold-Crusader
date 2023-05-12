package view.enums.commands.GameCommand;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum SelectedBuildingCommand {
    REPAIR("\\s*repair\\s*"),
    INFO("\\s*info\\s*"),
    STOP_PRODUCTION("\\s*stop\\s+production\\s*"),
    RESUME_PRODUCTION("\\s*resume\\s+production\\s*"),
    CREATE_UNIT("\\s*createunit((\\s+-t\\s+(?<type>\\S+)|(\\s+-c\\s+(?<count>\\S+))*"),
    CHANGE_ENTRANCE("\\s*change\\s+entrance\\s+gate"),
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
