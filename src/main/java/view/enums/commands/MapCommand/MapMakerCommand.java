package view.enums.commands.MapCommand;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum MapMakerCommand {
    SET_TEXTURE("^\\s*settexture\\s+-x\\s+(?<x>\\S+)\\s+-y\\s+(?<y>\\S+)\\s+-t\\s+(?<type>\\S+)\\s*$"),
    SET_TEXTURE_BLOCK("^\\s*settexture\\s+-x1\\s+(?<x1>\\S+)\\s+-y1\\s+(?<y1>\\S+)\\s+\\s+-x2\\s+(?<x2>\\S+)\\s+-y2\\s+(?<y2>\\S+)-t\\s+(?<type>\\S+)\\s*$"),
    CLEAR_CELL("^\\s*clear\\s+-x\\s+(?<x>\\S+)\\s+-y\\s+(?<y>\\S+)"),
    DROP_ROCK("^\\s*droprock\\s+-x\\s+(?<x>\\S+)\\s+-y\\s+(?<y>\\S+)\\s+-d\\s+(?<direction>\\S+)\\s*$"),
    DROP_TREE("^\\s*droptree\\s+-x\\s+(?<x>\\S+)\\s+-y\\s+(?<y>\\S+)\\s+-t\\s+(?<type>\\S+)\\s*$"),
    DROP_BUILDING("\\s*drop\\s+building\\s+-x\\s+(?<x>\\S+)\\s+-y\\s+(?<y>\\S+)\\s+-t(?<type>\\S+)\\s*"),
    DROP_UNIT("\\s*drop\\s+building\\s+-x\\s+(?<x>\\S+)\\s+-y\\s+(?<y>\\S+)\\s+-t(?<type>\\S+)\\s*"),

    BACK("\\s*back\\s*");
    private String regex;

    MapMakerCommand(String regex) {
        this.regex = regex;
    }

    public static MapMakerCommand getCommand(String input) {
        for (MapMakerCommand cmd : MapMakerCommand.values()) {
            if (input.matches(cmd.regex)) return cmd;
        }
        return null;
    }

    public static Matcher getMatcher(String input, MapMakerCommand cmd) {
        Pattern pattern = Pattern.compile(cmd.regex);
        Matcher matcher = pattern.matcher(input);
        return matcher;
    }
}
