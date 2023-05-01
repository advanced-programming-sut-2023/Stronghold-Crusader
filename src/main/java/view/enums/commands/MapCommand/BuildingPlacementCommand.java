package view.enums.commands.MapCommand;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum BuildingPlacementCommand {
    DANDA("\\s*defense\\s+and\\s+attack\\s*"),
    TANDA("\\s*training\\s+and\\s+employment\\s*"),
    PRODUCTION("\\s*production\\s*"),
    SYMBOLIC("\\s*symbolic\\s*"),
    BACK("\\s*back\\s*"),
    DROP_BUILDING("");
    private String regex;

    BuildingPlacementCommand(String regex) {
        this.regex = regex;
    }

    public static BuildingPlacementCommand getCommand(String input) {
        for (BuildingPlacementCommand cmd : BuildingPlacementCommand.values()) {
            if (input.matches(cmd.regex)) return cmd;
        }
        return null;
    }

    public static Matcher getMatcher(String input, BuildingPlacementCommand cmd) {
        Pattern pattern = Pattern.compile(cmd.regex);
        Matcher matcher = pattern.matcher(input);
        return matcher;
    }
}
