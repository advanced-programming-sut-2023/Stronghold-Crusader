package view.enums.commands.MapCommand;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum BuildingPlacementCommand {
    BACK("\\s*back\\s*"),
    DROP_BUILDING("\\s*drop\\s+building\\s+-x\\s+(?<x>\\S+)\\s+-y\\s+(?<y>\\S+)\\s+-t\\s+(?<type>\\S+)\\s*"),
    CHOOSE_CATEGORY("\\s*category\\s+-c\\s+(?<buildingCategory>\\S+)\\s*");
    private final String regex;

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
        matcher.find();
        return matcher;
    }
}
