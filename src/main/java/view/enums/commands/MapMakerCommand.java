package view.enums.commands;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum MapMakerCommand {
    // TODO : add commands
    PLACE_ASSETS(""),
    SET_TEXTURE(""),
    CLEAR_CELL(""),
    DROP_ROCK(""),
    DROP_TREE(""),
    DROP_HEADQUARTERS(""),
    DROP_BUILDING(""),
    DROP_UNIT(""),
    BACK("");
    private String regex;
    MapMakerCommand(String regex){
        this.regex = regex;
    }
    public static MapMakerCommand getCommand(String input){
        for(MapMakerCommand cmd : MapMakerCommand.values()){
            if(input.matches(cmd.regex)) return cmd;
        }
        return null;
    }

    public static Matcher getMatcher(String input, MapMakerCommand cmd){
        Pattern pattern = Pattern.compile(cmd.regex);
        Matcher matcher = pattern.matcher(input);
        return matcher;
    }
}
