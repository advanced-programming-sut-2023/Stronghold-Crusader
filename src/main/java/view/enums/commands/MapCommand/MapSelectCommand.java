package view.enums.commands.MapCommand;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum MapSelectCommand {
    GET_MAP_LIST("\\s*show\\s+maps\\s*"),
    SELECT_MAP("\\s*select\\s+map\\s+-n\\s+(?<mapId>\\S+)\\s*"),
    PLAYERS_COUNT("\\s*players\\s+count\\s*"),
    ADD_PLAYER("\\s*add\\s+player\\s+-u\\s+(?<username>\\S+)\\s+-c\\s+(?<color>\\S+)\\s*"),
    MAP_MODIFIABILITY("\\s*set\\s+map\\s+modifiability\\s+-m\\s+(?<modifiability>\\S+)\\s*"),
    START_GAME("\\s*start\\s+game\\s*");
    private String regex;

    MapSelectCommand(String regex) {
        this.regex = regex;
    }

    public static MapSelectCommand getCommand(String input) {
        for (MapSelectCommand cmd : MapSelectCommand.values()) {
            if (input.matches(cmd.regex)) return cmd;
        }
        return null;
    }

    public static Matcher getMatcher(String input, MapSelectCommand cmd) {
        Pattern pattern = Pattern.compile(cmd.regex);
        Matcher matcher = pattern.matcher(input);
        matcher.matches();
        return matcher;
    }
}
